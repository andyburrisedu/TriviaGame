package com.csa.trivia.data;

import com.csa.trivia.TriviaQuestion;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * Class to parse .json files that contain the trivia questions into usable {@link TriviaQuestion} objects
 * @author Andy
 */
public class TriviaQuestionParser {
    private File inputFile;

    /**
     * Creates a new TriviaQuestionParser
     * @param filePath path from root directory of project to the file to parse. Must be a .json file with pretty-print formatting and lines ordered "question", "A", "B", "C", "D", "answer"
     */
    public TriviaQuestionParser(String filePath){
        inputFile = new File(filePath);
    }

    /**
     * Parses {@link TriviaQuestionParser#inputFile}. Must be a .json file with pretty-print formatting and lines ordered "question", "A", "B", "C", "D", "answer"
     * @return List of trivia questions
     */
    public ArrayList<TriviaQuestion> parse() {
        try{
            String input = scan();
            return parseInput(input);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Reads {@link TriviaQuestionParser#inputFile} to a String
     * @return the entire text of the .json file in {@link TriviaQuestionParser#inputFile}
     * @throws FileNotFoundException if path of {@link TriviaQuestionParser#inputFile} is invalid
     */
    private String scan() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        Scanner scanner = new Scanner(inputFile);
        scanner.next();
        return br.lines().collect(Collectors.joining("\n"));
    }

    /**
     * Parses JSON input into a list of TriviaQuestions
     * @param input JSON data, should be from {@link TriviaQuestionParser#inputFile}
     */
    private ArrayList<TriviaQuestion> parseInput(String input){
        ArrayList<TriviaQuestion> outList = new ArrayList<TriviaQuestion>();
        StringSubsectionParser subsectionParser = new StringSubsectionParser(input);
        while (subsectionParser.hasSubsection("{", "}")){
            TriviaQuestion tq = parseSingleQuestion(subsectionParser);
            outList.add(tq);
        }
        return outList;
    }

    /**
     * Parses the next question in the file
     * @param subsectionParser the {@link StringSubsectionParser} containing the remainder of the .json file to be parsed
     * @return TriviaQuestion from the next .json object
     */
    private TriviaQuestion parseSingleQuestion(StringSubsectionParser subsectionParser){
        String question = parseLine(subsectionParser);
        String a = parseLine(subsectionParser);
        String b = parseLine(subsectionParser);
        String c = parseLine(subsectionParser);
        String d = parseLine(subsectionParser);
        String answerLetter = parseLine(subsectionParser);
        int answer;
        switch (answerLetter){
            case "A": answer = 0; break;
            case "B": answer = 1; break;
            case "C": answer = 2; break;
            case "D": answer = 3; break;
            default: throw new Error("answerLetter is not A, B, C, or D (" + answerLetter + ")");
        }
        return new TriviaQuestion(question, a, b, c, d, answer);
    }

    /**
     * Parses a single line of JSON text, returning only the value
     * @param sp the {@link StringSubsectionParser} holding the JSON data
     * @return the value of the first key-value pair in sp
     */
    private String parseLine(StringSubsectionParser sp){
        sp.skipPastNext("\": ");
        String section = sp.nextSubsection("\"", "\"");
        return section.substring(1, section.length() -1);
    }
}
