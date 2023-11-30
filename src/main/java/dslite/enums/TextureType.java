package dslite.enums;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Objects;

public enum TextureType {
    WILSON(1, "/dslite/textures/wilson.png");
    private int index;
    private Image textureImage;

    private TextureType(int index, String texturePath) {

        this.index = index;
        this.textureImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(texturePath)));
    }

    public Image getTextureImage() {
        return textureImage;
    }
}