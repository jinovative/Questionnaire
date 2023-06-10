package cs5004.questionnaire;

public interface Question {

  String getPrompt();
  boolean inRequired();
  void answer(String answer);
  String getAnswer();
  Question copy();

}
