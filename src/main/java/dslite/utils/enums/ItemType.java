package dslite.utils.enums;

import dslite.world.entity.Item;
import dslite.world.entity.resouces.CutGrass;
import dslite.world.entity.resouces.Twigs;
import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ItemType {
    CUT_GRASS(
            Texture.CUT_GRASS.getSprite(),
            Texture.CUT_GRASS.getName(),
            Texture.GRASS.getSprite(),
            true,
            new CutGrass()),

    TWIGS(
            Texture.TWIGS.getSprite(),
            Texture.TWIGS.getName(),
            (Texture.SAPLING.getSprite()),
            true,
            new Twigs()
    );

    private final int pickedItemSprite;
    private final String name;
    private final int miningResItemSprite;
    private final boolean isPlaceable;
    private final boolean isStackable;
    private final Item item;

    private final static Map<Integer, Item> items = Arrays.stream(ItemType.values())
            .collect(Collectors.toMap(k -> k.miningResItemSprite, v -> v.item));

    ItemType(int pickedItemSprite, String name, int miningResItemSprite, boolean isStackable, Item item) {
        this.pickedItemSprite = pickedItemSprite;
        this.name = name;
        this.miningResItemSprite = miningResItemSprite;
        this.isStackable = isStackable;
        this.isPlaceable = miningResItemSprite != -1;
        this.item = item;
    }

    public String getName() {
        return name;
    }

    public int getPickedItemSprite() {
        return pickedItemSprite;
    }

    public static Image getImage(int sprite) {
        return Texture.getTextureBySprite(sprite);
    }

    public int getObjectIndex() {
        return miningResItemSprite;
    }

    public boolean isPlaceable() {
        return isPlaceable;
    }

    public boolean isStackable() {
        return isStackable;
    }

    public static Item getItemBySprite(int sprite) {
        return items.get(sprite);
    }

    public int getMiningResItemSprite() {
        return miningResItemSprite;
    }


}

