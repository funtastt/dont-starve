package dslite.world.entity.food;


import dslite.utils.enums.ItemType;

public final class Berries extends Food {

    public Berries() {
        super(ItemType.BERRIES, new CookedBerries(), 10.0, -5.0, 1.0);
    }

}
