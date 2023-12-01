package dslite.world.entity.tools;

import dslite.utils.enums.ItemType;
import dslite.utils.interfaces.Craftable;

import java.util.Map;

public final class Pickaxe extends Tool implements Craftable {

    public Pickaxe() {
        super(ItemType.PICKAXE, (byte) 7);
    }

    @Override
    public Map<ItemType, Integer> getRequirements() {
        return Map.of(ItemType.TWIGS, 2, ItemType.FLINT, 2);
    }

}
