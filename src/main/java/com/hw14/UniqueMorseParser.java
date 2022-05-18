package com.hw14;

import java.util.HashSet;
import java.util.Set;

public class UniqueMorseParser {
    private final String[] morseCode = new String[] {
        ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
        "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-",
        "..-", "...-", ".--", "-..-", "-.--", "--.."
    };

    public int uniqueMorseRepresentations(String[] words) {
        Set<String> set = new HashSet<>();

        for (String word : words) {
            StringBuilder sb = new StringBuilder();

            for (char letter : word.toCharArray()) {
                sb.append(morseCode[letter - 'a']);
            }

            set.add(sb.toString());
        }

        return set.size();
    }
}