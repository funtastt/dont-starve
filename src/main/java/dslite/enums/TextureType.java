package dslite.enums;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public enum TextureType {
    WILSON(1, "/dslite/textures/characters/wilson.png"),
    GRASS(2, "/dslite/textures/mining_res/grass.png");
    private final Image textureImage;
    private final int sprite;

    private final static Map<Integer, Image> textures = Arrays.stream(TextureType.values())
            .collect(Collectors.toMap(TextureType::getSprite, v -> v.textureImage));

    private TextureType(int sprite, String texturePath) {
        this.sprite = sprite;
        this.textureImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
    }

    public Image getTextureImage() {
        return textureImage;
    }

    public int getSprite() {
        return sprite;
    }

    public static Image getTextureBySprite(int sprite) {
        return textures.get(sprite);
    }
}