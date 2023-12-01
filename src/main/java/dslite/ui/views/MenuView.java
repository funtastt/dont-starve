package dslite.ui.views;

import dslite.ui.inventory.InventoryItemRow;
import dslite.utils.enums.BiomeSize;
import dslite.utils.enums.DifficultyLevel;
import dslite.utils.enums.MapSize;
import dslite.ui.menu.ArrowMenu;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

import static dslite.world.map.WorldMap.MAX_FREQ;

public final class MenuView {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Rectangle minBtn;

    @FXML
    private HBox wSizeBox;

    @FXML
    private HBox bSizeBox;

    @FXML
    private HBox difficultyBox;

    private static String biomeSize;
    private static int sizeX;
    private static int sizeY;

    private double xOffset;
    private double yOffset;
    private Stage stage;

    private static Stage gameStage;

    @FXML
    private final ObservableList<MapSize> mapSizeOptions = FXCollections.observableArrayList(
            MapSize.values()
    );

    @FXML
    private final ObservableList<BiomeSize> biomeSizeOptions = FXCollections.observableArrayList(
            BiomeSize.values()
    );

    @FXML
    private final ObservableList<DifficultyLevel> difficultyLevelsOptions = FXCollections.observableArrayList(
            DifficultyLevel.values()
    );

    public static void addInventory(InventoryItemRow inventory) {
        gameStage.setScene(new Scene(inventory));
        gameStage.show();
    }

    @FXML
    private void startGame(ActionEvent event) throws IOException {
        gameStage = new Stage(StageStyle.DECORATED);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dslite/gamescreen.fxml"));
        Parent root = fxmlLoader.load();

        gameStage.setScene(new Scene(root));
        setGameStageFullScreen();
        gameStage.setTitle("Don't starve 2D");
        gameStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/dslite/ui/icon.png"))));

        gameStage.setOnHidden(request -> {
            getStage(mainPane).show();
            gameStage.close();
        });
        getStage((Node) event.getSource()).hide();
        gameStage.setFullScreenExitHint("");
        gameStage.setFullScreenExitKeyCombination(KeyCombination.keyCombination("WINDOWS"));
        gameStage.showAndWait();
    }

    public static void setGameStageFullScreen() {
        gameStage.setFullScreen(true);
        gameStage.setResizable(false);
    }

    @FXML
    public void initialize() {
        sizeX = mapSizeOptions.get(0).getWidth();
        sizeY = mapSizeOptions.get(0).getHeight();
        biomeSize = biomeSizeOptions.get(0).toString();
        MAX_FREQ = difficultyLevelsOptions.get(0).getFreq();

        ArrowMenu wSizeMenu = new ArrowMenu();
        ArrowMenu bSizeMenu = new ArrowMenu();
        ArrowMenu difficultyMenu = new ArrowMenu();
        wSizeMenu.getMenuButton().setOptions(mapSizeOptions);
        bSizeMenu.getMenuButton().setOptions(biomeSizeOptions);
        difficultyMenu.getMenuButton().setOptions(difficultyLevelsOptions);

        wSizeMenu.getMenuButton().textProperty().addListener((observableValue, s, newVal) -> {
            sizeX = MapSize.getColorByName(newVal).getWidth();
            sizeY = MapSize.getColorByName(newVal).getHeight();
        });
        bSizeMenu.getMenuButton().textProperty().addListener((observableValue, s, newVal) -> biomeSize = newVal);
        difficultyMenu.getMenuButton().textProperty().addListener((observableValue, s, newVal) -> MAX_FREQ = DifficultyLevel.getFrequencyByName(newVal));

        wSizeBox.getChildren().add(wSizeMenu);
        bSizeBox.getChildren().add(bSizeMenu);
        difficultyBox.getChildren().add(difficultyMenu);
    }

    @FXML
    private void handlePressEvent(MouseEvent event) {
        stage = getStage(mainPane);
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }

    @FXML
    private void handleDragEvent(MouseEvent event) {
        stage = getStage(mainPane);
        if (event.isPrimaryButtonDown()) {
            stage.setX(event.getScreenX() + xOffset);
            stage.setY(event.getScreenY() + yOffset);
        }
    }

    @FXML
    private void minimize() {
        stage = getStage(minBtn);
        stage.setIconified(true);
    }

    @FXML
    private void closeApp() {
        Platform.exit();
    }

    private Stage getStage(Node node) {
        return stage = (Stage) node.getScene().getWindow();
    }

    public static int getSizeX() {
        return sizeX;
    }

    public static int getSizeY() {
        return sizeY;
    }

    public static String getBiomeSize() {
        return biomeSize;
    }
}
