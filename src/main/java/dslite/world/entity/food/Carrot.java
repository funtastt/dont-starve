package dslite.world.entity.food;

import dslite.utils.enums.ItemType;

public final class Carrot extends Food {

    public Carrot() {
        super(ItemType.CARROT, new CookedCarrot(), 10.0, 0.0, 1.0);
    }

}