package dslite.world.entity.mining;

import dslite.player.Player;
import dslite.ui.tiles.TileWithObject;
import dslite.utils.enums.ItemType;
import dslite.utils.enums.MapObjectType;
import dslite.world.entity.MapObject;

public final class Flower extends MapObject {

    public Flower() {
        super(MapObjectType.FLOWER);
    }

    @Override
    public void interact(Player player) {
        if (!player.getInventory().addItem(ItemType.PETAL, getQuantity())) return;

        TileWithObject t = getTile();
        player.addSanity(2.0);
        player.decreaseActions(1);
        player.getMap().setTileAtPosition(t.getObjPosition(), t.getType());
    }
}
