package dslite.world.map;

import dslite.GameLauncher;
import dslite.controllers.GameController;
import dslite.controllers.MenuController;
import dslite.enums.TextureType;
import dslite.ui.tiles.Tile;
import dslite.enums.TileType;
import dslite.enums.BiomeType;

import java.util.*;

public final class WorldMap {
    private final int width;
    private final int height;

    private Point spawnPoint;

    private Tile[][] tileMap;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;

        generateMap();
    }

    public void generateMap() {
        List<Biome> biomes = new ArrayList<>();
        Set<Point> biomePoints = new HashSet<>();

        int biomeNum = 0;
        switch (MenuController.getBiomeSize()) {
            case "Small" -> biomeNum = (int) (width * width * 0.025);
            case "Medium" -> biomeNum = (int) (width * width * 0.01);
            case "Large" -> biomeNum = (int) (width * width * 0.005);
        }

        biomeNum += GameLauncher.RAND.nextInt(100);

        while (biomePoints.size() < biomeNum) {
            biomePoints.add(new Point(GameLauncher.RAND.nextInt(width - 1), GameLauncher.RAND.nextInt(height - 1)));
        }

        for (Point p : biomePoints) {
            biomes.add(new Biome(BiomeType.VAL[GameLauncher.RAND.nextInt(BiomeType.LEN)], p.getX(), p.getY()));
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
        makeTilemap(biomes);
        createSpawnPoint(biomes);

        biomePoints = null;
        biomes = null;
        System.gc();
    }

    public void update() {
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
                            .filter(tile -> !tile.getValue().getType().isSolid()).findFirst();
            if (entry.isPresent()) {
                setSpawnPoint(entry.get().getKey());
                return;
            }
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
}