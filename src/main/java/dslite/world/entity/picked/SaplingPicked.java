package dslite.world.entity.picked;

import dslite.player.Player;
import dslite.utils.enums.ObjectType;
import dslite.world.entity.MapObject;

public final class SaplingPicked extends MapObject {

    public SaplingPicked() {
        super(ObjectType.SAPLING_PICKED);
    }

    @Override
    public void interact(Player player) {
    }

}