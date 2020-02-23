package com.csa.trivia;
//assume other class returns an arraylist of TriviaQuestion
//git add file name
//git commit -m "whatever happened"
//git push origin master
// to get what andy has done git pull origin master
// to download again git clone and the url on cloner download in the page

//make a string for questions and each of the answers, then make an int from 0 to 3 
public class TriviaQuestion {
  
  private String questions;
  private String a;
  private String b;
  private String c;
  private String d;
  private int answer;
  
  public TriviaQuestion(String questions, String a, String b, String c, String d, int answer){
    this.questions = questions;
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
    this.answer = answer;
  }
  // method check answer rturn boolean
  //two string method, list the question name and options
  
  public boolean checkAnswer(int ans){
    return ans == answer;
  }
  public boolean checkAnswer(String ans){
      int parsed;
      switch (ans.toUpperCase()){
          case "A": parsed = 0; break;
          case "B": parsed = 1; break;
          case "C": parsed = 2; break;
          case "D": parsed = 3; break;
          default: throw new Error("Not a valid answer choice");
      }
      return checkAnswer(parsed);
  }

  public String questionFormat(){
      return questions + "\nA) " + a + "\nB) " + b + "\nC) " + c + "\nD) " + d + "\n";
  }

  public String formattedAnswer(){
      switch (answer){
          case 0: return "A";
          case 1: return "B";
          case 2: return "C";
          case 3: return "D";
      }
      return "ERROR";
  }

  @Override
  public String toString (){
    return questionFormat() + "Answer: " + formattedAnswer() + "\n";
  }
}
