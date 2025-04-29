package com.example.va.helper;

public class ExcelHelper {
    public static Number lookup(int lookupValue, int[] lookupVector, int[] resultVector) {
        if (lookupVector.length != resultVector.length) {
            throw new IllegalArgumentException("Lookup vector and result vector must have the same length");
        }

        if (lookupVector.length == 0) {
            return null;
        }

        // Find the largest value less than or equal to lookupValue
        int matchIndex = -1;
        for (int i = 0; i < lookupVector.length; i++) {
            if (lookupVector[i] - lookupValue <= 0) {
                matchIndex = i;
            } else {
                break;
            }
        }

        return matchIndex >= 0 ? resultVector[matchIndex] : null;
    }

    public static <T extends Comparable<T>, R> R lookup(T lookupValue, T[] lookupVector, R[] resultVector) {
        if (lookupVector.length != resultVector.length) {
            throw new IllegalArgumentException("Lookup vector and result vector must have the same length");
        }

        if (lookupVector.length == 0) {
            return null;
        }

        // Find the largest value less than or equal to lookupValue
        int matchIndex = -1;
        for (int i = 0; i < lookupVector.length; i++) {
            if (lookupVector[i].compareTo(lookupValue) <= 0) {
                matchIndex = i;
            } else {
                break; // Since values are sorted, we can stop once we find a larger value
            }
        }

        return matchIndex >= 0 ? resultVector[matchIndex] : null;
    }

    public static <T extends Comparable<T>> Object arrayLookup(T lookupValue, Object[][] lookupArray,
                                                               boolean isRowLookup) {
        if (lookupArray.length == 0 || lookupArray[0].length == 0) {
            return null;
        }

        int matchIndex = -1;

        if (isRowLookup) {
            // First row contains lookup values
            int rowCount = lookupArray.length;
            int colCount = lookupArray[0].length;

            // Find the largest value in first row less than or equal to lookupValue
            for (int i = 0; i < colCount; i++) {
                @SuppressWarnings("unchecked")
                T currentValue = (T) lookupArray[0][i];
                if (currentValue.compareTo(lookupValue) <= 0) {
                    matchIndex = i;
                } else {
                    break; // Since values are sorted, we can stop once we find a larger value
                }
            }

            return matchIndex >= 0 ? lookupArray[rowCount - 1][matchIndex] : null;
        } else {
            // First column contains lookup values
            int colCount = lookupArray[0].length;

            // Find the largest value in first column less than or equal to lookupValue
            for (int i = 0; i < lookupArray.length; i++) {
                @SuppressWarnings("unchecked")
                T currentValue = (T) lookupArray[i][0];
                if (currentValue.compareTo(lookupValue) <= 0) {
                    matchIndex = i;
                } else {
                    break; // Since values are sorted, we can stop once we find a larger value
                }
            }

            return matchIndex >= 0 ? lookupArray[matchIndex][colCount - 1] : null;
        }
    }
}
