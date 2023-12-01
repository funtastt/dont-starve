package dslite.world.entity.mining;

import dslite.player.Player;
import dslite.utils.enums.ItemType;
import dslite.utils.enums.MapObjectType;
import dslite.world.entity.MapObject;
import dslite.world.entity.picked.SpikyBushPicked;

public final class SpikyBush extends MapObject {

    public SpikyBush() {
        super(MapObjectType.SPIKY_BUSH);
    }

    @Override
    public void interact(Player player) {
        if (!player.getInventory().addItem(ItemType.TWIGS, getQuantity())) return;

        player.addHealth(-3.0f);
        player.decreaseActions(1);
        getTile().setObject(new SpikyBushPicked());

    }
}