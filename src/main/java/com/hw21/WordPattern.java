package com.hw21;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> map = new HashMap<>();
        String[] words = s.split(" ");

        if (pattern.length() != words.length) {
            return false;
        }

        for (int i = 0; i < words.length; i++) {
            char nextChar = pattern.charAt(i);

            if (map.containsKey(nextChar)) {
                if (!words[i].equals(map.get(nextChar))) {
                    return false;
                }
            } else {
                if (map.containsValue(words[i])) {
                    return false;
                }

                map.put(nextChar, words[i]);
            }
        }

        return true;
    }
}