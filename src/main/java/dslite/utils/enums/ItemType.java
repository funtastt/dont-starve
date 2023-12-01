package dslite.utils.enums;


import javafx.scene.image.Image;

public enum ItemType {
    AXE(
            Texture.AXE.getSprite(),
            Texture.AXE.getName(),
            Texture.AXE.getSprite(),
            false),
    CAMPFIRE(
            Texture.CAMPFIRE.getSprite(),
            Texture.CAMPFIRE.getName(),
            Texture.CAMPFIRE.getSprite(),
            false),
    CUT_GRASS(
            Texture.CUT_GRASS.getSprite(),
            Texture.CUT_GRASS.getName(),
            Texture.GRASS.getSprite(),
            true),

    TWIGS(
            Texture.TWIGS.getSprite(),
            Texture.TWIGS.getName(),
            (Texture.SAPLING.getSprite()),
            true
    );

    private final int pickedItemSprite;
    private final String name;
    private final int miningResItemSprite;
    private final boolean isPlaceable;
    private final boolean isStackable;

    ItemType(int pickedItemSprite, String name, int miningResItemSprite, boolean isStackable) {
        this.pickedItemSprite = pickedItemSprite;
        this.name = name;
        this.miningResItemSprite = miningResItemSprite;
        this.isStackable = isStackable;
        this.isPlaceable = miningResItemSprite != -1;
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


    public int getMiningResItemSprite() {
        return miningResItemSprite;
    }


}

