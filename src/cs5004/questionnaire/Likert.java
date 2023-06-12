package cs5004.questionnaire;

public class Likert implements Question{
  private String prompt;
  private String answer;
  private boolean required;

  public Likert(String prompt, boolean required) {
    this.prompt = prompt;
    this.answer = "";
    this.required = required;

  }

  @Override
  public String getPrompt() {
    return prompt;
  }

  @Override
  public boolean inRequired() {
    return required;
  }

  @Override
  public void answer(String answer) {

  }

  @Override
  public String getAnswer() {
    return answer;
  }

  @Override
  public Question copy() {
    return null;
  }
}
