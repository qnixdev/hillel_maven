package com.hw13;

import java.util.Arrays;

public class ArrayManager {
    public int maxProduct(int[] nums) {
        int count = nums.length;
        Arrays.sort(nums);

        return (nums[count - 1] - 1) * (nums[count - 2] - 1);
    }

    public int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }

        Arrays.sort(nums);

        return nums;
    }
}