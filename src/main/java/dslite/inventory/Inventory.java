package dslite.inventory;

import dslite.interfaces.Updatable;
import dslite.player.Player;
import dslite.ui.inventory.InventoryItemRow;
import dslite.world.entity.Item;

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
