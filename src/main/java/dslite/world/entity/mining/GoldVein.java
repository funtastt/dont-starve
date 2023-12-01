package dslite.world.entity.mining;

import dslite.player.Player;
import dslite.player.inventory.Inventory;
import dslite.ui.tiles.TileWithObject;
import dslite.utils.enums.ItemType;
import dslite.utils.enums.MapObjectType;
import dslite.world.entity.Item;
import dslite.world.entity.MapObject;
import dslite.world.entity.tools.Tool;

public final class GoldVein extends MapObject {
    public GoldVein() {
        super(MapObjectType.GOLD_VEIN);
    }

    @Override
    public void interact(Player player) {
        Item equippedItem = player.getEquippedItem();

        if (!(equippedItem instanceof Tool && equippedItem.getType() == ItemType.PICKAXE)) return;

        TileWithObject t = getTile();
        Inventory inv = player.getInventory();
        if (inv.addItem(ItemType.ROCK, getQuantity()) && inv.addItem(ItemType.GOLD, getQuantity())) {
            player.decreaseActions(4);
            ((Tool) equippedItem).setDurability(-1);
            player.getMap().setTileAtPosition(t.getObjPosition(), t.getType());
        }
    }
}
