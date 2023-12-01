package dslite.player;

import dslite.utils.interfaces.Updatable;
import dslite.ui.tiles.Tile;
import dslite.utils.enums.TileType;
import dslite.utils.enums.GameState;
import dslite.world.World;
import dslite.world.map.Point;
import dslite.world.map.WorldMap;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.stage.Screen;

public final class GameScreen extends Canvas implements Updatable {
    private static final int DEFAULT_GRID_SIZE = 27;
    public static final int COLUMNS_ON_SCREEN_COUNT;
    public static final int ROWS_ON_SCREEN_COUNT;
    private static final double GAME_SCREEN_WIDTH;
    private static final double GAME_SCREEN_HEIGHT;
    private static final boolean DRAW_GRID = true;
    private static final double GRID_THICKNESS = 0.004;
    private static final double singleCellWidth;
    private static final double singleCellHeight;
    private final GraphicsContext gc = getGraphicsContext2D();

    private Player player;
    private Camera camera;
    private World world;
    private WorldMap map;
    private Tile[][] tileMap;

    private static GameScreen instance = null;

    static {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getBounds();
        GAME_SCREEN_WIDTH = bounds.getWidth();
        GAME_SCREEN_HEIGHT = bounds.getHeight();
        double cellSize = Math.min(GAME_SCREEN_WIDTH / DEFAULT_GRID_SIZE, GAME_SCREEN_HEIGHT / DEFAULT_GRID_SIZE);
        singleCellWidth = cellSize;
        singleCellHeight = cellSize;
        COLUMNS_ON_SCREEN_COUNT = (int) (GAME_SCREEN_WIDTH / cellSize);
        ROWS_ON_SCREEN_COUNT = (int) (GAME_SCREEN_HEIGHT / cellSize);
    }

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
        camera.update();
        draw();
    }

    public void draw() {
        gc.setFill(TileType.WATER.getColor());
        gc.fillRect(0, 0, getWidth(), getHeight());

        int xOffset = camera.getxOffset();
        int yOffset = camera.getyOffset();

        for (int i = 0; i < COLUMNS_ON_SCREEN_COUNT; i++) {
            for (int j = 0; j < ROWS_ON_SCREEN_COUNT; j++) {
                int posX = xOffset + i;
                int posY = yOffset + j;
                if (posX > 0 && posX < map.getWidth() && posY > 0 && posY < map.getHeight()) {
                    tileMap[posX][posY].draw(gc, i, j);
                }
            }
        }

        if (DRAW_GRID) drawGrid();

        if (world.getGameState() == GameState.NIGHT) drawNightOverlay();
    }

    public void drawPlayer(Player player) {
        gc.drawImage(Player.PLAYER_IMAGE, player.getPositionX() - camera.getxOffset(),
                player.getPositionY() - camera.getyOffset(), 1.0, 1.0);
    }


    // Draws lines between cells
    private void drawGrid() {
        for (int i = 0; i < COLUMNS_ON_SCREEN_COUNT; i++) {
            gc.strokeLine(i, 0.0, i, ROWS_ON_SCREEN_COUNT);
        }

        for (int i = 0; i < ROWS_ON_SCREEN_COUNT; i++) {
            gc.strokeLine(0.0, i, COLUMNS_ON_SCREEN_COUNT, i);
        }
    }

    public void drawTileAtPosition(Point pos) {
        int posX = pos.getX() - camera.getxOffset();
        int posY = pos.getY() - camera.getyOffset();

        if (posX >= 0 && posX < COLUMNS_ON_SCREEN_COUNT && posY >= 0 && posY < ROWS_ON_SCREEN_COUNT) {
            tileMap[pos.getX()][pos.getY()].draw(gc, posX, posY);

            if (DRAW_GRID) {
                gc.strokeRect(posX, posY, 1.0, 1.0);
            }
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
        this.tileMap = map.getTilemap();
        camera = new Camera(map.getWidth(), map.getHeight(), player);
        draw();
        drawPlayer(player);
    }

    public static GameScreen getInstance() {
        return instance == null ? new GameScreen() : instance;
    }

}