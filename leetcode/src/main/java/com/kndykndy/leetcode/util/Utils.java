package com.kndykndy.leetcode.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private Utils() {
    }

    public static void printBooleanMatrix(boolean[][] m) {
        for (boolean[] row : m) {
            for (int j = 0; j < m[0].length; j++) {
//                System.out.print((char) ('A' + String.valueOf(row[j]).charAt(0) - 'a'));
                System.out.print(row[j] ? "T " : "- ");
            }
            System.out.println();
        }
    }

    public static int[][] strTo2DArray(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }

        final int rows = str.split("\\s*(],\\[)\\s*").length;
        final int cols = str.split("\\s*(,)\\s*").length / rows;

        final int[][] result = new int[rows][cols];

        int row = 0, col = 0;

        final Matcher matcher = Pattern.compile("(\\d+)").matcher(str);
        while (matcher.find()) {
            result[row][col++] = Integer.parseInt(matcher.group());
            if (col == cols) {
                row++;
                col = 0;
            }
        }

        return result;
    }
}
