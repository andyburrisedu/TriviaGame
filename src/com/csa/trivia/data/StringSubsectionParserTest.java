package com.csa.trivia.data;

public class StringSubsectionParserTest {

    public static void main(String[] args) {
        StringSubsectionParser sp = new StringSubsectionParser("abc dklfjdsc abcabc hsfjlafd abcddfjsakfjsd");
        assertEquals(sp.hasSubsection("abc", "abc"), true);
        assertEquals(sp.nextSubsection("abc", "abc"), "abc dklfjdsc abc");
        sp.skipPastNext("abc");
        assertEquals(sp.nextSubsection("abc", "d"), "abcd");
        assertEquals(sp.hasSubsection("f", "d"), true);
        assertEquals(sp.nextSubsection("f", "d"), "fjsakfjsd");
    }

    private static <T> void assertEquals(T o1, T o2){
        if (!o1.equals(o2)){
            throw new AssertionError(o1.toString() + " != " + o2.toString());
        }
    }
}
