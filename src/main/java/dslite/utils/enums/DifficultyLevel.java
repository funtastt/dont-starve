package dslite.utils.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum DifficultyLevel {
    BEGINNER( 0.6, "Beginner"),
    AMATEUR(0.4, "Amateur"),
    HARDCORE(0.2, "Hardcore");
    private final double freq;
    private final String name;
    private final static Map<String, Double> frequencies = Arrays.stream(DifficultyLevel.values())
            .collect(Collectors.toMap(k -> k.name, v -> v.freq));

    DifficultyLevel(double freq, String name) {
        this.freq = freq;
        this.name = name;
    }

    public double getFreq() {
        return freq;
    }

    public String getName() {
        return name;
    }

    public static double getFrequencyByName(String name) {
        return frequencies.get(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
