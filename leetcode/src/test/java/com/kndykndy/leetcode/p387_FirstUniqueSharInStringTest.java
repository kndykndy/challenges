package com.kndykndy.leetcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.kndykndy.leetcode.p387_FirstUniqueCharInString.Solution;
import com.kndykndy.leetcode.p387_FirstUniqueCharInString.Solution1;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class p387_FirstUniqueSharInStringTest {

    private List<Solution> solutions;

    @BeforeEach
    void setUp() {
        solutions = Collections.singletonList(new Solution1());
    }

    @Test
    void test1() {
        for (Solution solution : solutions) {
            assertEquals(0, solution.firstUniqChar("leetcode"));
            assertEquals(2, solution.firstUniqChar("loveleetcode"));
        }
    }
}
