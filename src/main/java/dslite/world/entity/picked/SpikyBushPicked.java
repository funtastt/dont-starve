package dslite.world.entity.picked;

import dslite.player.Player;
import dslite.utils.enums.MapObjectType;
import dslite.world.entity.MapObject;

public final class SpikyBushPicked extends MapObject {

    public SpikyBushPicked() {
        super(MapObjectType.SPIKY_BUSH_PICKED);
    }

    @Override
    public void interact(Player player) {
    }
}
