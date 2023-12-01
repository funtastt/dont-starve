package dslite.utils.enums;

import java.util.Map;

public enum BiomeType {
    ROCKY_LAND(TileType.ROCKY, Map.of(
            Texture.BOULDER_FLINTLESS.getSprite(), 0.4,
            Texture.BOULDER.getSprite(), 0.3,
            Texture.FLINT.getSprite(), 0.1
    )),
    DESERT(TileType.SAND, Map.of(
            Texture.FLINT.getSprite(), 0.35,
            Texture.BOULDER.getSprite(), 0.4,
            Texture.GOLD_VEIN.getSprite(), 0.25,
            Texture.GOLD.getSprite(), 0.1,
            Texture.ROCK.getSprite(), 0.2
    )),
    SAVANNA(TileType.SAVANNA, Map.of(
            Texture.FLINT.getSprite(), 0.2,
            Texture.CARROT.getSprite(), 0.15,
            Texture.GRASS.getSprite(), 0.75,
            Texture.BOULDER.getSprite(), 0.05,
            Texture.FLOWER.getSprite(), 0.2
    )),
    MARSH(TileType.MARSH, Map.of(
            Texture.SPIKY_TREE.getSprite(), 0.45,
            Texture.SPIKY_BUSH.getSprite(), 0.45
    )),
    GRASSLANDS(TileType.GRASS, Map.of(
            Texture.FLINT.getSprite(), 0.15,
            Texture.CARROT.getSprite(), 0.15,
            Texture.GRASS.getSprite(), 0.6,
            Texture.EVERGREEN.getSprite(), 0.2,
            Texture.SAPLING.getSprite(), 0.25,
            Texture.BUSH.getSprite(), 0.2,
            Texture.BOULDER.getSprite(), 0.05,
            Texture.FLOWER.getSprite(), 0.2
    )),
    FOREST(TileType.FOREST, Map.of(
            Texture.EVERGREEN.getSprite(), 0.5,
            Texture.LUMPY_EVERGREEN.getSprite(), 0.3,
            Texture.FLINT.getSprite(), 0.05,
            Texture.CARROT.getSprite(), 0.05,
            Texture.BUSH.getSprite(), 0.02,
            Texture.GRASS.getSprite(), 0.1,
            Texture.SAPLING.getSprite(), 0.1
    ));

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
