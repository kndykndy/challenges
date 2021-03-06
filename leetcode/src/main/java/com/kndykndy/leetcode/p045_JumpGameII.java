package com.kndykndy.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class p045_JumpGameII {

    interface Solution {

        int jump(int[] nums);
    }

    public static class BruteForceSolution implements Solution {

        @Override
        public int jump(int[] nums) {
            if (nums == null || nums.length == 1) {
                return 0;
            }

            final Set<Integer> jumps = new HashSet<>(); // successful jumps

            for (int currentPos = nums[0]; currentPos > 0; currentPos--) {
                jumpImpl(nums, currentPos, jumps, 1);
            }

            return !jumps.isEmpty() ? Collections.min(jumps) : 0;
        }

        private void jumpImpl(int[] nums, int currentPos, Set<Integer> jumps, int jumpNumber) {
            if (currentPos > nums.length - 1) { // outside
                return;
            } else if (currentPos == nums.length - 1) {
                jumps.add(jumpNumber);
                return;
            }

            for (int i = nums[currentPos]; i > 0; i--) {
                jumpImpl(nums, currentPos + i, jumps, jumpNumber + 1);
            }
        }
    }

    public static class DpMemoizationSolution implements Solution {

        @Override
        public int jump(int[] nums) {
            if (nums.length == 1) {
                return 0;
            }

            final int[] waysToGet = new int[nums.length];
            Arrays.fill(waysToGet, Integer.MAX_VALUE);
            waysToGet[0] = 0;

            for (int i = nums[0]; i > 0; i--) {
                jumpImpl(nums, i, waysToGet, 1);
            }

            final int result = waysToGet[waysToGet.length - 1];
            return result != Integer.MAX_VALUE ? result : 0;
        }

        private void jumpImpl(int[] nums, int pos, int[] waysToGet, int jumpIdx) {
            if (pos > nums.length - 1) {
                return;
            }

            if (jumpIdx < waysToGet[pos]) {
                waysToGet[pos] = jumpIdx;
                for (int i = nums[pos]; i > 0; i--) {
                    jumpImpl(nums, pos + i, waysToGet, jumpIdx + 1);
                }
            }
        }
    }

    public static class GreedySolution implements Solution {

        @Override
        public int jump(int[] nums) {
            int result = 0;
            int farthestLeap = 0;

            for (int i = 0; i < nums.length; i++) {
                if (i + nums[i] > farthestLeap) {
                    farthestLeap = i + nums[i];
                    result++;

                    if (farthestLeap >= nums.length - 1) {
                        break;
                    }
                }
            }

            return result;
        }
    }
}
