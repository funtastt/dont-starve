package dslite.player;

import dslite.inventory.Inventory;
import dslite.ui.inventory.InventoryItemRow;
import dslite.views.GameView;
import dslite.interfaces.Updatable;
import dslite.enums.Texture;
import dslite.ui.tiles.Tile;
import dslite.world.World;
import dslite.world.map.WorldMap;
import dslite.world.map.Point;
import javafx.scene.image.Image;

public final class Player implements Updatable {
    public static final Image PLAYER_IMAGE = Texture.WILSON.getTextureImage();
    public static final double MAX_HEALTH = 100.0;
    public static final double MAX_SANITY = 100.0;
    public static final double MAX_HUNGER = 100.0;

    private int positionX;
    private int positionY;
    private double health;
    private double sanity;
    private double hunger;
    private byte actions;
    private GameView controller;
    private WorldMap map;
    private World world;
    private Tile[][] tileMap;
    private Inventory inventory;


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
        this.tileMap = map.getTilemap();
        controller = world.getController();
        controller.setInventory(new InventoryItemRow(this));
        this.inventory = new Inventory(this);
        setPosition(map.getSpawnPoint());
    }

    private void setPosition(Point point) {
        this.positionX = point.getX();
        this.positionY = point.getY();
    }

    @Override
    public void update() {
        GameView.getGameScreen().drawPlayer(this);
        inventory.update();
        controller.getCharacteristics().update();
    }

    public void move(int x, int y) {
        if (tileMap[positionX + x][positionY + y].getType().isPassable()) {
            positionX += x;
            positionY += y;
            decreaseActions(1);
        }
    }

    public void addHealth(double val) {
        if (val < 0) {
            health = Math.max(health + val, 0.0);
        } else {
            health = Math.min(health + val, MAX_HEALTH);
        }
    }

    public void addSanity(double val) {
        if (val < 0) {
            sanity = Math.max(sanity + val, 0.0);
        } else {
            sanity = Math.min(sanity + val, MAX_SANITY);
        }
    }

    public void addHunger(double val) {
        if (val < 0) {
            hunger = Math.max(hunger + val, 0.0);
        } else {
            hunger = Math.min(hunger + val, MAX_HUNGER);
        }
    }

    private void decreaseActions(int actions) {
        this.actions -= actions;
    }

    public World getWorld() {
        return world;
    }

    public WorldMap getMap() {
        return map;
    }

    public GameView getController() {
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

    public Inventory getInventory() {
        return inventory;
    }
}
