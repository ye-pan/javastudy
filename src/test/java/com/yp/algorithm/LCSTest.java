package com.yp.algorithm;

import org.junit.Test;
import static org.junit.Assert.*;

public class LCSTest {

    @Test
    public void testLongSubString() {
        String sub = LCS.subString("abcdefg", "acg");
        assertEquals(sub, "acg");
    }
}
