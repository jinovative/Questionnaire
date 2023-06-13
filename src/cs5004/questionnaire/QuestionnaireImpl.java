package cs5004.questionnaire;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class QuestionnaireImpl implements Questionnaire{
  private Map<String, Question> questions;
  public QuestionnaireImpl(){
    questions = new HashMap<>();
  }
  @Override
  public void addQuestion(String identifier, Question q) {
    if (questions.containsKey(identifier)) {
      throw new IllegalArgumentException("Invalid input");
    }
    questions.put(identifier, q);
  }

  @Override
  public void removeQuestion(String identifier) {
    questions.remove(identifier);
  }

  @Override
  public Question getQuestion(int num) {
    if (num < 0 || num >= questions.size()){
      throw new IllegalArgumentException("Invalid input");
    }
    List<Question> questionLists = new ArrayList<>(questions.values());
    return questionLists.get(num);
  }

  @Override
  public Question getQuestion(String identifier) {
    if (!questions.containsKey(identifier)) {
      throw new NoSuchElementException("Invalid input");
    }
    return questions.get(identifier);
  }

  @Override
  public List<Question> getRequiredQuestions() {
    List<Question> requiredQuestions = new ArrayList<>();
    for (Question question : questions.values()) {
      if (question.isRequired()) {
        requiredQuestions.add(question);
      }
    }
    return requiredQuestions;
  }

  @Override
  public List<Question> getOptionalQuestions() {
    List<Question> optionalQuestions = new ArrayList<>();
    for (Question question : questions.values()) {
      if (!question.isRequired()) {
        optionalQuestions.add(question);
      }
    }
    return optionalQuestions;
  }

  @Override
  public boolean isComplete() {
    for (Question question : questions.values()) {
      if (question.isRequired() && question.getAnswer() == null){
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
      if (answer != null) {
        responses.add(answer);
      }
    }
    return responses;
  }

  @Override
  public Questionnaire filter(Predicate<Question> pq) {
    QuestionnaireImpl filteredQuestionnaire = new QuestionnaireImpl();
    for (Map.Entry<String, Question> entry : questions.entrySet()) {
      Question originalQuestion = entry.getValue();
      Question copiedQuestion = originalQuestion.copy();
      if (pq.test(copiedQuestion)) {
        filteredQuestionnaire.addQuestion(entry.getKey(), copiedQuestion);
      }
    }
    return filteredQuestionnaire;
  }

  @Override
  public void sort(Comparator<Question> comp) {
    List<Question> questionList = new ArrayList<>(questions.values());
    questionList.sort(comp);
    questions.clear();
    for (Question question : questionList) {
      for (Map.Entry<String, Question> entry : questions.entrySet()) {
        if (entry.getKey().equals(question)) {
          questions.put(entry.getKey(), question);
          break;
        }
      }
    }
  }

  @Override
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) {
    R result = seed;
    for (Question question : questions.values()) {
      result = bf.apply(question, result);
    }
    return result;
  }

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
