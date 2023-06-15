package cs5004.questionnaire;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class QuestionnaireImplTest {
  private QuestionnaireImpl questionnaire;

  @Before
  public void setUp() {
    questionnaire = new QuestionnaireImpl();
  }

  @Test
  public void testAddQuestion() {
    Question question = new YesNo("Do you like ice cream?", true);
    questionnaire.addQuestion("q1", question);

    assertEquals(question, questionnaire.getQuestion("q1"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddQuestion_ThrowsIllegalArgumentException() {
    Question question = new YesNo("Do you like ice cream?", true);
    questionnaire.addQuestion("q1", question);
    questionnaire.addQuestion("q1", question); // Adding the same question again should throw an exception
  }

  @Test
  public void testRemoveQuestion() {
    Question question = new YesNo("Do you like ice cream?", true);
    questionnaire.addQuestion("q1", question);

    questionnaire.removeQuestion("q1");

    try {
      questionnaire.getQuestion("q1"); // Trying to get the removed question should throw an exception
      fail("Expected NoSuchElementException to be thrown");
    } catch (NoSuchElementException e) {
      // Exception is expected
    }
  }

  @Test
  public void testGetQuestion() {
    Question question1 = new YesNo("Do you like ice cream?", true);
    Question question2 = new Likert("How satisfied are you?", true);
    questionnaire.addQuestion("q1", question1);
    questionnaire.addQuestion("q2", question2);

    assertEquals(question1, questionnaire.getQuestion("q1"));
    assertEquals(question2, questionnaire.getQuestion("q2"));
  }

  @Test(expected = NoSuchElementException.class)
  public void testGetQuestion_ThrowsNoSuchElementException() {
    questionnaire.getQuestion("q1"); // Trying to get a non-existent question should throw an exception
  }

  @Test
  public void testGetRequiredQuestions() {
    Question question1 = new YesNo("Do you like ice cream?", true);
    Question question2 = new Likert("How satisfied are you?", true);
    Question question3 = new ShortAnswer("What is your favorite color?", false);
    questionnaire.addQuestion("q1", question1);
    questionnaire.addQuestion("q2", question2);
    questionnaire.addQuestion("q3", question3);

    List<Question> requiredQuestions = questionnaire.getRequiredQuestions();
    assertEquals(2, requiredQuestions.size());
    assertTrue(requiredQuestions.contains(question1));
    assertTrue(requiredQuestions.contains(question2));
  }

  @Test
  public void testGetOptionalQuestions() {
    Question question1 = new YesNo("Do you like ice cream?", true);
    Question question2 = new Likert("How satisfied are you?", true);
    Question question3 = new ShortAnswer("What is your favorite color?", false);
    questionnaire.addQuestion("q1", question1);
    questionnaire.addQuestion("q2", question2);
    questionnaire.addQuestion("q3", question3);

    List<Question> optionalQuestions = questionnaire.getOptionalQuestions();
    assertEquals(1, optionalQuestions.size());
    assertTrue(optionalQuestions.contains(question3));
  }

  @Test
  public void testIsComplete() {
    Question question1 = new YesNo("Do you like ice cream?", true);
    Question question2 = new Likert("How satisfied are you?", true);
    questionnaire.addQuestion("q1", question1);
    questionnaire.addQuestion("q2", question2);

    assertFalse(questionnaire.isComplete());

    question1.answer("Yes");
    question2.answer("Agree");

    assertTrue(questionnaire.isComplete());
  }

  @Test
  public void testGetResponses() {
    Question question1 = new YesNo("Do you like ice cream?", true);
    Question question2 = new Likert("How satisfied are you?", true);
    questionnaire.addQuestion("q1", question1);
    questionnaire.addQuestion("q2", question2);

    question1.answer("Yes");
    question2.answer("Agree");

    List<String> responses = questionnaire.getResponses();
    assertEquals(2, responses.size());
    assertTrue(responses.contains("Yes"));
    assertTrue(responses.contains("Agree"));
  }

  @Test
  public void testFilter() {
    Question question1 = new YesNo("Do you like ice cream?", true);
    Question question2 = new Likert("How satisfied are you?", true);
    Question question3 = new ShortAnswer("What is your favorite color?", false);
    questionnaire.addQuestion("q1", question1);
    questionnaire.addQuestion("q2", question2);
    questionnaire.addQuestion("q3", question3);

    Questionnaire filteredQuestionnaire = questionnaire.filter(q -> q.isRequired());
    assertEquals(2, filteredQuestionnaire.getRequiredQuestions().size());
    assertTrue(filteredQuestionnaire.getRequiredQuestions().contains(question1));
    assertTrue(filteredQuestionnaire.getRequiredQuestions().contains(question2));
  }

  @Test
  public void testSort() {
    Question question1 = new YesNo("Do you like ice cream?", true);
    Question question2 = new Likert("How satisfied are you?", true);
    Question question3 = new ShortAnswer("What is your favorite color?", false);
    questionnaire.addQuestion("q1", question1);
    questionnaire.addQuestion("q2", question2);
    questionnaire.addQuestion("q3", question3);

    questionnaire.sort((q1, q2) -> q1.getPrompt().compareToIgnoreCase(q2.getPrompt()));

    List<Question> sortedQuestions = questionnaire.getRequiredQuestions();
    assertEquals(question3, sortedQuestions.get(0));
    assertEquals(question1, sortedQuestions.get(1));
    assertEquals(question2, sortedQuestions.get(2));
  }

  @Test
  public void testFold() {
    Question question1 = new YesNo("Do you like ice cream?", true);
    Question question2 = new Likert("How satisfied are you?", true);
    Question question3 = new ShortAnswer("What is your favorite color?", false);
    questionnaire.addQuestion("q1", question1);
    questionnaire.addQuestion("q2", question2);
    questionnaire.addQuestion("q3", question3);

    int totalCharacters = questionnaire.fold((q, count) -> count + q.getPrompt().length(), 0);

    assertEquals(72, totalCharacters);
  }

  @Test
  public void testToString() {
    Question question1 = new YesNo("Do you like ice cream?", true);
    Question question2 = new Likert("How satisfied are you?", true);
    questionnaire.addQuestion("q1", question1);
    questionnaire.addQuestion("q2", question2);

    String expected = "Question: Do you like ice cream?\n\nAnswer: \n\n" +
            "Question: How satisfied are you?\n\nAnswer: \n\n";
    assertEquals(expected, questionnaire.toString());

    question1.answer("Yes");
    question2.answer("Agree");

    expected = "Question: Do you like ice cream?\n\nAnswer: Yes\n\n" +
            "Question: How satisfied are you?\n\nAnswer: Agree\n\n";
    assertEquals(expected, questionnaire.toString());
  }
}

