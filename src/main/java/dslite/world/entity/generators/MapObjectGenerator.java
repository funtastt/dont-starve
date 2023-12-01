package dslite.world.entity.generators;

import dslite.utils.enums.MapObjectType;
import dslite.world.entity.MapObject;
import dslite.world.entity.dropped.CutGrassDropped;
import dslite.world.entity.dropped.TwigsDropped;
import dslite.world.entity.mining.Grass;
import dslite.world.entity.mining.Sapling;
import dslite.world.entity.survival.CampFireMapObject;

public final class MapObjectGenerator {
    public static MapObject getObject(MapObjectType type) {
        return switch (type) {
            case GRASS -> new Grass();
            case SAPLING -> new Sapling();
            case CUT_GRASS -> new CutGrassDropped();
            case TWIGS -> new TwigsDropped();
            case CAMPFIRE -> new CampFireMapObject();
            default -> null;
        };
    }
}
