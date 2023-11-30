package dslite.world.entity;

import dslite.world.entity.mining_res.Grass;
import dslite.world.entity.mining_res.MapObject;

public final class ObjectGenerator {
    public static MapObject getObject(int index) {
        return switch (index) {
            case 2 -> new Grass();
            default -> null;
        };
    }
}
