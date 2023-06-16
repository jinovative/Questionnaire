package cs5004.questionnaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

  @Override
  public void addQuestion(String identifier, Question q) {
    if (identifier == null || identifier.isEmpty()) {
      // Check if the identifier is null or empty
      throw new IllegalArgumentException("Invalid input");
    }
    if (questions.containsKey(identifier)) {
      // Check if the identifier already exists
      throw new IllegalArgumentException("Invalid input");
    }
    // Add the question to the questionnaire map
    questions.put(identifier, q);
  }

  @Override
  public void removeQuestion(String identifier) {
    // Check if the identifier is already in use
    if (!questions.containsKey(identifier)) {
      throw new NoSuchElementException("Question not found");
    }
    questions.remove(identifier);
  }

  @Override
  public Question getQuestion(int num) {
    // Check if the index is out of range
    if (num < 1 || num > questions.size()) {
      throw new IndexOutOfBoundsException("No such question 1: " + num);
    }
    // Get a list of all questions in the questionnaire
    List<Question> sortedQuestions = new ArrayList<>(questions.values());
    // Check if the index is out of range
    if (num > sortedQuestions.size()) {
      throw new IndexOutOfBoundsException("No such question 2: " + num);
    }
    return sortedQuestions.get(num - 1);
  }

  @Override
  public Question getQuestion(String identifier) {
    if (identifier == null || identifier.isEmpty()) {
      // Check if the identifier is null or empty
      throw new IllegalArgumentException("Invalid input: Identifier cannot be empty");
    }

    if (!questions.containsKey(identifier)) {
      // Check if the identifier exists in the map
      throw new NoSuchElementException("Invalid input");
    }

    return questions.get(identifier);
  }

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

  @Override
  public Questionnaire filter(Predicate<Question> pq) {
    QuestionnaireImpl filteredQuestionnaire = new QuestionnaireImpl();
    for (Map.Entry<String, Question> entry : questions.entrySet()) {
      String identifier = entry.getKey();
      Question originalQuestion = entry.getValue();
      // Create a copy of the original question
      Question copiedQuestion = originalQuestion.copy();
      if (pq.test(copiedQuestion)) {
        filteredQuestionnaire.addQuestion(identifier, copiedQuestion);
      }
    }
    return filteredQuestionnaire;
  }

  @Override
  public void sort(Comparator<Question> comp) {
    // Get a list of all questions in the questionnaire
    List<Question> questionList = new ArrayList<>(questions.values());
    // Sort the question list using the comparator
    questionList.sort(comp);

    Map<String, Question> sortedQuestions = new LinkedHashMap<>();
    for (Question question : questionList) {
      for (Map.Entry<String, Question> entry : questions.entrySet()) {
        // Find the corresponding entry for the question in the questionnaire
        if (entry.getValue().equals(question)) {
          // Add the question back to the questionnaire with the correct ordering
          sortedQuestions.put(entry.getKey(), question);
          break;
        }
      }
    }

    questions = sortedQuestions;
  }

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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Question question : questions.values()) {
      sb.append("Question: ").append(question.getPrompt()).append("\n\n");
      String answer = question.getAnswer();
      if (answer != null && !answer.isEmpty()) {
        // Convert the answer to lowercase
        answer = answer.toLowerCase();
      } else {
        answer = ""; // Handle empty answers
      }
      sb.append("Answer: ").append(answer).append("\n\n");
    }
    return sb.toString();
  }
}


