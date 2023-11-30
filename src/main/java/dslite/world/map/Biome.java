package dslite.world.map;

import dslite.enums.BiomeType;
import dslite.ui.tiles.Tile;
import dslite.enums.TileType;

import java.util.HashMap;

public final class Biome {

    private TileType tileType;
    private final BiomeType biomeType;

    private final Point base;
    private final HashMap<Point, Tile> tiles;

    public Biome(BiomeType type, int x, int y) {
        this.biomeType = type;
        this.tileType = type.getTileType();
        this.base = new Point(x, y);
        this.tiles = new HashMap<>();
    }

    public void addToBiome(Tile t, int x, int y) {
        tiles.put(new Point(x, y), t);
    }

    public static void setTileType(Biome biome, TileType tileType) {
        biome.tiles.forEach((point, tile) -> tile.setType(tileType));
        biome.tileType = tileType;
    }

    public HashMap<Point, Tile> getTiles() {
        return tiles;
    }

    public BiomeType getBiomeType() {
        return biomeType;
    }

    public TileType getTileType() {
        return tileType;
    }

    public Point getBase() {
        return base;
    }
}
