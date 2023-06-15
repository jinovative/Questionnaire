package cs5004.questionnaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Represents an implementation of the {@link Questionnaire} interface.
 */
public class QuestionnaireImpl implements Questionnaire {
  private Map<String, Question> questions;

  /**
   * Constructs a new QuestionnaireImpl object.
   */
  public QuestionnaireImpl() {
    questions = new HashMap<>();
  }

  /**
   * Adds a question to the questionnaire.
   *
   * @param identifier the identifier for the question
   * @param q the question to add
   * @throws IllegalArgumentException if the identifier is already in use
   */
  @Override
  public void addQuestion(String identifier, Question q) {
    if (questions.containsKey(identifier)) {
      // Check if the identifier is already in use
      throw new IllegalArgumentException("Invalid input");
    }
    // Add the question to the questionnaire map
    questions.put(identifier, q);
  }

  /**
   * Removes a question from the questionnaire.
   *
   * @param identifier the identifier of the question to remove
   */
  @Override
  public void removeQuestion(String identifier) {
    questions.remove(identifier);
  }

  /**
   * Gets the question at the specified index.
   *
   * @param num the index of the question
   * @return the question at the specified index
   * @throws IllegalArgumentException if the index is out of range
   */
  @Override
  public Question getQuestion(int num) {
    // Get a list of all questions in the questionnaire
    List<Question> questionLists = new ArrayList<>(questions.values());
    // Check if the index is out of range
    if (num < 0 || num >= questionLists.size()) {
      throw new IllegalArgumentException("Invalid input");
    }
    return questionLists.get(num);
  }

  /**
   * Gets the question with the specified identifier.
   *
   * @param identifier the identifier of the question
   * @return the question with the specified identifier
   * @throws NoSuchElementException if the question with the specified identifier does not exist
   */
  @Override
  public Question getQuestion(String identifier) {
    // Check if the question with the specified identifier exists
    if (!questions.containsKey(identifier)) {
      throw new NoSuchElementException("Invalid input");
    }
    return questions.get(identifier);
  }

  /**
   * Gets a list of all required questions in the questionnaire.
   *
   * @return a list of all required questions
   */
  @Override
  public List<Question> getRequiredQuestions() {
    List<Question> requiredQuestions = new ArrayList<>();
    for (Question question : questions.values()) {
      // Check if the question is required
      if (question.isRequired()) {
        // Add the required question to the list
        requiredQuestions.add(question);
      }
    }
    return requiredQuestions;
  }

  /**
   * Gets a list of all optional questions in the questionnaire.
   *
   * @return a list of all optional questions
   */
  @Override
  public List<Question> getOptionalQuestions() {
    List<Question> optionalQuestions = new ArrayList<>();
    for (Question question : questions.values()) {
      // Check if the question is optional
      if (!question.isRequired()) {
        // Add the optional question to the list
        optionalQuestions.add(question);
      }
    }
    return optionalQuestions;
  }

  /**
   * Checks if the questionnaire is complete.
   *
   * @return true if the questionnaire is complete, false otherwise
   */
  @Override
  public boolean isComplete() {
    for (Question question : questions.values()) {
      // Check if a required question is unanswered
      if (question.isRequired() && question.getAnswer() == null) {
        return false;
      }
    }
    return true;
  }

  /**
   * Gets a list of all responses provided in the questionnaire.
   *
   * @return a list of all responses
   */
  @Override
  public List<String> getResponses() {
    List<String> responses = new ArrayList<>();
    for (Question question : questions.values()) {
      String answer = question.getAnswer();
      // Check if a question has an answer
      if (answer != null) {
        // Add the answer to the list of responses
        responses.add(answer);
      }
    }
    return responses;
  }

  /**
   * Filters the questionnaire based on a predicate.
   *
   * @param pq the predicate to filter the questionnaire
   * @return a filtered questionnaire
   */
  @Override
  public Questionnaire filter(Predicate<Question> pq) {
    QuestionnaireImpl filteredQuestionnaire = new QuestionnaireImpl();
    for (Map.Entry<String, Question> entry : questions.entrySet()) {
      Question originalQuestion = entry.getValue();
      Question copiedQuestion = originalQuestion.copy();
      // Create a copy of the original question
      if (pq.test(copiedQuestion)) {
        // Check if the copied question satisfies the predicate
        filteredQuestionnaire.addQuestion(entry.getKey(), copiedQuestion);
        // Add the copied question to the filtered questionnaire
      }
    }
    return filteredQuestionnaire;
  }

  /**
   * Sorts the questions in the questionnaire using the specified comparator.
   *
   * @param comp the comparator to sort the questions
   */
  @Override
  public void sort(Comparator<Question> comp) {
    // Get a list of all questions in the questionnaire
    List<Question> questionList = new ArrayList<>(questions.values());
    questionList.sort(comp);  // Sort the question list using the comparator
    questions.clear();  // Clear the current questions in the questionnaire
    for (Question question : questionList) {
      for (Map.Entry<String, Question> entry : questions.entrySet()) {
        // Find the corresponding entry for the question in the questionnaire
        if (entry.getKey().equals(question)) {
          // Add the question back to the questionnaire with the correct ordering
          questions.put(entry.getKey(), question);
          break;
        }
      }
    }
  }

  /**
   * Applies a folding operation to the questions in the questionnaire.
   *
   * @param bf   the binary function to apply to each question and the accumulator
   * @param seed the initial value of the accumulator
   * @param <R>  the type of the result
   * @return the result of the folding operation
   */
  @Override
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) {
    // Initialize the result with the seed value
    R result = seed;
    for (Question question : questions.values()) {
      // Apply the binary function to the question and the result
      result = bf.apply(question, result);
    }
    return result;
  }

  /**
   * Returns a string representation of the questionnaire.
   *
   * @return a string representation of the questionnaire
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Question question : questions.values()) {
      sb.append("Question: ").append(question.getPrompt()).append("\n\n");
      sb.append("Answer: ").append(question.getAnswer()).append("\n\n");
    }
    return sb.toString();
  }
}

