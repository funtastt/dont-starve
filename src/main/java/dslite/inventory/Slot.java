package dslite.inventory;

import dslite.enums.ItemType;
import dslite.world.entity.Item;
import dslite.world.entity.ItemGenerator;

public final class Slot {
    private Item storedItem;
    private ItemType storedItemType;
    private int itemCount;
    private byte stackSize;
    private boolean full;

    Slot() {
        init();
    }

    void init() {
        stackSize = Inventory.STACK_SIZE;
        storedItem = null;
        storedItemType = null;
        full = false;
        itemCount = 0;
    }

    public int add(int count) {
        int initialSize = getItemCount();
        if (count > 0) {
            itemCount = Math.min(stackSize, itemCount + count);
        } else if (count < 0) {
            itemCount = Math.max(0, itemCount + count);
        }

        update();
        return Math.abs(itemCount - initialSize);
    }

    private void update() {
        if (getItemCount() <= 0) {
            init();
        } else if (getItemCount() >= stackSize) {
            itemCount = stackSize;
            full = true;
        }
    }


    void setStoredItem(ItemType itemType) {
        init();
        storedItemType = itemType;
        storedItem = ItemGenerator.getItem(itemType);
        if (!itemType.isStackable()) {
            stackSize = 1;
        }
    }

    public ItemType getStoredItemType() {
        return storedItemType;
    }

    public Item getStoredItem() {
        return storedItem;
    }

    public int getItemCount() {
        return itemCount;
    }

    public boolean isFull() {
        return full;
    }
}
