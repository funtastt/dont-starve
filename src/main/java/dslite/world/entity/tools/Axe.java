package dslite.world.entity.tools;

import dslite.utils.enums.ItemType;
import dslite.utils.interfaces.Craftable;

import java.util.Map;

public final class Axe extends Tool implements Craftable {
    public Axe() {
        super(ItemType.AXE, (byte) 10);
    }

    @Override
    public Map<ItemType, Integer> getRequirements() {
        return Map.of(ItemType.TWIGS, 1, ItemType.CUT_GRASS, 1);
    }

}
