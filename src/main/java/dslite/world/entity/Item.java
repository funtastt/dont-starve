package dslite.world.entity;

import dslite.utils.enums.ItemType;

public abstract class Item {
    private final ItemType type;

    public Item(ItemType type) {
        this.type = type;
    }

    public ItemType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
