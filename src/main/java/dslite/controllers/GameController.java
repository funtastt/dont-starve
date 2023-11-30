package dslite.controllers;


import dslite.player.Player;
import dslite.player.Screen;
import dslite.ui.info.MainCharacteristics;
import dslite.world.World;
import dslite.world.map.WorldMap;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public final class GameController {
    @FXML
    private VBox mainPane;
    private static World world;
    private static WorldMap map;
    private static Player player;
    private static Screen gameScreen;
    private MainCharacteristics characteristics;
    private EventHandler<KeyEvent> keyHandler;


    @FXML
    public void initialize() {
        world = new World(this);
        map = world.getMap();
        player = world.getPlayer();

        gameScreen = Screen.getInstance();
        gameScreen.setWorld(world);

        characteristics = new MainCharacteristics();

        HBox hbox = new HBox(25.0);
        hbox.getChildren().addAll(gameScreen, characteristics);
        hbox.setAlignment(Pos.CENTER);

        mainPane.getChildren().addAll(hbox);
        setKeyListener();
        enableView();
    }

    private void setKeyListener() {
        keyHandler = keyEvent -> {
            KeyCode code = keyEvent.getCode();

            switch (code) {
                case W -> player.move(0, -1);
                case A -> player.move(-1,0);
                case S -> player.move(0, 1);
                case D -> player.move(1, 0);
            }

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
        mainPane.setOnKeyPressed(keyHandler);
    }

    private void disableView() {
        gameScreen.setDisable(true);
        characteristics.setDisable(true);
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
}
