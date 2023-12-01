package dslite.world.entity.picked;

import dslite.player.Player;
import dslite.utils.enums.MapObjectType;
import dslite.world.entity.MapObject;

public final class SaplingPicked extends MapObject {

    public SaplingPicked() {
        super(MapObjectType.SAPLING_PICKED);
    }

    @Override
    public void interact(Player player) {
    }

}