package dslite.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum MapSize {
    TINY(128, 128, "Tiny"),
    SMALL(192, 192, "Small"),
    STANDARD(256, 256, "Standard"),
    LARGE(384, 384, "Large"),
    GIANT(512, 512, "Giant");
    private int width, height;
    private String size;
    private final static Map<String, MapSize> sizes = Arrays.stream(MapSize.values())
            .collect(Collectors.toMap(k -> k.size, v -> v));

    MapSize(int width, int height, String size) {
        this.width = width;
        this.height = height;
        this.size = size;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return size;
    }


    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static MapSize getColorByName(String size) {
        return sizes.get(size);
    }
}
