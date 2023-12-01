package dslite.ui.crafting;

import dslite.utils.enums.ItemType;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public final class ItemList extends ListView<ItemType> {

    ItemList(ObservableList<ItemType> items, CraftingView cw) {
        super();
        setCellFactory(listElement -> new ListElement());
        setFocusTraversable(false);
        getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            cw.selectedType = newVal;
            cw.update();
        });
        getItems().addAll(items);
    }

}
