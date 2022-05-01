package com.hw12;

public class JewelsAndStones {
    public int numJewelsInStones(String jewels, String stones) {
        return stones.replaceAll("[^" + jewels + "]", "").length();
    }
}