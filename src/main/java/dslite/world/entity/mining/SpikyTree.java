package dslite.world.entity.mining;

import dslite.player.Player;
import dslite.ui.tiles.TileWithObject;
import dslite.utils.enums.ItemType;
import dslite.utils.enums.MapObjectType;
import dslite.world.entity.Item;
import dslite.world.entity.MapObject;
import dslite.world.entity.tools.Tool;

public final class SpikyTree extends MapObject {

    private byte health = 2;

    public SpikyTree() {
        super(MapObjectType.SPIKY_TREE);
    }

    @Override
    public void interact(Player player) {

        Item equippedItem = player.getEquippedItem();

        if (!(equippedItem instanceof Tool && equippedItem.getType() == ItemType.AXE)) return;

        TileWithObject t = getTile();
        if (player.getInventory().addItem(ItemType.TWIGS, getQuantity())) {
            player.decreaseActions(3);
            ((Tool) equippedItem).setDurability((byte) -1);
            if (--health <= 0) player.getMap().setTileAtPosition(t.getObjPosition(), t.getType());
        }
    }
}