package com.mipo.test.test2;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Solution
 * @Description two sum的解题
 * @Date 2020/10/20 15:21
 * @Created by li.yy
 */
public class Solution {

    public static void main(String[] args) {
        int num [] = {2, 7, 11, 15};
        int target = 9;
        int[] ints = twoSum(num, target);
        for (int i = 0; i < ints.length; i++) {
            System.out.println(ints[i]);
        }
    }

    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement)) {
                return new int[] {map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
