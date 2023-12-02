package dslite.world.map;

import dslite.GameLauncher;
import dslite.utils.enums.*;
import dslite.ui.tiles.TileWithObject;
import dslite.controllers.GameController;
import dslite.controllers.MenuController;
import dslite.ui.tiles.Tile;
import dslite.utils.interfaces.Updatable;
import dslite.world.entity.MapObject;

import java.util.*;

public final class WorldMap {
    private final int width;
    private final int height;
    private Point spawnPoint;

    private Tile[][] tileMap;

    private List<MapObject> updatableObjects;

    public static double MAX_FREQ = DifficultyLevel.AMATEUR.getFreq();

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.updatableObjects = new ArrayList<>();

        generateMap();
    }

    public void generateMap() {
        List<Biome> biomes = new ArrayList<>();
        Set<Point> biomePoints = new HashSet<>();

        BiomeSize size = BiomeSize.getBiomeSize(MenuController.getBiomeSize());
        int biomeNum = (int) (size.getFreq() * width * height) + GameLauncher.RANDOM.nextInt(size.getExtra());

        while (biomePoints.size() < biomeNum) {
            biomePoints.add(new Point(GameLauncher.RANDOM.nextInt(width - 1), GameLauncher.RANDOM.nextInt(height - 1)));
        }

        for (Point p : biomePoints) {
            biomes.add(new Biome(BiomeType.VAL[GameLauncher.RANDOM.nextInt(BiomeType.LEN)], p.getX(), p.getY()));
        }

        for (int i = 0; i < width - 1; i++) {
            for (int j = 0; j < height - 1; j++) {
                Biome nearestBiome = null;
                int dist = Integer.MAX_VALUE;

                for (Biome b : biomes) {
                    int euclideanDist = Point.euclideanDist(b.getBase(), new Point(i, j));
                    if (euclideanDist < dist) {
                        nearestBiome = b;
                        dist = euclideanDist;
                    }
                }
                assert nearestBiome != null;
                nearestBiome.addToBiome(new Tile(nearestBiome.getTileType()), i, j);
            }
        }

        initTilemap(TileType.WATER);
        chunkBorders(biomes);
        fillWithResources(biomes);
        makeTilemap(biomes);
        createSpawnPoint(biomes);

        biomePoints = null;
        biomes = null;
        System.gc();
    }

    public void update() {
        for (MapObject obj : updatableObjects) {
            if (obj instanceof Updatable) {
                ((Updatable) obj).update();
            }
        }
        GameController.getGameScreen().drawPlayer(GameController.getPlayer());
    }

    private void initTilemap(TileType type) {
        tileMap = new Tile[width][height];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tileMap[i][j] = new Tile(type);
            }
        }
    }

    private void makeTilemap(List<Biome> biomes) {
        for (Biome b : biomes) {
            b.getTiles().forEach((point, tile) ->
                    tileMap[point.getX()][point.getY()] = tile);
        }
    }

    private void chunkBorders(List<Biome> biomes) {
        biomes.stream().filter(biome -> biome
                .getTiles()
                .keySet()
                .stream().anyMatch(point -> {
                    int x = point.getX();
                    int y = point.getY();
                    return x == width - 2 || x == 0 || y == height - 2 || y == 0;
                })).forEach(border -> Biome.setTileType(border, TileType.WATER));
        biomes.removeIf(biome -> biome.getTileType().equals(TileType.WATER));
    }

    private void createSpawnPoint(List<Biome> biomes) {
        for (Biome b : biomes) {
            Optional<Map.Entry<Point, Tile>> entry =
                    b.getTiles()
                            .entrySet()
                            .stream()
                            .filter(tile -> tile.getValue().getType().isPassable()).findFirst();
            if (entry.isPresent()) {
                setSpawnPoint(entry.get().getKey());
                return;
            }
        }
    }

    private void fillWithResources(List<Biome> biomes) {
        for (Biome b : biomes) {
            Map<Integer, Double> frequencies = b.getBiomeType().getSpawnFrequency();

            b.getTiles().replaceAll((point, tile) -> {
                if (GameLauncher.RANDOM.nextFloat() > MAX_FREQ) {
                    return tile;
                }

                Optional<Map.Entry<Integer, Double>> newTile = frequencies
                        .entrySet()
                        .stream()
                        .filter(entry -> GameLauncher.RANDOM.nextFloat() <= entry.getValue())
                        .findAny();

                if (newTile.isPresent()) {
                    int objIndex = newTile.get().getKey();
                    return new TileWithObject(b.getTileType(), point, objIndex);
                }

                return tile;
            });
        }
    }


    public void setTileAtPosition(Point p, TileType type) {
        tileMap[p.getX()][p.getY()] = new Tile(type);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnPoint(Point spawnPoint) {
        this.spawnPoint = spawnPoint;
    }

    public Tile[][] getTilemap() {
        return tileMap;
    }

    public void addToUpdatable(MapObject obj) {
        updatableObjects.add(obj);
    }

    public void removeFromUpdatable(MapObject obj) {
        updatableObjects.remove(obj);
    }
}