package com.csa.trivia.data;

import com.csa.trivia.Utils;

/**
 * Test for {@link StringSubsectionParser}
 */
public class StringSubsectionParserTest {

    public static void main(String[] args) {
        StringSubsectionParser sp = new StringSubsectionParser("abc dklfjdsc abcabc hsfjlafd abcddfjsakfjsd");
        Utils.assertEquals(sp.hasSubsection("abc", "abc"), true);
        Utils.assertEquals(sp.nextSubsection("abc", "abc"), "abc dklfjdsc abc");
        sp.skipPastNext("abc");
        Utils.assertEquals(sp.nextSubsection("abc", "d"), "abcd");
        Utils.assertEquals(sp.hasSubsection("f", "d"), true);
        Utils.assertEquals(sp.nextSubsection("f", "d"), "fjsakfjsd");
    }
}
