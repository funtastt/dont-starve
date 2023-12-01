package dslite.player.inventory;

import dslite.utils.enums.ItemType;
import dslite.utils.interfaces.Craftable;
import dslite.utils.interfaces.Updatable;
import dslite.player.Player;
import dslite.ui.inventory.InventoryItemRow;
import dslite.world.entity.Item;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public final class Inventory implements Updatable {
    public static final byte MAX_SLOT_SIZE = 10;
    public static final byte MAX_STACK_SIZE = 64;
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
        slots = new Slot[MAX_SLOT_SIZE];
        for (int i = 0; i < MAX_SLOT_SIZE; i++) {
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
        for (Slot s : slots) {
            if (s.getStoredItemType() == type & !s.isFull()) return s;
            if (s.getStoredItemType() == null) return s;
        }
        return null;
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

    public void removeSlot(Slot slot) {
        for (Slot s : slots) {
            if (s == slot) {
                slot.init();
                invDisplay.update();
                return;
            }
        }
        invDisplay.update();
    }

    public Slot getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(int index) {
        if (index < 0) return;
        this.selectedSlot = slots[Math.min(MAX_SLOT_SIZE - 1, index)];
        invDisplay.update();
    }

    public Slot getSlot(byte index) {
        return slots[index];
    }

    public Slot[] getSlots() {
        return slots;
    }

    public boolean tryToCraft(Item item, boolean craft) {
        if (!(item instanceof Craftable)) return false;

        Map<ItemType, Integer> requirements = ((Craftable) item).getRequirements();

        int possibleSlots = (int) requirements
                .entrySet().stream()
                .filter(entry -> getItemCountFor(entry.getKey()) >= entry.getValue())
                .count();

        if (possibleSlots >= requirements.entrySet().size()) {
            if (!craft) return true;
            requirements.forEach((itemType, integer) -> {
                removeItemByType(itemType, integer);
                invDisplay.update();
            });

            if (availableSlotFor(item.getType()) == null) {
                requirements.forEach(this::addItem);
                return false;
            } else {
                addItem(item.getType(), 1);
                return true;
            }
        }
        return false;

    }

    public int getItemCountFor(ItemType type) {
        int count = 0;
        for (Slot s : slots) {
            if (s.getStoredItemType() == type) {
                count += s.getItemCount();
            }
        }
        return count;
    }

    public void removeItem(Item item) {
        for (Slot s : slots) {
            if (s.getStoredItem() == item) {
                s.init();
            }
        }
        invDisplay.update();
    }
}
