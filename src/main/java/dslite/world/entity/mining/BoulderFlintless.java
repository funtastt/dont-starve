package dslite.world.entity.mining;

import dslite.player.Player;
import dslite.ui.tiles.TileWithObject;
import dslite.utils.enums.ItemType;
import dslite.utils.enums.MapObjectType;
import dslite.world.entity.Item;
import dslite.world.entity.MapObject;
import dslite.world.entity.tools.Tool;

public final class BoulderFlintless extends MapObject {

    public BoulderFlintless() {
        super(MapObjectType.BOULDER_FLINTLESS);
    }

    @Override
    public void interact(Player player) {
        Item equippedItem = player.getEquippedItem();

        if (!(equippedItem instanceof Tool && equippedItem.getType() == ItemType.PICKAXE)) return;

        TileWithObject t = getTile();
        if (player.getInventory().addItem(ItemType.ROCK, getQuantity())) {
            player.decreaseActions(3);
            ((Tool) equippedItem).setDurability(-1);
            player.getMap().setTileAtPosition(t.getObjPosition(), t.getType());
        }
    }
}