package dslite.world.entity.dropped;

import dslite.player.Player;
import dslite.ui.tiles.TileWithObject;
import dslite.utils.enums.ItemType;
import dslite.utils.enums.MapObjectType;
import dslite.world.entity.MapObject;

public final class GoldDropped extends MapObject {
    public GoldDropped() {
        super(MapObjectType.GOLD);
    }

    @Override
    public void interact(Player player) {

        if (!player.getInventory().addItem(ItemType.GOLD,getQuantity())) return;

        TileWithObject t = getTile();
        player.getMap().setTileAtPosition(t.getObjPosition(),t.getType());
    }
}
