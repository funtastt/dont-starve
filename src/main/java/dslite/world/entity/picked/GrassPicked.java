package dslite.world.entity.picked;

import dslite.utils.enums.ObjectType;
import dslite.player.Player;
import dslite.world.entity.MapObject;

public final class GrassPicked extends MapObject {

    public GrassPicked() {
        super(ObjectType.GRASS_PICKED);
    }

    @Override
    public void interact(Player player) {
    }

}