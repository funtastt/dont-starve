package dslite.world.entity.survival;

import dslite.utils.enums.ItemType;
import dslite.utils.interfaces.Craftable;
import dslite.world.entity.Item;

import java.util.Map;

public final class CampFire extends Item implements Craftable {

    public CampFire() {
        super(ItemType.CAMPFIRE);
    }

    public Map<ItemType, Integer> getRequirements() {
        return Map.of(ItemType.CUT_GRASS, 10, ItemType.TWIGS, 6);
    }

}