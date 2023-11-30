package dslite.world.entity.mining_res;

import dslite.enums.ObjectType;
import dslite.player.Player;

public final class Grass extends MapObject {
    public Grass() {
        super(ObjectType.GRASS);
    }

    @Override
    public void interact(Player player) {
    }
}
