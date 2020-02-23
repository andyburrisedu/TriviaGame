package com.csa.trivia.data;

import com.csa.trivia.TriviaQuestion;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class TriviaQuestionParser {
    private File inputFile;

    public TriviaQuestionParser(String filePath){
        inputFile = new File(filePath);
    }

    public ArrayList<TriviaQuestion> parse() {
        try{
            String input = scan();
            return parseInput(input);
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private String scan() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        Scanner scanner = new Scanner(inputFile);
        scanner.next();
        return br.lines().collect(Collectors.joining("\n"));
    }

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

    private String parseLine(StringSubsectionParser sp){
        sp.skipPastNext("\": ");
        String section = sp.nextSubsection("\"", "\"");
        return section.substring(1, section.length() -1);
    }

    private ArrayList<TriviaQuestion> parseInput(String input){
        ArrayList<TriviaQuestion> outList = new ArrayList<TriviaQuestion>();
        StringSubsectionParser subsectionParser = new StringSubsectionParser(input);
        while (subsectionParser.hasSubsection("{", "}")){
            TriviaQuestion tq = parseSingleQuestion(subsectionParser);
            outList.add(tq);
        }
        return outList;
    }

    public static void main(String[] args) {
        TriviaQuestionParser parser = new TriviaQuestionParser("src/com/csa/trivia/data/questions.json");
        System.out.println(parser.parse());
    }
}
