package dslite.world;

import dslite.controllers.GameController;
import dslite.controllers.MenuController;
import dslite.interfaces.Updatable;
import dslite.player.Player;

public final class World implements Updatable {

    private final Player player;
    private final WorldMap map;
    private final GameController controller;

    private int dayCount = 0;
    private GameState gameState;

    //Initial values
    public static final byte ACTIONS_PER_DAYTIME = 85;
    public static final byte ACTIONS_PER_NIGHTTIME = 30;
    public static final byte DAY_LENGTH = ACTIONS_PER_DAYTIME + ACTIONS_PER_NIGHTTIME;

    public World(GameController controller) {
        this.controller = controller;
        gameState = GameState.DAY;
        map = new WorldMap(MenuController.getSizeX(),MenuController.getSizeY());
        player = new Player(this);
    }

    @Override
    public void update() {
        if (player.getActions() <= 0) {
            changeGameState();
        }
    }

    private void changeGameState() {
        if (gameState == GameState.DAY) {
            player.setActions(ACTIONS_PER_NIGHTTIME);
            gameState = GameState.NIGHT;
        } else {
            ++dayCount;
            player.setActions(ACTIONS_PER_DAYTIME);
            gameState = GameState.DAY;
        }
    }

    public int getDayCount() {
        return dayCount;
    }

    public GameState getGameState() {
        return gameState;
    }

    public GameController getController() {
        return controller;
    }

    public WorldMap getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

}