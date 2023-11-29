package dslite.player;


import dslite.controllers.GameController;
import dslite.interfaces.Updatable;
import dslite.world.World;
import dslite.world.WorldMap;
import dslite.world.map.Point;
import javafx.scene.image.Image;

public final class Player implements Updatable {
    public static final double MAX_HEALTH = 100.0;
    public static final double MAX_SANITY = 100.0;
    public static final double MAX_HUNGER = 100.0;

    private int positionX;
    private int positionY;
    private double health;
    private double sanity;
    private double hunger;
    private byte actions;
    private GameController controller;
    private WorldMap map;
    private World world;

    public Player() {
        health = MAX_HEALTH;
        sanity = MAX_SANITY;
        hunger = MAX_HUNGER;
        actions = World.ACTIONS_PER_DAYTIME;
    }

    public Player(World world) {
        this();
        this.world = world;
        this.map = world.getMap();
        controller = world.getController();
    }

    @Override
    public void update() {
    }

    public World getWorld() {
        return world;
    }

    public WorldMap getMap() {
        return map;
    }

    public GameController getController() {
        return controller;
    }

    public byte getActions() {
        return actions;
    }

    public void setActions(byte actions) {
        this.actions = actions;
    }


    public double getHealth() {
        return health;
    }

    public double getSanity() {
        return sanity;
    }

    public double getHunger() {
        return hunger;
    }

    public Point getPos() {
        return new Point(positionX, positionY);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

}
