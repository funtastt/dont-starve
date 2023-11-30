package dslite.utils.enums;

import javafx.scene.paint.Color;

public enum TileType {
    WATER(Color.AQUA, false),
    GRASS(Color.YELLOWGREEN, true),
    FOREST(Color.FORESTGREEN, true),
    ROCKY(Color.GAINSBORO, true),
    MARSH(Color.THISTLE, true),
    SAVANNA(Color.GOLD, true),
    SAND(Color.MOCCASIN, true);

    private final Color color;
    private final boolean isPassable;

    TileType(Color color, boolean isPassable) {
        this.color = color;
        this.isPassable = isPassable;
    }

    public boolean isPassable() {
        return isPassable;
    }

    public Color getColor() {
        return color;
    }

}

