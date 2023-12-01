package dslite.ui.crafting;


import dslite.player.Player;
import dslite.utils.enums.ItemType;
import dslite.views.GameView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public final class CraftingView extends VBox {
    ItemType selectedType;

    private TabPane tabPane;
    private Button craftBtn;

    public CraftingView() {
        super();
        initTabPane();
        initCraftBtn();

        craftBtn.setOnAction(actionEvent -> {
            Player player = GameView.getPlayer();
            player.craft(selectedType);
        });

        setPrefHeight(700.0);
        setFocusTraversable(false);
        getChildren().addAll(tabPane, new StackPane(craftBtn));
    }

    private void initTabPane() {
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setPrefWidth(300.0);
        tabPane.setPrefHeight(GameView.getGameScreen().getHeight());
        tabPane.setFocusTraversable(false);
        tabPane.setSide(Side.LEFT);

        ObservableList<ItemType> tools = FXCollections.observableArrayList(
                ItemType.AXE
        );
        Tab toolsTab = new Tab("Tools", new ItemList(tools, this));

        ObservableList<ItemType> survival = FXCollections.observableArrayList(
                ItemType.CAMPFIRE
        );
        Tab survivalTab = new Tab("Survival", new ItemList(survival, this));
        tabPane.getTabs().addAll(toolsTab, survivalTab);
    }

    private void initCraftBtn() {
        craftBtn = new Button("Craft");
        craftBtn.setPrefWidth(300.0);
        craftBtn.setFocusTraversable(false);
        craftBtn.setDisable(true);
        craftBtn.setOnAction(actionEvent -> setCraftBtn(GameView.getPlayer().canCraft(selectedType)));
    }


    public void update() {
        if (selectedType != null) {
            setCraftBtn(GameView.getPlayer().canCraft(selectedType));
        }
    }

    private void setCraftBtn(boolean val) {
        craftBtn.setDisable(!val);
    }

}
