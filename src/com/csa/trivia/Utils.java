package com.csa.trivia;

/**
 * Class to hold utility methods
 */
public class Utils {

    /**
     * Testing method that throws an error if two objects aren't equal
     * @param o1 first object to compare
     * @param o2 second object to compare
     * @param <T> Type that both objects need to be, inferred by compiler
     */
    public static <T> void assertEquals(T o1, T o2){
        if (!o1.equals(o2)){
            throw new AssertionError(o1.toString() + " != " + o2.toString());
        }
    }

    /**
     * Calculates random integer within a given range
     *
     * @param baseInclusive    Bottom of random range (possible to be a returned value)
     * @param ceilingExclusive Top of random range (won't be a returned value)
     * @return Random integer in range
     */
    public static int random(int baseInclusive, int ceilingExclusive) {
        int range = ceilingExclusive - baseInclusive;
        return (int) (Math.random() * range + baseInclusive);
    }

    /**
     * Calculates random integer within a range of 0 to a given number
     *
     * @param ceilingExclusive Top of random range (won't be a returned value)
     * @return Random integer in range
     */
    public static int random(int ceilingExclusive) {
        return (int) (Math.random() * ceilingExclusive);
    }


}
