package dslite.utils.enums;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public enum Texture {
    WILSON(1, "Wilson", "/dslite/textures/characters/wilson.png"),

    // GRASS
    GRASS(2, "Grass", "/dslite/textures/mining/grass.png"),
    CUT_GRASS(3, "Cut grass", "/dslite/textures/resources/cut_grass.png"),
    GRASS_PICKED(4, "Grass picked", "/dslite/textures/mining/grass_picked.png"),

    // TWIGS
    SAPLING(5, "Sapling", "/dslite/textures/mining/sapling.png"),
    SAPLING_PICKED(6, "Sapling picked", "/dslite/textures/mining/sapling_picked.png"),
    TWIGS(7, "Twigs", "/dslite/textures/resources/twigs.png"),

    AXE(8, "Axe", "/dslite/textures/tools/axe.png"),
    CAMPFIRE(9, "Campfire", "/dslite/textures/survival/campfire.png"),
    BOULDER(10, "Boulder", "/dslite/textures/mining/boulder.png"),
    PICKAXE(11, "Pickaxe", "/dslite/textures/tools/pickaxe.png"),
    BOULDER_FLINTLESS(12, "Boulder without flint", "/dslite/textures/mining/boulder_flintless.png"),
    BUSH(13, "Bush", "/dslite/textures/mining/bush.png"),
    BUSH_PICKED(14, "Bush", "/dslite/textures/mining/bush_picked.png"),
    EVERGREEN(15, "Evergreen", "/dslite/textures/mining/evergreen.png"),
    FLOWER(16, "Flower", "/dslite/textures/mining/flower.png"),
    GOLD_VEIN(17, "Gold vein", "/dslite/textures/mining/goldvein.png"),
    SPIKY_BUSH(18, "Spiky bush", "/dslite/textures/mining/spikybush.png"),
    SPIKY_BUSH_PICKED(19, "Spiky bush picked", "/dslite/textures/mining/spikybush_picked.png"),
    SPIKY_TREE(20, "Spiky tree", "/dslite/textures/mining/spikytree.png"),
    GOLD(21, "Gold", "/dslite/textures/resources/gold.png"),
    LOG(22, "Log", "/dslite/textures/resources/log.png"),
    PETALS(24, "Petals", "/dslite/textures/resources/petals.png"),
    ROCK(25, "Rock", "/dslite/textures/resources/rocks.png"),
    GARLAND(26, "Garland", "/dslite/textures/survival/garland.png"),

    BERRIES(27, "Berries", "/dslite/textures/food/berries.png"),

    BERRIES_COOKED(28, "Berries cooked", "/dslite/textures/food/berries_cooked.png"),
    FLINT(29, "Flint", "/dslite/textures/resources/flint.png"),
    LUMPY_EVERGREEN(30, "Lumpy evergreen", "/dslite/textures/mining/lumpy_evergreen.png"),
    CARROT(31, "Carrot", "/dslite/textures/food/carrot.png"),
    CARROT_COOKED(32, "Carrot cooked", "/dslite/textures/food/carrot_cooked.png"),
    ;
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