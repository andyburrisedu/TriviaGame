package com.csa.trivia;

import com.csa.trivia.data.TriviaQuestionParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {
    private static List<String> POSSIBLE_ANSWERS = Arrays.asList("a", "b", "c", "d");

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<TriviaQuestion> questions;

    private int askedQuestionCount = 0;
    private boolean anyWrong = false;

    public Game(){
        TriviaQuestionParser parser = new TriviaQuestionParser("src/com/csa/trivia/data/questions.json");
        questions = parser.parse();
    }

    public void play() {
        System.out.println("Welcome to TriviaGame!\n");
        while (!anyWrong){
            TriviaQuestion question = getNextQuestion();
            askedQuestionCount++;
            System.out.println(question.questionFormat());

            boolean correct = question.checkAnswer(getNextValidAnswer());
            if (correct){
                System.out.println("That's correct! Time for the next question.");
            }else {
                printIncorrect(question, askedQuestionCount - 1);
            }
            anyWrong = !correct;
        }
    }

    private TriviaQuestion getNextQuestion() {
        int index = (int) (Math.random() * questions.size());
        return questions.remove(index);
    }

    private String getNextValidAnswer(){
        String userAnswer = scanner.nextLine().toLowerCase();
        while (!POSSIBLE_ANSWERS.contains(userAnswer)){
            System.out.println(userAnswer + " is invalid -- please answer A, B, C, or D");
            userAnswer = scanner.nextLine().toLowerCase();
        }
        return userAnswer;
    }

    private void printIncorrect(TriviaQuestion question, int finalScore){
        System.out.println("That's incorrect!");
        System.out.println("The right answer was " + question.formattedAnswer() + "\n");
        System.out.println("GAME OVER");
        System.out.println("Your final score was " + finalScore);
    }
}
