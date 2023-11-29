package dslite.ui;

import dslite.interfaces.Updatable;
import dslite.player.Player;
import dslite.world.GameState;
import dslite.world.World;
import dslite.world.WorldMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

public final class GameScreen extends Canvas implements Updatable {

    public static final int COLUMNS_ON_SCREEN_COUNT = 25;
    public static final int ROWS_ON_SCREEN_COUNT = 25;
    private static final double GAME_SCREEN_WIDTH = 700.0;
    private static final double GAME_SCREEN_HEIGHT = 700.0;
    private static final boolean DRAW_GRID = true;
    private static final double GRID_THICKNESS = 0.01;
    private static final double singleCellWidth = Math.round(GAME_SCREEN_WIDTH / COLUMNS_ON_SCREEN_COUNT);
    private static final double singleCellHeight = Math.round(GAME_SCREEN_HEIGHT / ROWS_ON_SCREEN_COUNT);
    private final GraphicsContext gc = getGraphicsContext2D();

    private Player player;
    private World world;
    private WorldMap map;

    private static GameScreen instance = null;

    private GameScreen() {
        super(GAME_SCREEN_WIDTH, GAME_SCREEN_HEIGHT);
        prefWidth(GAME_SCREEN_WIDTH);
        prefHeight(GAME_SCREEN_HEIGHT);

        Affine affine = new Affine();
        affine.appendScale(Math.round(getWidth() / COLUMNS_ON_SCREEN_COUNT), Math.round(getHeight() / ROWS_ON_SCREEN_COUNT));
        gc.setTransform(affine);

        gc.setLineWidth(GRID_THICKNESS);
        gc.setStroke(Color.BLACK);
        setOnMouseClicked(mouseEvent -> requestFocus());
        instance = this;
    }

    @Override
    public void update() {
        draw();
    }

    public void draw() {
        //Draw the grid if option set
        if (DRAW_GRID) drawGrid();

        //Make the world dark if it's nighttime
        if (world.getGameState() == GameState.NIGHT) drawNightOverlay();
    }

    public void drawPlayer(Player player) {

    }

    private void drawGrid() {
        for (int i = 0; i < COLUMNS_ON_SCREEN_COUNT; i++) {
            gc.strokeLine(i, 0.0, i, ROWS_ON_SCREEN_COUNT);
        }

        for (int i = 0; i < ROWS_ON_SCREEN_COUNT; i++) {
            gc.strokeLine(0.0, i, COLUMNS_ON_SCREEN_COUNT, i);
        }
    }

    private void drawNightOverlay() {
        gc.setFill(Color.BLACK);
        gc.setGlobalAlpha(0.5);
        gc.fillRect(0, 0, getWidth(), getHeight());
        gc.setGlobalAlpha(1.0);
    }

    public void setWorld(World world) {
        this.world = world;
        this.map = world.getMap();
        this.player = world.getPlayer();
        draw();
        drawPlayer(player);
    }

    public static GameScreen getInstance() {
        return instance == null ? new GameScreen() : instance;
    }

}