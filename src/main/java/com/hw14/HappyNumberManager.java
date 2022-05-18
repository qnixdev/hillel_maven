package com.hw14;

import java.util.HashSet;
import java.util.Set;

public class HappyNumberManager {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();

        int response = 0;

        while (set.add(n)) {
            if (n == 1) {
                return true;
            }

            while (n > 0) {
                int d = n % 10;
                response += d * d;
                n /= 10;
            }

            n = response;
            response = 0;
        }

        return false;
    }
}