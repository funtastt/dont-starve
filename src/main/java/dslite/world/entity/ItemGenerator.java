package dslite.world.entity;

import dslite.utils.enums.ItemType;
import dslite.world.entity.mining_res.CutGrass;

public class ItemGenerator {
    public static Item getItem(ItemType type) {
        switch (type) {
            case CUT_GRASS: return new CutGrass();
            default: return null;
        }
    }
}
