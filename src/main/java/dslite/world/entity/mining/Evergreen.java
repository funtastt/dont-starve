package dslite.world.entity.mining;

import dslite.player.Player;
import dslite.ui.tiles.TileWithObject;
import dslite.utils.enums.ItemType;
import dslite.utils.enums.MapObjectType;
import dslite.world.entity.Item;
import dslite.world.entity.MapObject;
import dslite.world.entity.tools.Tool;

public final class Evergreen extends MapObject {

    private byte health = 2;

    public Evergreen() {
        super(MapObjectType.EVERGREEN);
    }

    @Override
    public void interact(Player player) {
        Item equippedItem = player.getEquippedItem();

        if (!(equippedItem instanceof Tool && equippedItem.getType() == ItemType.AXE)) return;

        TileWithObject t = getTile();
        if (player.getInventory().addItem(ItemType.LOG, getQuantity())) {
            player.decreaseActions(3);
            ((Tool) equippedItem).setDurability(-1);
            if (--health <= 0) player.getMap().setTileAtPosition(t.getObjPosition(), t.getType());
        }
    }
}