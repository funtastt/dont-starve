package dslite.world.entity.mining_res;

import dslite.utils.enums.ObjectType;
import dslite.player.Player;
import dslite.world.entity.MapObject;

public final class GrassPicked extends MapObject {

    protected GrassPicked() {
        super(ObjectType.GRASS_PICKED);
    }

    @Override
    public void interact(Player player) {
    }

}