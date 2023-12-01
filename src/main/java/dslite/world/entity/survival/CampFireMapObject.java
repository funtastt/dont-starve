package dslite.world.entity.survival;

import dslite.player.Player;
import dslite.ui.views.GameView;
import dslite.utils.enums.GameState;
import dslite.utils.enums.MapObjectType;
import dslite.utils.interfaces.Drawable;
import dslite.utils.interfaces.Updatable;
import dslite.world.World;
import dslite.world.entity.MapObject;
import dslite.world.map.Point;
import dslite.world.map.WorldMap;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public final class CampFireMapObject extends MapObject implements Drawable, Updatable {

    private final Point position;
    private final int placedAtDayNum;
    private static final int LIGHT_DIST = 7;

    private final World world = GameView.getWorld();
    private final Player player = world.getPlayer();

    private final List<Point> litTiles;

    public CampFireMapObject() {
        super(MapObjectType.CAMPFIRE);
        position = player.getPos();
        placedAtDayNum = world.getDayCount();

        WorldMap map = world.getMap();
        map.addToUpdatable(this);

        Player player = GameView.getPlayer();
        player.decreaseActions(2);


        litTiles = new ArrayList<>();
        for (int i = position.getX() - LIGHT_DIST; i <= position.getX() + LIGHT_DIST; i++) {
            for (int j = position.getY() - LIGHT_DIST; j <= position.getY() + LIGHT_DIST; j++) {
                if (i > 0 && i < map.getWidth() && j > 0 && j < map.getHeight()
                        && Point.manhattanDist(position, new Point(i, j)) <= LIGHT_DIST) {
                    litTiles.add(new Point(i,j));
                }
            }
        }

    }

    @Override
    public void update() {
        if (world.getDayCount() > placedAtDayNum) {
            Platform.runLater(() -> {
                GameView.getMap().setTileAtPosition(position, getTile().getType());
                GameView.getMap().removeFromUpdatable(this);
            });
        }

        if (world.getGameState() == GameState.NIGHT) {

            //Draws itself
            draw(GameView.getGameScreen().getGraphicsContext2D(), position.getX(), position.getY());

            if (!player.hasCampFireNearby()) {
                if (Point.manhattanDist(position, new Point(player.getPositionX(), player.getPositionY())) <= LIGHT_DIST) {
                    player.setHasCampFireNearby(true);
                }
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc, int i, int j) {
        for (Point p : litTiles) {
            GameView.getGameScreen().drawTileAtPosition(p);
        }
    }

    @Override
    public void interact(Player player) {
    }
}