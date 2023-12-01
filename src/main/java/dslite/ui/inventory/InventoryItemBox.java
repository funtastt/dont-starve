package dslite.ui.inventory;

import dslite.utils.enums.ItemType;
import dslite.utils.enums.Texture;
import dslite.player.inventory.Slot;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import java.util.Objects;

public final class InventoryItemBox extends StackPane {

    private static final Image BACKGROUND = new Image(Objects.requireNonNull(InventoryItemBox.class.getResourceAsStream("/dslite/ui/inv_bg.png")));
    private static final Font ARIAL = new Font("Arial", 24.0);

    private final ImageView item = new ImageView();
    private final Label itemCount = new Label();

    InventoryItemBox() {
        super(new ImageView(BACKGROUND));
        item.setFitHeight(64.0);
        item.setFitWidth(64.0);
        itemCount.setFont(ARIAL);
        itemCount.setAlignment(Pos.TOP_LEFT);
        getChildren().addAll(item, itemCount);
    }

    void setItem(Slot slot) {
        ItemType type = slot.getStoredItemType();
        if (type != null) {
            item.setImage(Texture.getTextureBySprite(type.getPickedItemSprite()));
            itemCount.setText(String.valueOf(slot.getItemCount()));
        } else {
            item.setImage(null);
            itemCount.setText(null);
        }
    }

    public Label getItemCount() {
        return itemCount;
    }
}
