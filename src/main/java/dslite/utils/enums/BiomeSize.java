package dslite.utils.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum BiomeSize {
    SMALL("Small", 0.025, 50),
    MEDIUM("Medium", 0.01, 100),
    LARGE("Large", 0.005, 150);

    private final String size;
    private final double freq;

    private final int extra;

    private final static Map<String, BiomeSize> sizes = Arrays.stream(BiomeSize.values())
            .collect(Collectors.toMap(k -> k.size, v -> v));

    BiomeSize(String size, double freq, int extra) {
        this.size = size;
        this.freq = freq;
        this.extra = extra;
    }

    public String getSize() {
        return size;
    }

    public static BiomeSize getBiomeSize(String size) {
        return sizes.get(size);
    }

    public double getFreq() {
        return freq;
    }

    public int getExtra() {
        return extra;
    }

    @Override
    public String toString() {
        return size;
    }
}
