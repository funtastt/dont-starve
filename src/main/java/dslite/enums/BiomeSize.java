package dslite.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum BiomeSize {
    SMALL("Small", 0.025),
    MEDIUM("Medium", 0.01),
    LARGE("Large", 0.005);

    private final String size;
    private final double freq;

    private final static Map<String, Double> frequencies = Arrays.stream(BiomeSize.values())
            .collect(Collectors.toMap(k -> k.size, v -> v.freq));

    BiomeSize(String size, double freq) {
        this.size = size;
        this.freq = freq;
    }

    public String getSize() {
        return size;
    }

    public static double getFreq(String size) {
        return frequencies.get(size);
    }

    @Override
    public String toString() {
        return size;
    }
}
