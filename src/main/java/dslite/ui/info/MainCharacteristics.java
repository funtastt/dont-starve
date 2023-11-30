package dslite.ui.info;

import dslite.controllers.GameController;
import dslite.player.Player;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public final class MainCharacteristics extends VBox {

    private final StatisticsBox health;
    private final StatisticsBox hunger;
    private final StatisticsBox sanity;
    private final ElementaryStatisticsBox ap;
    private final Player player;

    public MainCharacteristics() {
        super(20.0);
        player = GameController.getPlayer();

        setPrefWidth(300.0);
        setPrefHeight(GameController.getGameScreen().getHeight());
        setMinHeight(200.0);
        setAlignment(Pos.TOP_LEFT);

        health = new StatisticsBox("Health: ", Player.MAX_HEALTH);
        hunger = new StatisticsBox("Hunger: ", Player.MAX_HUNGER);
        sanity = new StatisticsBox("Sanity: ", Player.MAX_SANITY);
        ap = new ElementaryStatisticsBox("Actions left: ");

        update();
        getChildren().addAll(health, hunger, sanity, ap);

    }

    public void update() {
        setHealth(player.getHealth());
        setHunger(player.getHunger());
        setSanity(player.getSanity());
        setActionPoints(player.getActions());
    }

    private void setHealth(double val) {
        health.setValue(val);
    }

    private void setHunger(double val) {
        hunger.setValue(val);
    }

    private void setSanity(double val) {
        sanity.setValue(val);
    }

    private void setActionPoints(double val) {
        ap.setValue(val);
    }

}