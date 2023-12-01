package dslite.world.entity;

import dslite.utils.enums.MapObjectType;
import dslite.player.Player;
import dslite.ui.tiles.TileWithObject;

public abstract class MapObject {

    protected int sprite;
    protected MapObjectType type;
    protected TileWithObject tile;
    protected byte quantity;

    protected MapObject(MapObjectType type) {
        this.type = type;
        sprite = type.getSprite();
        quantity = 1;
    }

    protected MapObject(MapObjectType type, TileWithObject tile) {
        this(type);
        this.tile = tile;
    }


    protected MapObject(int index) {
        this(MapObjectType.getBySprite(index));
    }

    public abstract void interact(Player player);

    public void setType(MapObjectType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(byte quantity) {
        this.quantity = quantity;
    }

    public MapObjectType getType() {
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