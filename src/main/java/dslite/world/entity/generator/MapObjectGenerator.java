package dslite.world.entity.generator;

import dslite.utils.enums.ObjectType;
import dslite.utils.enums.Texture;
import dslite.world.entity.MapObject;
import dslite.world.entity.mining.Grass;
import dslite.world.entity.mining.Sapling;

public final class MapObjectGenerator {
    public static MapObject getObject(ObjectType type) {
        return switch (type) {
            case GRASS -> new Grass();
            case SAPLING -> new Sapling();
            default -> null;
        };
    }
}
