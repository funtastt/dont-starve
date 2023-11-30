package dslite.world.entity;

import dslite.utils.enums.ObjectType;
import dslite.player.Player;
import dslite.ui.tiles.TileWithObject;

public abstract class MapObject {

    protected int sprite;
    protected ObjectType type;
    protected TileWithObject tile;
    protected byte quantity;

    protected MapObject(ObjectType type) {
        this.type = type;
        sprite = type.getSprite();
        quantity = 1;
    }

    protected MapObject(ObjectType type, TileWithObject tile) {
        this(type);
        this.tile = tile;
    }


    protected MapObject(int index) {
        this(ObjectType.getByIndex(index));
    }

    public abstract void interact(Player player);

    public void setType(ObjectType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(byte quantity) {
        this.quantity = quantity;
    }

    public ObjectType getType() {
        return type;
    }

    public TileWithObject getTile() {
        return tile;
    }

    public void setTile(TileWithObject tile) {
        this.tile = tile;
    }

    protected void setSprite(int spriteIndex) {
        this.sprite = spriteIndex;
    }

    public int getSprite() {
        return sprite;
    }

}