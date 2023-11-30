package dslite.enums;

import java.util.Map;

public enum BiomeType {
    FOREST(TileType.FOREST, Map.of(
            1, 0.5,
            11, 0.3,
            15, 0.05,
            14, 0.05,
            5, 0.02,
            4, 0.1,
            2, 0.1,
            3, 0.1)),
    GRASSLANDS(TileType.GRASS, Map.of(
            15, 0.15,
            14, 0.15,
            4, 0.6,
            1, 0.2,
            2, 0.25,
            5, 0.2,
            6, 0.05,
            3, 0.03)),
    MARSH(TileType.MARSH, Map.of(
            9, 0.45,
            10, 0.2)),
    SAVANNA(TileType.SAVANNA, Map.of(
            15, 0.2,
            14, 0.15,
            4, 0.75,
            6, 0.05,
            3, 0.05)),
    DESERT(TileType.SAND, Map.of(
            8, 0.3,
            6, 0.2,
            15, 0.05,
            4, 0.1)),
    ROCKYLAND(TileType.ROCKY, Map.of(
            15, 0.35,
            6, 0.4,
            7, 0.25,
            13, 0.1,
            12, 0.2));

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
