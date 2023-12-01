package dslite.ui.views;


import dslite.ui.crafting.CraftingView;
import dslite.ui.inventory.InventoryItemRow;
import dslite.player.Player;
import dslite.player.GameScreen;
import dslite.ui.characteristics.MainCharacteristics;
import dslite.world.World;
import dslite.world.map.WorldMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public final class GameView {
    @FXML
    private StackPane mainPane;
    private static World world;
    private static WorldMap map;
    private static Player player;
    private static GameScreen gameScreen;
    private MainCharacteristics characteristics;
    private EventHandler<KeyEvent> keyHandler;

    private InventoryItemRow inventory;
    private CraftingView craftingView;

    @FXML
    public void initialize() {
        world = new World(this);
        map = world.getMap();
        player = world.getPlayer();

        gameScreen = GameScreen.getInstance();
        gameScreen.setWorld(world);

        characteristics = new MainCharacteristics();

        craftingView = new CraftingView();

        VBox inventoryContainer = new VBox();
        inventoryContainer.getChildren().add(inventory);
        inventoryContainer.setAlignment(Pos.BOTTOM_CENTER);

        craftingView.setAlignment(Pos.CENTER);
        changeCraftViewVisibility();

        characteristics.setAlignment(Pos.TOP_RIGHT);

        VBox gameContainer = new VBox();
        gameContainer.getChildren().add(gameScreen);
        gameContainer.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(gameContainer, inventoryContainer, craftingView, characteristics);

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
                case C -> changeCraftViewVisibility();
                case F12 -> MenuView.setGameStageFullScreen();
                case SPACE -> player.interact();
                case F -> player.place();
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

    private void changeCraftViewVisibility() {
        boolean visibility = craftingView.isVisible();

        if (!visibility) {
            craftingView.setVisible(true);
            craftingView.toFront();
        } else {
            craftingView.setVisible(false);
        }
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

    public static GameScreen getGameScreen() {
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
