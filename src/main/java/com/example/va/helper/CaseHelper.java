package com.example.va.helper;

public class CaseHelper {
    public static String toCamelCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        // Split the input string by spaces
        String[] words = input.split(" ");
        StringBuilder result = new StringBuilder();

        // Add the first word in lowercase
        if (words.length > 0) {
            result.append(words[0].toLowerCase());
        }

        // Add the rest of the words with first letter capitalized
        for (int i = 1; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                result.append(Character.toUpperCase(words[i].charAt(0)));
                result.append(words[i].substring(1).toLowerCase());
            }
        }

        return result.toString();
    }
}
