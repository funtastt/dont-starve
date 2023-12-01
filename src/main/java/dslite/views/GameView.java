package dslite.views;


import dslite.ui.crafting.CraftingView;
import dslite.ui.inventory.InventoryItemRow;
import dslite.player.Player;
import dslite.player.Screen;
import dslite.ui.characteristics.MainCharacteristics;
import dslite.world.World;
import dslite.world.map.WorldMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public final class GameView {
    @FXML
    private VBox mainPane;
    private static World world;
    private static WorldMap map;
    private static Player player;
    private static Screen gameScreen;
    private MainCharacteristics characteristics;
    private EventHandler<KeyEvent> keyHandler;

    private InventoryItemRow inventory;
    private CraftingView craftingView;



    @FXML
    public void initialize() {
        world = new World(this);
        map = world.getMap();
        player = world.getPlayer();

        gameScreen = Screen.getInstance();
        gameScreen.setWorld(world);

        characteristics = new MainCharacteristics();

        craftingView = new CraftingView();

        HBox hbox = new HBox(25.0);
        hbox.getChildren().addAll(craftingView, gameScreen, characteristics);
        hbox.setAlignment(Pos.CENTER);

        mainPane.getChildren().addAll(hbox, inventory);
        setKeyListener();
        enableView();
    }

    private void setKeyListener() {
        keyHandler = keyEvent -> {
            KeyCode code = keyEvent.getCode();

            switch (code) {
                case W, UP -> player.move(0, -1);
                case A, LEFT -> player.move(-1, 0);
                case S, DOWN -> player.move(0, 1);
                case D, RIGHT -> player.move(1, 0);
                case SPACE -> player.interact();
                default -> {
                    if (code.isDigitKey()) {
                        player.getInventory().setSelectedSlot(Integer.parseInt(code.getChar()) - 1);
                    }
                }
            }

            // Update everything after user interaction
            world.update();
            gameScreen.update();
            player.update();
            map.update();
        };
        mainPane.setOnKeyPressed(keyHandler);
    }

    private void enableView() {
        gameScreen.setDisable(false);
        gameScreen.setFocusTraversable(true);
        characteristics.setDisable(false);
        craftingView.setDisable(false);
        mainPane.setOnKeyPressed(keyHandler);
    }

    private void disableView() {
        gameScreen.setDisable(true);
        characteristics.setDisable(true);
        craftingView.setDisable(true);
        mainPane.getScene().removeEventHandler(KeyEvent.ANY, keyHandler);
    }

    public static World getWorld() {
        return world;
    }

    public static WorldMap getMap() {
        return map;
    }

    public static Player getPlayer() {
        return player;
    }

    public static Screen getGameScreen() {
        return gameScreen;
    }

    public MainCharacteristics getCharacteristics() {
        return characteristics;
    }

    public EventHandler<KeyEvent> getKeyHandler() {
        return keyHandler;
    }

    public void setInventory(InventoryItemRow inventory) {
        this.inventory = inventory;
    }

    public InventoryItemRow getInventory() {
        return inventory;
    }

    public CraftingView getCraftingView() {
        return craftingView;
    }
}
