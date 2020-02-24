package com.csa.trivia;
//assume other class returns an arraylist of TriviaQuestion
//git add file name
//git commit -m "whatever happened"
//git push origin master
// to get what andy has done git pull origin master
// to download again git clone and the url on cloner download in the page

/**
 * Class representing a trivia question, its answer choices, and its correct answer
 * @author Enzo
 */
public class TriviaQuestion {

    private String question;
    private String a;
    private String b;
    private String c;
    private String d;
    private int correctAnswer;

    /**
     * Creates a new TriviaQuestion with the answer choices for a, b, c, and d
     *
     * @param correctAnswer number representing answer -- 0 = A, 1 = B, 2 = C, 3 = D
     */
    public TriviaQuestion(String question, String a, String b, String c, String d, int correctAnswer) {
        this.question = question;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correctAnswer = correctAnswer;
    }
    // method check answer rturn boolean
    //two string method, list the question name and options

    /**
     * Checks if a given answer is correct
     *
     * @param ans number representing selected answer -- 0 = A, 1 = B, 2 = C, 3 = D
     * @return true if answer is correct, false otherwise
     */
    public boolean checkAnswer(int ans) {
        return ans == correctAnswer;
    }

    /**
     * Checks if a given answer is correct
     *
     * @param ans letter representing selected answer -- needs to be one of A, B, C, or D (case insensitive) or error will be thrown
     * @return true if answer is correct, false otherwise
     */
    public boolean checkAnswer(String ans) {
        int parsed;
        switch (ans.toUpperCase()) {
            case "A":
                parsed = 0;
                break;
            case "B":
                parsed = 1;
                break;
            case "C":
                parsed = 2;
                break;
            case "D":
                parsed = 3;
                break;
            default:
                throw new Error("Not a valid answer choice");
        }
        return checkAnswer(parsed);
    }

    /**
     * String representation of {@link TriviaQuestion#correctAnswer} -- 0 = A, 1 = B, 2 = C, 3 = D
     */
    public String formattedAnswer() {
        switch (correctAnswer) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
        }
        return "ERROR";
    }

    /**
     * String representation of question and the answer choices but not the correct answer in format:
     * Question
     * A) Answer A
     * B) Answer B
     * C) Answer C
     * D) Answer D
     */
    public String questionAndAnswerChoices() {
        return question + "\nA) " + a + "\nB) " + b + "\nC) " + c + "\nD) " + d + "\n";
    }

    /**
     * String representation of question, answer choices and correct answer in format:
     * Question
     * A) Answer A
     * B) Answer B
     * C) Answer C
     * D) Answer D
     * Answer: E
     */
    @Override
    public String toString() {
        return questionAndAnswerChoices() + "Answer: " + formattedAnswer() + "\n";
    }
}
