package dslite.utils.enums;

import java.util.Map;

public enum BiomeType {
    FOREST(TileType.FOREST, Map.of(
            2, 1.0)),
    GRASSLANDS(TileType.GRASS, Map.of(
            2, 1.0)),
    MARSH(TileType.MARSH, Map.of(
            2, 1.0)),
    SAVANNA(TileType.SAVANNA, Map.of(
            2, 1.0)),
    DESERT(TileType.SAND, Map.of(
            2, 1.0)),
    ROCKYLAND(TileType.ROCKY, Map.of(
            2, 1.0));

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
