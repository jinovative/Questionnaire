package cs5004.questionnaire;

public class YesNo implements Question{
  private String prompt;
  private String answer;
  private boolean required;

  public YesNo(String prompt, boolean required) {
    this.prompt = prompt;
    this.answer = "";
    this.required = required;
  }
  @Override
  public String getPrompt() {
    return prompt;
  }

  @Override
  public boolean isRequired() {
    return required;
  }

  @Override
  public void answer(String answer) {
    if (answer.equalsIgnoreCase("Yes") || answer.equalsIgnoreCase(("No"))) {
      this.answer = answer;
    } else {
      throw new IllegalArgumentException("Invalid input");
    }
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
