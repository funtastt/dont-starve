package dslite.enums;

public enum BiomeSize {
    SMALL("Small"),
    MEDIUM("Medium"),
    LARGE("Large");

    private final String size;

    BiomeSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }
}
