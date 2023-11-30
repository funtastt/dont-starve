package dslite.inventory;

import dslite.utils.enums.ItemType;
import dslite.utils.interfaces.Updatable;
import dslite.player.Player;
import dslite.ui.inventory.InventoryItemRow;
import dslite.world.entity.Item;

import java.util.Arrays;
import java.util.Optional;

public final class Inventory implements Updatable {
    public static final byte MAX_SIZE = 10;
    public static final byte STACK_SIZE = 64;
    private Slot[] slots;
    private Slot selectedSlot;
    private final InventoryItemRow invDisplay;

    public Inventory(Player player) {
        invDisplay = player.getController().getInventory();
        initSlots();
    }

    // Update everything updatable
    @Override
    public void update() {
        for (Slot s : slots) {
            Item item = s.getStoredItem();
            if (item instanceof Updatable) {
                ((Updatable) item).update();
            }
        }
    }

    private void initSlots() {
        slots = new Slot[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            slots[i] = new Slot();
        }
        if (slots.length >= 1) {
            selectedSlot = slots[0];
        }
    }

    public boolean addItem(ItemType itemType, int quantity) {
        int addedItems = 0;
        while (addedItems < quantity) {
            Slot s = availableSlotFor(itemType);
            if (s == null) {
                removeItemByType(itemType, addedItems);
                invDisplay.update();
                return false;
            }

            if (s.getStoredItemType() == null) {
                s.setStoredItem(itemType);
            }
            addedItems += s.add(quantity - addedItems);
        }
        invDisplay.update();
        return true;
    }

    private Slot availableSlotFor(ItemType type) {
        // Finds among existing
        Optional<Slot> s = Arrays.stream(slots)
                .filter(slot -> {
                    if (slot.getStoredItemType() == type) {
                        return !slot.isFull();
                    }
                    return false;
                }).findFirst();

        if (s.isPresent()) return s.get();

        // Creating new one
        s = Arrays.stream(slots)
                .filter(slot -> slot.getStoredItemType() == null)
                .findFirst();

        return s.orElse(null);
    }

    public int removeItemByType(ItemType itemType, int quantity) {
        int itemsLeft = quantity;

        for (Slot s : slots) {
            if (s.getStoredItemType() == itemType) {
                itemsLeft -= s.add(-itemsLeft);
            }
            if (itemsLeft <= 0) {
                invDisplay.update();
                return quantity;
            }
        }
        invDisplay.update();
        return quantity - itemsLeft;
    }


    public Slot getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(int index) {
        if (index < 0) return;
        this.selectedSlot = slots[Math.min(MAX_SIZE - 1, index)];
        invDisplay.update();
    }

    public Slot getSlot(byte index) {
        return slots[index];
    }

    public Slot[] getSlots() {
        return slots;
    }
}
