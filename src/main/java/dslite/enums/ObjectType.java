package dslite.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ObjectType {
    GRASS(2);
    private final int sprite;
    private final static Map<Integer, ObjectType> sprites = Arrays.stream(ObjectType.values())
            .collect(Collectors.toMap(ObjectType::getSprite, v -> v));

    ObjectType(int sprite) {
        this.sprite = sprite;
    }

    public static ObjectType getByIndex(int index) {
        return sprites.get(index);
    }

    public int getSprite() {
        return sprite;
    }
}