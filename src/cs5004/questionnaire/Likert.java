package cs5004.questionnaire;

public class Likert implements Question {
  @Override
  public String getPrompt() {
    return null;
  }

  @Override
  public boolean inRequired() {
    return false;
  }

  @Override
  public void answer(String answer) {

  }

  @Override
  public String getAnswer() {
    return null;
  }

  @Override
  public Question copy() {
    return null;
  }
}
