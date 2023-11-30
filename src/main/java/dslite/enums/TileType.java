package dslite.enums;

import javafx.scene.paint.Color;

public enum TileType {
    WATER(Color.AQUA, true),
    GRASS(Color.YELLOWGREEN, false),
    FOREST(Color.FORESTGREEN, false),
    ROCKY(Color.GAINSBORO, false),
    MARSH(Color.THISTLE, false),
    SAVANNA(Color.GOLD, false),
    SAND(Color.MOCCASIN, false);

    private final Color color;
    private final boolean isSolid;

    TileType(Color color, boolean isSolid) {
        this.color = color;
        this.isSolid = isSolid;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public Color getColor() {
        return color;
    }

}

