package dslite.controllers;


import dslite.player.Player;
import dslite.ui.GameScreen;
import dslite.ui.info.MainCharacteristics;
import dslite.world.World;
import dslite.world.WorldMap;
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
    private static GameScreen grid;
    private MainCharacteristics characteristics;
    private EventHandler<KeyEvent> keyHandler;


    @FXML
    public void initialize() {
        world = new World(this);
        map = world.getMap();
        player = world.getPlayer();

        grid = GameScreen.getInstance();
        grid.setWorld(world);

        characteristics = new MainCharacteristics();

        HBox hbox = new HBox(25.0);
        hbox.getChildren().addAll(grid, characteristics);
        hbox.setAlignment(Pos.CENTER);

        mainPane.getChildren().addAll(hbox);
        setKeyListener();
        enableView();
    }

    private void setKeyListener() {
        keyHandler = keyEvent -> {
            KeyCode code = keyEvent.getCode();

            switch (code) {
                case W: {                                           //Move up
                    player.move((byte) 0, (byte) -1);
                    break;
                }
                case A: {                                           //Move left
                    player.move((byte) -1, (byte) 0);
                    break;
                }
                case S: {                                           //Move down
                    player.move((byte) 0, (byte) 1);
                    break;
                }
                case D: {                                           //Move right
                    player.move((byte) 1, (byte) 0);
                    break;
                }
            }

            world.update();
            grid.update();
            player.update();
        };
        mainPane.setOnKeyPressed(keyHandler);
    }

    private void enableView() {
        grid.setDisable(false);
        grid.setFocusTraversable(true);
        characteristics.setDisable(false);
        mainPane.setOnKeyPressed(keyHandler);
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

    public static GameScreen getGrid() {
        return grid;
    }

    public MainCharacteristics getCharacteristics() {
        return characteristics;
    }

    public EventHandler<KeyEvent> getKeyHandler() {
        return keyHandler;
    }
}
