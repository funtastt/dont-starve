package dslite.world.entity.food;

import dslite.utils.enums.ItemType;

public final class CookedCarrot extends Food {
    public CookedCarrot() {
        super(ItemType.CARROT_COOKED, null, 8.0f, 2.0f, 3.0f);
    }
}
