package dslite.controllers;


import dslite.ui.chat.ChatView;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import static dslite.ui.chat.ChatApplication.getChatApplication;

public final class GameController {
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

    private ChatView chatView;

    @FXML
    public void initialize() {
        world = new World(this);
        map = world.getMap();
        player = world.getPlayer();

        gameScreen = GameScreen.getInstance();
        gameScreen.setWorld(world);

        characteristics = new MainCharacteristics();

        craftingView = new CraftingView();

        chatView =  getChatApplication().getChatView();

        VBox inventoryContainer = new VBox();
        inventoryContainer.getChildren().add(inventory);
        inventoryContainer.setAlignment(Pos.BOTTOM_CENTER);

        craftingView.setAlignment(Pos.CENTER);
        changeCraftViewVisibility();

        characteristics.setAlignment(Pos.TOP_RIGHT);

        VBox chatContainer = new VBox();
        chatContainer.getChildren().add(chatView);
        chatContainer.setAlignment(Pos.BOTTOM_LEFT);

        VBox gameContainer = new VBox();
        gameContainer.getChildren().add(gameScreen);
        gameContainer.setAlignment(Pos.CENTER);
        mainPane.getChildren().addAll(gameContainer, inventoryContainer, craftingView, characteristics, chatContainer);

        mainPane.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            if (!chatView.getBoundsInParent().contains(mouseEvent.getX(), mouseEvent.getY())) {
                chatView.requestFocus();
            }
        });

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
                case I -> changeCraftViewVisibility();
                case F12 -> MenuController.setGameStageFullScreen();
                case SPACE -> player.interact();
                case F -> player.place();
                case ESCAPE -> {
                    if (craftingView.isVisible()) {
                        changeCraftViewVisibility();
                    }
                }
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
