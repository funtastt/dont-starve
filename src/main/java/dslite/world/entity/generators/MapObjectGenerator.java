package dslite.world.entity.generators;

import dslite.utils.enums.MapObjectType;
import dslite.world.entity.MapObject;
import dslite.world.entity.dropped.*;
import dslite.world.entity.mining.*;
import dslite.world.entity.picked.*;

public final class MapObjectGenerator {
    public static MapObject getObject(MapObjectType type) {
        return switch (type) {
            case GRASS -> new Grass();
            case GRASS_PICKED -> new GrassPicked();
            case SAPLING -> new Sapling();
            case SAPLING_PICKED -> new SaplingPicked();
            case CUT_GRASS -> new CutGrassDropped();
            case TWIGS -> new TwigsDropped();
            case CAMPFIRE -> new CampFireBuilt();
            case BOULDER -> new Boulder();
            case BOULDER_FLINTLESS -> new BoulderFlintless();
            case BUSH -> new Bush();
            case BUSH_PICKED -> new BushPicked();
            case EVERGREEN -> new Evergreen();
            case FLOWER -> new Flower();
            case GOLD_VEIN -> new GoldVein();
            case SPIKY_BUSH -> new SpikyBush();
            case SPIKY_BUSH_PICKED -> new SpikyBushPicked();
            case SPIKY_TREE -> new SpikyTree();
            case GOLD -> new GoldDropped();
            case AXE -> new AxeDropped();
            case PICKAXE -> new PickaxeDropped();
            case GARLAND -> new GarlandDropped();
            case PETAL -> new PetalDropped();
            case LOG -> new LogDropped();
            case BERRIES -> new BerriesDropped();
            case ROCK -> new RockDropped();
            case FLINT -> new FlintDropped();
            case CARROT -> new CarrotDropped();
            case CARROT_COOKED -> new CookedCarrotDropped();
            case BERRIES_COOKED -> new CookedBerriesDropped();
            case LUMPY_EVERGREEN -> new LumpyEvergreen();
        };
    }
}
