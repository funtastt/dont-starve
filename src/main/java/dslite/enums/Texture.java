package dslite.enums;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Texture {
    WILSON(1, "Wilson", "/dslite/textures/characters/wilson.png"),
    GRASS(2, "Grass", "/dslite/textures/mining_res/grass.png"),
    CUT_GRASS(3, "Cut grass", "/dslite/textures/mining_res/cut_grass.png");
    private final Image textureImage;
    private final int sprite;
    private final String name;

    private final static Map<Integer, Image> textures = Arrays.stream(Texture.values())
            .collect(Collectors.toMap(Texture::getSprite, v -> v.textureImage));

    Texture(int sprite, String name, String texturePath) {
        this.sprite = sprite;
        this.name = name;
        this.textureImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
    }

    public Image getTextureImage() {
        return textureImage;
    }

    public int getSprite() {
        return sprite;
    }

    public String getName() {
        return name;
    }

    public static Image getTextureBySprite(int sprite) {
        return textures.get(sprite);
    }
}