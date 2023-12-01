package dslite.world.entity.mining;

import dslite.player.Player;
import dslite.utils.enums.ItemType;
import dslite.utils.enums.MapObjectType;
import dslite.world.entity.MapObject;
import dslite.world.entity.picked.BushPicked;

public final class Bush extends MapObject {
    public Bush() {
        super(MapObjectType.BUSH);
    }

    @Override
    public void interact(Player player) {
        if (!player.getInventory().addItem(ItemType.BERRIES, getQuantity())) return;

        player.decreaseActions(1);
        getTile().setObject(new BushPicked());
    }
}
