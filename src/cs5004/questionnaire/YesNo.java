package cs5004.questionnaire;

/**
 * Represents a Yes/No question implementation of the {@link Question} interface.
 */
public class YesNo implements Question {
  private String prompt;
  private String answer;
  private boolean required;

  /**
   * Constructs a new Yes/No question with the given prompt and required flag.
   *
   * @param prompt   the question prompt
   * @param required true if the question is required, false otherwise
   */
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
    YesNo copy = new YesNo(prompt, required);
    copy.answer = this.answer;
    return copy;
  }
}
