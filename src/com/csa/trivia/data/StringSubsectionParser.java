package com.csa.trivia.data;

/**
 * Class to progressively find subsections in a String - once a subsection is found it is ignored in the future.
 */
public class StringSubsectionParser {
    private String overall;

    /**
     *
     * @param overall The string to be parsed
     */
    public StringSubsectionParser(String overall){
        this.overall = overall;
    }

    /**
     * Check whether the remaining string has the specified subsection
     * @param startPattern Pattern to mark start of subsection
     * @param endPattern Pattern to mark end of subsection
     * @return true if subsection exists, false otherwise
     */
    public boolean hasSubsection(String startPattern, String endPattern){
        int startIndex = overall.indexOf(startPattern);
        int endIndex = overall.indexOf(endPattern, startIndex);
        return startIndex >= 0 && endIndex >= 0;
    }

    /**
     * Gets the next instance of the specified subsection and ignores it and anything before it for future searches
     * @param startPattern Pattern to mark start of subsection (inclusive)
     * @param endPattern Pattern to mark end of subsection (inclusive)
     * @return String subsection
     */
    public String nextSubsection(String startPattern, String endPattern){
        int startIndex = overall.indexOf(startPattern);
        int startOffset = startIndex + startPattern.length();
        int endIndex = overall.indexOf(endPattern, startOffset);
        String subSection = overall.substring(startIndex, endIndex + endPattern.length());
        overall = overall.substring(endIndex + endPattern.length());
        return subSection;
    }

    public void skipPastNext(String pattern){
        int index = overall.indexOf(pattern);
        overall = overall.substring(index + pattern.length());
    }

    public String currentString(){
        return overall;
    }
}
