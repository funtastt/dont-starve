package dslite.utils.interfaces;

import dslite.utils.enums.ItemType;

import java.util.Map;

public interface Craftable {
    Map<ItemType, Integer> getRequirements();
}
