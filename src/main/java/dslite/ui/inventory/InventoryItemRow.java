package dslite.ui.inventory;

import dslite.player.inventory.Inventory;
import dslite.player.inventory.Slot;
import dslite.player.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public final class InventoryItemRow extends StackPane {
    private InventoryItemBox[] items;
    private final Player player;
    private final HBox hbox;

    public InventoryItemRow(Player player) {
        super();
        setHeight(100.0);
        setMinHeight(100.0);

        this.player = player;

        hbox = new HBox(10.0);
        hbox.setAlignment(Pos.CENTER);

        getChildren().add(hbox);
        initSlots();
    }

    public void update() {
        Inventory inv = player.getInventory();

        for (int i = 0; i < Inventory.MAX_SLOT_SIZE; i++) {
            Slot currentSlot = inv.getSlots()[i];

            items[i].setItem(currentSlot);

            if (inv.getSelectedSlot() == currentSlot) {
                items[i].getItemCount().setTextFill(Color.RED);
            } else {
                items[i].getItemCount().setTextFill(Color.WHITE);
            }
        }
    }

    private void initSlots() {
        items = new InventoryItemBox[Inventory.MAX_SLOT_SIZE];
        for (int i = 0; i < Inventory.MAX_SLOT_SIZE; i++) {
            hbox.getChildren().add(items[i] = new InventoryItemBox());
        }
    }
}
