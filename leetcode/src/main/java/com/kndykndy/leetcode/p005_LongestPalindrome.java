package com.kndykndy.leetcode;

/**
 * Leetcode #5 ~ Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring
 *
 * 1 Mar 2018
 *
 * Problem statement:
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum
 * length of s is 1000.
 *
 * Examples:
 * Input: "babad", output: "bab", note: "aba" is also a valid answer.
 * Input: "cbbd", output: "bb"
 */
public class p005_LongestPalindrome {

    interface Solution {

        String longestPalindrome(String s);
    }

    public static class BruteForceSolution implements Solution {

        @Override
        public String longestPalindrome(String s) {
            if (s == null || "".equals(s)) {
                return "";
            }

            String longestPalindrome = "";
            String tempStr;
            for (int i = 0; i < s.length(); i++) {
                tempStr = oddPalindrome(s, i);
                if (tempStr != null && tempStr.length() > longestPalindrome.length()) {
                    longestPalindrome = tempStr;
                }
                tempStr = evenPalindrome(s, i);
                if (tempStr != null && tempStr.length() > longestPalindrome.length()) {
                    longestPalindrome = tempStr;
                }
            }

            return longestPalindrome;
        }

        private static String oddPalindrome(String s, int pivotCharIdx) {
            if (s == null || "".equals(s)) {
                return null;
            }
            if (pivotCharIdx < 0 || pivotCharIdx > s.length()) {
                return null;
            }

            int i = 0;
            while (i < s.length()
                && pivotCharIdx - i - 1 >= 0
                && pivotCharIdx + i + 1 <= s.length() - 1
                && s.charAt(pivotCharIdx - i - 1) == s.charAt(pivotCharIdx + i + 1)) {
                i++;
            }

            return s.substring(pivotCharIdx - i, pivotCharIdx + i + 1);
        }

        private static String evenPalindrome(String s, int pivotCharIdx) {
            if (s == null || "".equals(s) || s.length() < 2) {
                return null;
            }
            if (pivotCharIdx < 0 || pivotCharIdx > s.length() - 1) {
                return null;
            }

            int i = -1;
            while (i < s.length()
                && pivotCharIdx - i - 1 >= 0
                && pivotCharIdx + 1 + i + 1 <= s.length() - 1
                && s.charAt(pivotCharIdx - i - 1) == s.charAt(pivotCharIdx + 1 + i + 1)) {
                i++;
            }

            if (i == -1) {
                return null;
            }

            return s.substring(pivotCharIdx - i, pivotCharIdx + 1 + i + 1);
        }
    }

    public static void main(String[] args) {
        // test odd palindrome
        assert BruteForceSolution.oddPalindrome(null, 0) == null;
        assert BruteForceSolution.oddPalindrome("", 0) == null;
        assert "b".equals(BruteForceSolution.oddPalindrome("b", 0));
        assert "b".equals(BruteForceSolution.oddPalindrome("ba", 0));
        assert "a".equals(BruteForceSolution.oddPalindrome("ba", 1));
        assert "bab".equals(BruteForceSolution.oddPalindrome("bab", 1));
        assert "bab".equals(BruteForceSolution.oddPalindrome("baba", 1));
        assert "aba".equals(BruteForceSolution.oddPalindrome("baba", 2));
        assert "babab".equals(BruteForceSolution.oddPalindrome("babab", 2));
        assert "aba".equals(BruteForceSolution.oddPalindrome("babad", 2));

        // test even palindrome
        assert BruteForceSolution.evenPalindrome(null, 0) == null;
        assert BruteForceSolution.evenPalindrome("", 0) == null;
        assert BruteForceSolution.evenPalindrome("l", 0) == null;
        assert BruteForceSolution.evenPalindrome("lc", 0) == null;
        assert BruteForceSolution.evenPalindrome("lcc", 0) == null;
        assert "cc".equals(BruteForceSolution.evenPalindrome("lcc", 1));
        assert BruteForceSolution.evenPalindrome("lccl", 0) == null;
        assert "lccl".equals(BruteForceSolution.evenPalindrome("lccl", 1));
        assert "cc".equals(BruteForceSolution.evenPalindrome("lccb", 1));
    }
}
