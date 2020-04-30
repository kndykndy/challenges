package com.kndykndy.leetcode;

import com.kndykndy.leetcode.util.TreeNode;

public class p124_BinaryTreeMaximumPathSum {

    interface Solution {

        int maxPathSum(TreeNode root);
    }

    public static class Solution1 implements Solution {

        @Override
        public int maxPathSum(TreeNode root) {
            if (root == null) {
                return 0;
            }

            int[] memoizeSubBranch = {0};

            return Math.max(maxPathSumHelper(root, memoizeSubBranch), memoizeSubBranch[0]);
        }

        private int maxPathSumHelper(TreeNode root, int[] p) {
            if (root == null) {
                return 0;
            }

            int sl = maxPathSumHelper(root.left, p);
            int sr = maxPathSumHelper(root.right, p);

            p[0] = max(p[0], sl, sr, root.val + sr + sl);

            return max(root.val, sl + root.val, root.val + sr);
        }

        private int max(int... nums) {
            int m = Short.MIN_VALUE;
            for (int num : nums) {
                if (num != 0 && num > m) {
                    m = num;
                }
            }
            return m;
        }
    }
}
