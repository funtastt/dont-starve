package dslite.world.entity.mining_res;

import dslite.utils.enums.ItemType;
import dslite.utils.enums.ObjectType;
import dslite.player.Player;
import dslite.world.entity.MapObject;

public final class Grass extends MapObject {
    public Grass() {
        super(ObjectType.GRASS);
    }

    @Override
    public void interact(Player player) {
        if (!player.getInventory().addItem(ItemType.CUT_GRASS, getQuantity())) return;

        player.decreaseActions(1);
        getTile().setObject(new GrassPicked());
    }
}
