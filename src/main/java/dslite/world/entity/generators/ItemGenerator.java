package dslite.world.entity.generators;

import dslite.utils.enums.ItemType;
import dslite.world.entity.Item;
import dslite.world.entity.food.Berries;
import dslite.world.entity.food.Carrot;
import dslite.world.entity.food.CookedBerries;
import dslite.world.entity.food.CookedCarrot;
import dslite.world.entity.resouces.*;
import dslite.world.entity.survival.CampFire;
import dslite.world.entity.survival.Garland;
import dslite.world.entity.tools.Axe;
import dslite.world.entity.tools.Pickaxe;

public class ItemGenerator {
    public static Item getItem(ItemType type) {
        return switch (type) {
            case AXE -> new Axe();
            case CAMPFIRE -> new CampFire();
            case CUT_GRASS -> new CutGrass();
            case TWIGS -> new Twigs();
            case ROCK -> new Rock();
            case PICKAXE -> new Pickaxe();
            case BERRIES -> new Berries();
            case CARROT -> new Carrot();
            case LOG -> new Log();
            case PETAL -> new Petal();
            case GOLD -> new Gold();
            case FLINT -> new Flint();
            case GARLAND -> new Garland();
            case BERRIES_COOKED -> new CookedBerries();
            case CARROT_COOKED -> new CookedCarrot();
        };
    }
}
