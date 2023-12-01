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
    CAMPFIRE(Texture.CAMPFIRE.getSprite());
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