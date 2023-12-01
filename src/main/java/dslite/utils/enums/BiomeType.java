package dslite.utils.enums;

import java.util.Map;

public enum BiomeType {
    FOREST(TileType.FOREST, Map.of(
            Texture.GRASS.getSprite(), 0.5,
            Texture.SAPLING.getSprite(), 0.5)),
    GRASSLANDS(TileType.GRASS, Map.of(
            Texture.GRASS.getSprite(), 0.5,
            Texture.SAPLING.getSprite(), 0.5)),
    MARSH(TileType.MARSH, Map.of(
            Texture.GRASS.getSprite(), 0.5,
            Texture.SAPLING.getSprite(), 0.5)),
    SAVANNA(TileType.SAVANNA, Map.of(
            Texture.GRASS.getSprite(), 0.5,
            Texture.SAPLING.getSprite(), 0.5)),
    DESERT(TileType.SAND, Map.of(
            Texture.GRASS.getSprite(), 0.5,
            Texture.SAPLING.getSprite(), 0.5)),
    ROCKY_LAND(TileType.ROCKY, Map.of(
            Texture.GRASS.getSprite(), 0.5,
            Texture.SAPLING.getSprite(), 0.5));

    private final TileType type;
    private final Map<Integer, Double> spawnFrequency;

    public static final BiomeType[] VAL = values();
    public static final int LEN = values().length;

    BiomeType(TileType type, Map<Integer, Double> spawnFrequency) {
        this.type = type;
        this.spawnFrequency = spawnFrequency;
    }

    public Map<Integer, Double> getSpawnFrequency() {
        return spawnFrequency;
    }

    public TileType getTileType() {
        return type;
    }

}
