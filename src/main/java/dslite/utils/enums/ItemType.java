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
            Texture.SAPLING.getSprite(),
            true
    ),

    ROCK(
            Texture.ROCK.getSprite(),
            Texture.ROCK.getName(),
            Texture.BOULDER.getSprite(),
            true
    ),

    PICKAXE(
            Texture.PICKAXE.getSprite(),
            Texture.PICKAXE.getName(),
            Texture.PICKAXE.getSprite(),
            false
    ),

    BERRIES(
            Texture.BERRIES.getSprite(),
            Texture.BERRIES.getName(),
            Texture.BUSH.getSprite(),
            true
    ),
    CARROT(
            Texture.CARROT.getSprite(),
            Texture.CARROT.getName(),
            Texture.CARROT.getSprite(),
            true
    ),

    LOG(
            Texture.LOG.getSprite(),
            Texture.LOG.getName(),
            Texture.EVERGREEN.getSprite(),
            true
    ),

    PETAL(
            Texture.PETALS.getSprite(),
            Texture.PETALS.getName(),
            Texture.FLOWER.getSprite(),
            true
    ),

    GOLD(
            Texture.GOLD.getSprite(),
            Texture.GOLD.getName(),
            Texture.GOLD.getSprite(),
            true
    ),

    FLINT(
            Texture.FLINT.getSprite(),
            Texture.FLINT.getName(),
            Texture.FLINT.getSprite(),
            true
    ),


    GARLAND(
            Texture.GARLAND.getSprite(),
            Texture.GARLAND.getName(),
            Texture.GARLAND.getSprite(),
            false
    ),

    BERRIES_COOKED(
            Texture.BERRIES_COOKED.getSprite(),
            Texture.BERRIES_COOKED.getName(),
            Texture.BERRIES.getSprite(),
            true
    ),

    CARROT_COOKED(
            Texture.CARROT_COOKED.getSprite(),
            Texture.CARROT_COOKED.getName(),
            Texture.CARROT.getSprite(),
            true
    );

    private final int pickedItemSprite;
    private final String name;
    private final int miningResItemSprite;
    private final boolean isPlaceable;
    private final boolean isStackable;

    ItemType(int itemInInvSprite, String name, int itemOnMapSprite, boolean isStackable) {
        this.pickedItemSprite = itemInInvSprite;
        this.name = name;
        this.miningResItemSprite = itemOnMapSprite;
        this.isStackable = isStackable;
        this.isPlaceable = itemOnMapSprite != -1;
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

