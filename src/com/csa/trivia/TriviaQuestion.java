//assume other class returns an arraylist of TriviaQuestion 
//git add file name
//got commit "whatever happened"
//git push origin master
// to get what andy has done git pull origin master
// to download again git clone and the url on cloner download in the page


//make a string for questions and each of the answers, then make an int from 0 to 3 
public class TriviaQuestion {
  
  String questions;
  String a;
  String b;
  String c;
  String d;
  int answer;
  
  public TriviaQuestion(String questions, String a, String b, String c, String d, int answer){
    this.questions = questions;
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
    this.answer = answer;
  }
  
  public static void Answer (int answer){
    
  }
  
}
