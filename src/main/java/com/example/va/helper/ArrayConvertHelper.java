package com.example.va.helper;

import java.util.Arrays;

public class ArrayConvertHelper {
    public static int[] convertFromString(String str) {
        String[] stringValues = str
                .replace("[", "")
                .replace("]", "")
                .split(",\\s*"); // Split by comma followed by optional whitespace

        return Arrays.stream(stringValues)
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
