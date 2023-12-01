package dslite.world.entity.picked;

import dslite.utils.enums.MapObjectType;
import dslite.player.Player;
import dslite.world.entity.MapObject;

public final class GrassPicked extends MapObject {

    public GrassPicked() {
        super(MapObjectType.GRASS_PICKED);
    }

    @Override
    public void interact(Player player) {
    }

}