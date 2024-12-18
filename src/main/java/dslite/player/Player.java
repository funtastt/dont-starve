package dslite.player;

import dslite.player.inventory.Inventory;
import dslite.ui.inventory.InventoryItemRow;
import dslite.ui.tiles.TileWithObject;
import dslite.utils.enums.ItemType;
import dslite.controllers.GameController;
import dslite.utils.interfaces.Updatable;
import dslite.utils.enums.Texture;
import dslite.ui.tiles.Tile;
import dslite.world.World;
import dslite.world.entity.Item;
import dslite.world.entity.generators.ItemGenerator;
import dslite.world.map.WorldMap;
import dslite.world.map.Point;
import javafx.scene.image.Image;

public final class Player implements Updatable {
    public static final Image PLAYER_IMAGE = Texture.WILSON.getTextureImage();
    public static final double MAX_HEALTH = 100.0;
    public static final double MAX_SANITY = 100.0;
    public static final double MAX_SATIETY = 100.0;

    private int positionX;
    private int positionY;
    private double health;
    private double sanity;
    private double satiety;
    private int actions;
    private GameController controller;
    private WorldMap map;
    private World world;
    private Tile[][] tileMap;
    private Inventory inventory;

    private boolean hasCampFireNearby;


    public Player() {
        this.health = MAX_HEALTH;
        this.sanity = MAX_SANITY;
        this.satiety = MAX_SATIETY;
        this.actions = World.ACTIONS_PER_DAYTIME;
    }

    public Player(World world) {
        this();
        this.world = world;
        this.map = world.getMap();
        this.tileMap = map.getTilemap();
        this.controller = world.getController();
        this.controller.setInventory(new InventoryItemRow(this));
        this.inventory = new Inventory(this);
        setPosition(map.getSpawnPoint());
    }

    private void setPosition(Point point) {
        this.positionX = point.getX();
        this.positionY = point.getY();
    }

    @Override
    public void update() {
        // Update everything
        GameController.getGameScreen().drawPlayer(this);
        hasCampFireNearby = false;
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

    public boolean canCraft(ItemType item) {
        return inventory.tryToCraft(ItemGenerator.getItem(item), false);
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

    public void addSatiety(double val) {
        if (val < 0) {
            satiety = Math.max(satiety + val, 0.0);
        } else {
            satiety = Math.min(satiety + val, MAX_SATIETY);
        }
    }

    public void decreaseActions(int actions) {
        this.actions -= actions;
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

    public int getActions() {
        return actions;
    }

    public void setActions(int actions) {
        this.actions = actions;
    }


    public double getHealth() {
        return health;
    }

    public double getSanity() {
        return sanity;
    }

    public double getSatiety() {
        return satiety;
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

    public Tile[][] getTileMap() {
        return tileMap;
    }

    private Tile getTile() {
        return tileMap[positionX][positionY];
    }

    private void setTile(TileWithObject tile) {
        tileMap[positionX][positionY] = tile;
    }

    public void interact() {
        if (getTile() instanceof TileWithObject) {
            ((TileWithObject) getTile()).getObject().interact(this);
        }
    }

    public void craft(ItemType selectedType) {
        if (inventory.tryToCraft(ItemGenerator.getItem(selectedType), true)) {
            decreaseActions(1);
        }
    }

    public void place() {
        if (getTile() instanceof TileWithObject) return;

        Item item = getEquippedItem();
        if (item == null || !item.getType().isPlaceable()) return;

        TileWithObject obj =
                new TileWithObject(getTile().getType(), getPos(),
                        item.getType().getPickedItemSprite());


        obj.getObject().setQuantity((byte) getEquippedItemCount());
        removeEquippedItem();
        setTile(obj);
    }

    public Item getEquippedItem() {
        return getInventory().getSelectedSlot().getStoredItem();
    }

    public int getEquippedItemCount() {
        return getInventory().getSelectedSlot().getItemCount();
    }

    public void removeEquippedItem() {
        getInventory().removeSlot(getInventory().getSelectedSlot());
    }

    public boolean hasCampFireNearby() {
        return hasCampFireNearby;
    }

    public void setHasCampFireNearby(boolean hasCampFireNearby) {
        this.hasCampFireNearby = hasCampFireNearby;
    }
}
