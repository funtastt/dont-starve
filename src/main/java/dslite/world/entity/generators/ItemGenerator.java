package dslite.world.entity.generators;

import dslite.utils.enums.ItemType;
import dslite.world.entity.Item;
import dslite.world.entity.resouces.CutGrass;
import dslite.world.entity.resouces.Twigs;
import dslite.world.entity.survival.CampFire;
import dslite.world.entity.tools.Axe;

public class ItemGenerator {
    public static Item getItem(ItemType type) {
        return switch (type) {
            case AXE -> new Axe();
            case CAMPFIRE -> new CampFire();
            case CUT_GRASS -> new CutGrass();
            case TWIGS -> new Twigs();
        };
    }
}
