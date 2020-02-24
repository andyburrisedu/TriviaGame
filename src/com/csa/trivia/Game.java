package com.csa.trivia;

import com.csa.trivia.data.TriviaQuestionParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Class that contains game logic and state
 */
public class Game {
    private static List<String> POSSIBLE_ANSWERS = Arrays.asList("a", "b", "c", "d");

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<TriviaQuestion> questions;

    private int askedQuestionCount = 0;
    private boolean anyWrong = false;

    /**
     * Creates a new game and initializes the trivia questions with values loaded from questions.json
     */
    public Game() {
        TriviaQuestionParser parser = new TriviaQuestionParser("src/com/csa/trivia/data/questions.json");
        questions = parser.parse();
    }

    /**
     * Begins the game -- requires user input so suspends code
     */
    public void play() {
        System.out.println("Welcome to TriviaGame!\n");
        while (!anyWrong) {
            TriviaQuestion question = getNextQuestion();
            askedQuestionCount++;
            System.out.println(question.questionAndAnswerChoices());

            boolean correct = question.checkAnswer(getNextValidAnswer());
            if (correct) {
                System.out.println("That's correct! Time for the next question.");
            } else {
                printIncorrect(question, askedQuestionCount - 1);
            }
            anyWrong = !correct;
        }
    }

    /**
     * Gets a random question and removes it from the options
     */
    private TriviaQuestion getNextQuestion() {
        int index = Utils.random(questions.size());
        return questions.remove(index);
    }

    /**
     * Gets next answer of A, B, C, or D (case insensitive) that user inputs, reprompting them if they input an unexpected value
     */
    private String getNextValidAnswer() {
        String userAnswer = scanner.nextLine().toLowerCase();
        while (!POSSIBLE_ANSWERS.contains(userAnswer)) {
            System.out.println(userAnswer + " is invalid -- please answer A, B, C, or D");
            userAnswer = scanner.nextLine().toLowerCase();
        }
        return userAnswer;
    }

    /**
     * Prints output for when player gets a question incorrect, including the correct answer
     * @param question the question the user got wrong
     * @param finalScore the total score to display
     */
    private void printIncorrect(TriviaQuestion question, int finalScore) {
        System.out.println("That's incorrect!");
        System.out.println("The right answer was " + question.formattedAnswer() + "\n");
        System.out.println("GAME OVER");
        System.out.println("Your final score was " + finalScore);
    }
}
