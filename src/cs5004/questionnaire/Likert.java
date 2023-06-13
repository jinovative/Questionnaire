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
  public boolean isRequired() {
    return required;
  }

  @Override
  public void answer(String answer) {
    answer = answer.toLowerCase();
    boolean validAnswer = false;
    for (LikertResponseOption option : LikertResponseOption.values()) {
      if (option.getText().toLowerCase().equalsIgnoreCase(answer)) {
        this.answer = answer;
        validAnswer = true;
        break;
      }
    }
    if (!validAnswer) {
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
