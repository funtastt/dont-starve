package dslite.world.entity.picked;

import dslite.player.Player;
import dslite.utils.enums.MapObjectType;
import dslite.world.entity.MapObject;

public final class BushPicked extends MapObject {

    public BushPicked() {
        super(MapObjectType.BUSH_PICKED);
    }

    @Override
    public void interact(Player player) {
    }

}