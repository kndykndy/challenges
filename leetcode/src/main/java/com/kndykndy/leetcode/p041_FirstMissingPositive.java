package com.kndykndy.leetcode;

import com.kndykndy.leetcode.util.Utils;
import java.util.PriorityQueue;

public class p041_FirstMissingPositive {

    interface Solution {

        /**
         * Your algorithm should run in O(n) time and uses constant extra space.
         */
        int firstMissingPositive(int[] nums);
    }

    /**
     * Falls out the space complexity.
     */
    public static class Solution1 implements Solution {

        @Override
        public int firstMissingPositive(int[] nums) {
            final PriorityQueue<Integer> q = new PriorityQueue<>();

            for (int num : nums) {
                if (num > 0) {
                    q.add(num);
                }
            }

            if (q.isEmpty()) {
                return 1;
            }

            int result = 1;
            while (!q.isEmpty()) {
                Integer peek = q.peek();
                if (peek > result) {
                    return result;
                } else {
                    result = q.poll() + 1;
                }
            }
            return result;
        }
    }

    /**
     * Not mine :(
     */
    public static class OptimalSolution implements Solution {

        @Override
        public int firstMissingPositive(int[] nums) {
            Utils.printIntArray(nums, "initial");

            int n = nums.length;

            // 1. mark numbers (num < 0) and (num > n) with a special marker number (n+1)
            // (we can ignore those because if all number are > n then we'll simply return 1)
            for (int i = 0; i < n; i++) {
                if (nums[i] <= 0 || nums[i] > n) {
                    nums[i] = n + 1;
                }
            }
            Utils.printIntArray(nums, "mark out extra");
            // note: all number in the array are now positive, and on the range 1..n+1

            // 2. mark each cell appearing in the array, by converting the index for that number to
            // negative
            for (int i = 0; i < n; i++) {
                int num = Math.abs(nums[i]);
                if (num > n) {
                    continue;
                }
                num--; // -1 for zero index based array (so the number 1 will be at pos 0)
                if (nums[num] > 0) { // prevents double negative operations
                    nums[num] = -1 * nums[num];
                }
            }
            Utils.printIntArray(nums, "identify existing");

            // 3. find the first cell which isn't negative (doesn't appear in the array)
            for (int i = 0; i < n; i++) {
                if (nums[i] >= 0) {
                    return i + 1;
                }
            }

            // 4. no positive numbers were found, which means the array contains all numbers 1..n
            return n + 1;
        }
    }
}
