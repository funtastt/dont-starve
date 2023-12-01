package dslite.utils.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ObjectType {
    GRASS(Texture.GRASS.getSprite()),
    GRASS_PICKED(Texture.GRASS_PICKED.getSprite()),

    SAPLING(Texture.SAPLING.getSprite()),
    SAPLING_PICKED(Texture.SAPLING_PICKED.getSprite());
    private final int sprite;
    private final static Map<Integer, ObjectType> objectTypes = Arrays.stream(ObjectType.values())
            .collect(Collectors.toMap(ObjectType::getSprite, v -> v));

    ObjectType(int sprite) {
        this.sprite = sprite;
    }

    public static ObjectType getBySprite(int sprite) {
        return objectTypes.get(sprite);
    }

    public int getSprite() {
        return sprite;
    }
}