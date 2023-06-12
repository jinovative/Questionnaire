package cs5004.questionnaire;

/**
 * Represents a question in a questionnaire.
 */
public interface Question {

  /**
   * Gets the prompt of the question.
   *
   * @return the prompt of the question
   */
  String getPrompt();

  /**
   * Checks if the question is required.
   *
   * @return true if the question is required, false otherwise
   */
  boolean inRequired();

  /**
   * Answers the question with the provided answer.
   *
   * @param answer the answer to the question
   */
  void answer(String answer);

  /**
   * Gets the answer to the question.
   *
   * @return the answer to the question
   */
  String getAnswer();

  /**
   * Creates a copy of the question.
   *
   * @return a copy of the question
   */
  Question copy();

}
