package cs5004.questionnaire;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class QuestionnaireImpl implements Questionnaire{
  @Override
  public void addQuestion(String identifier, Question q) {

  }

  @Override
  public void removeQuestion(String identifier) {

  }

  @Override
  public Question getQuestion(int num) {
    return null;
  }

  @Override
  public Question getQuestion(String identifier) {
    return null;
  }

  @Override
  public List<Question> getRequiredQuestions() {
    return null;
  }

  @Override
  public List<Question> getOptionalQuestions() {
    return null;
  }

  @Override
  public boolean isComplete() {
    return false;
  }

  @Override
  public List<String> getResponses() {
    return null;
  }

  @Override
  public Questionnaire filter(Predicate<Question> pq) {
    return null;
  }

  @Override
  public void sort(Comparator<Question> comp) {

  }

  @Override
  public <R> R fold(BiFunction<Question, R, R> bf, R seed) {
    return null;
  }
}
