package dslite.world.entity.mining;

import dslite.utils.enums.ItemType;
import dslite.utils.enums.ObjectType;
import dslite.player.Player;
import dslite.world.entity.MapObject;
import dslite.world.entity.picked.GrassPicked;

public final class Grass extends MapObject {
    public Grass() {
        super(ObjectType.GRASS);
    }

    @Override
    public void interact(Player player) {
        if (!player.getInventory().addItemAndCheck(ItemType.CUT_GRASS, getQuantity())) return;

        player.decreaseActions(1);
        getTile().setObject(new GrassPicked());
    }
}
