package dslite.utils.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum MapObjectType {
    GRASS(Texture.GRASS.getSprite()),
    GRASS_PICKED(Texture.GRASS_PICKED.getSprite()),

    SAPLING(Texture.SAPLING.getSprite()),
    SAPLING_PICKED(Texture.SAPLING_PICKED.getSprite()),
    CUT_GRASS(Texture.CUT_GRASS.getSprite()),
    TWIGS(Texture.TWIGS.getSprite()),
    CAMPFIRE(Texture.CAMPFIRE.getSprite()),
    BOULDER(Texture.BOULDER.getSprite()),
    BOULDER_FLINTLESS(Texture.BOULDER_FLINTLESS.getSprite()),
    BUSH(Texture.BUSH.getSprite()),
    BUSH_PICKED(Texture.BUSH_PICKED.getSprite()),
    EVERGREEN(Texture.EVERGREEN.getSprite()),
    FLOWER(Texture.FLOWER.getSprite()),
    GOLD_VEIN(Texture.GOLD_VEIN.getSprite()),
    SPIKY_BUSH(Texture.SPIKY_BUSH.getSprite()),
    SPIKY_BUSH_PICKED(Texture.SPIKY_BUSH_PICKED.getSprite()),
    SPIKY_TREE(Texture.SPIKY_TREE.getSprite()),
    GOLD(Texture.GOLD.getSprite()),
    AXE(Texture.AXE.getSprite()),
    PICKAXE(Texture.PICKAXE.getSprite()),
    GARLAND(Texture.GARLAND.getSprite()),
    PETAL(Texture.PETALS.getSprite()),
    LOG(Texture.LOG.getSprite()),
    BERRIES(Texture.BERRIES.getSprite()),
    ROCK(Texture.ROCK.getSprite()),
    FLINT(Texture.FLINT.getSprite()),
    CARROT(Texture.CARROT.getSprite()),
    CARROT_COOKED(Texture.CARROT_COOKED.getSprite()),
    BERRIES_COOKED(Texture.BERRIES_COOKED.getSprite()),
    LUMPY_EVERGREEN(Texture.LUMPY_EVERGREEN.getSprite());
    private final int sprite;
    private final static Map<Integer, MapObjectType> objectTypes = Arrays.stream(MapObjectType.values())
            .collect(Collectors.toMap(MapObjectType::getSprite, v -> v));

    MapObjectType(int sprite) {
        this.sprite = sprite;
    }

    public static MapObjectType getBySprite(int sprite) {
        return objectTypes.get(sprite);
    }

    public int getSprite() {
        return sprite;
    }
}