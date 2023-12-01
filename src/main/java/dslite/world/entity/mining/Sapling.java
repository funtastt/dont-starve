package dslite.world.entity.mining;

import dslite.player.Player;
import dslite.utils.enums.ItemType;
import dslite.utils.enums.ObjectType;
import dslite.world.entity.MapObject;
import dslite.world.entity.picked.SaplingPicked;

public final class Sapling extends MapObject {
    public Sapling() {
        super(ObjectType.SAPLING);
    }

    @Override
    public void interact(Player player) {
        if (!player.getInventory().addItem(ItemType.TWIGS, getQuantity())) return;

        player.decreaseActions(1);
        getTile().setObject(new SaplingPicked());
    }
}
