package dslite.world.entity.dropped;

import dslite.player.Player;
import dslite.ui.tiles.TileWithObject;
import dslite.utils.enums.ItemType;
import dslite.world.entity.MapObject;

public abstract class DroppedItem extends MapObject {

    private final ItemType heldItem;

    public DroppedItem(ItemType type) {
        super(type.getPickedItemSprite());
        setSprite(type.getPickedItemSprite());
        heldItem = type;
    }

    @Override
    public void interact(Player player) {
        if (player.getInventory().addItem(heldItem, getQuantity())) {
            TileWithObject t = getTile();
            player.getMap().setTileAtPosition(t.getObjPosition(), t.getType());
        }
    }
}
