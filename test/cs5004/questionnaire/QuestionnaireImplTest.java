package cs5004.questionnaire;


import org.junit.Before;
import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Tests for the {@link QuestionnaireImpl} class.
 */
public class QuestionnaireImplTest {
  private QuestionnaireImpl questionnaire;

  @Before
  public void setUp() {
    questionnaire = new QuestionnaireImpl();
  }

  @Test
  public void testAddQuestion() {
    Question question = new Likert("Test Question", true);
    questionnaire.addQuestion("Q1", question);

    assertEquals(question, questionnaire.getQuestion("Q1"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddQuestion_NullIdentifier() {
    Question question = new Likert("Test Question", true);
    questionnaire.addQuestion(null, question);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddQuestion_EmptyIdentifier() {
    Question question = new Likert("Test Question", true);
    questionnaire.addQuestion("", question);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddQuestion_DuplicateIdentifier() {
    Question question1 = new Likert("Test Question 1", true);
    Question question2 = new Likert("Test Question 2", true);

    questionnaire.addQuestion("Q1", question1);
    questionnaire.addQuestion("Q1", question2);
  }

  @Test
  public void testRemoveQuestion() {
    Question question = new Likert("Test Question", true);
    questionnaire.addQuestion("Q1", question);

    questionnaire.removeQuestion("Q1");

    try {
      questionnaire.getQuestion("Q1");
      fail("Expected NoSuchElementException to be thrown");
    } catch (NoSuchElementException e) {
      // Exception is expected
    }
  }

  @Test(expected = NoSuchElementException.class)
  public void testRemoveQuestion_NonexistentIdentifier() {
    questionnaire.removeQuestion("Q1");
  }

  @Test
  public void testGetQuestion() {
    Question question1 = new Likert("Test Question 1", true);
    Question question2 = new Likert("Test Question 2", true);
    questionnaire.addQuestion("Q1", question1);
    questionnaire.addQuestion("Q2", question2);

    assertEquals(question1, questionnaire.getQuestion(1));
    assertEquals(question2, questionnaire.getQuestion(2));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetQuestion_InvalidIndex() {
    Question question = new Likert("Test Question", true);
    questionnaire.addQuestion("Q1", question);

    questionnaire.getQuestion(2);
  }

  @Test
  public void testGetQuestion_NonexistentIdentifier() {
    try {
      questionnaire.getQuestion("Q1");
      fail("Expected NoSuchElementException to be thrown");
    } catch (NoSuchElementException e) {
      // Exception is expected
    }
  }

  // Add more test cases for other methods

  @Test
  public void testIsComplete() {
    Question question1 = new Likert("Test Question 1", true);
    Question question2 = new Likert("Test Question 2", false);

    questionnaire.addQuestion("Q1", question1);
    questionnaire.addQuestion("Q2", question2);

    // No answers provided
    assertFalse(questionnaire.isComplete()); //Invalid input

    // Answer only the required question
    question1.answer("Agree");
    assertFalse(questionnaire.isComplete());

    // Answer both required and optional questions
    question2.answer("Neutral");
    assertTrue(questionnaire.isComplete());
  }

  @Test
  public void testGetResponses() {
    Question question1 = new Likert("Test Question 1", true);
    Question question2 = new Likert("Test Question 2", false);

    questionnaire.addQuestion("Q1", question1);
    questionnaire.addQuestion("Q2", question2);

    question1.answer("Disagree");
    question2.answer("Agree");

    assertEquals(2, questionnaire.getResponses().size());
    assertTrue(questionnaire.getResponses().contains("Disagree")); //Invalid input
    assertTrue(questionnaire.getResponses().contains("Agree"));
  }

  @Test
  public void testFilter() {
    Question question1 = new Likert("Test Question 1", true);
    Question question2 = new Likert("Test Question 2", false);

    questionnaire.addQuestion("Q1", question1);
    questionnaire.addQuestion("Q2", question2);

    // Filter for required questions
    QuestionnaireImpl filtered = (QuestionnaireImpl) questionnaire.filter(Question::isRequired);
    assertEquals(1, filtered.getRequiredQuestions().size());
    assertEquals(question1, filtered.getRequiredQuestions().get(0)); //Invalid input
  }

  @Test
  public void testSort() {
    Question question1 = new Likert("Test Question 1", true);
    Question question2 = new Likert("Test Question 2", false);

    questionnaire.addQuestion("Q1", question1);
    questionnaire.addQuestion("Q2", question2);

    // Sort in descending order
    questionnaire.sort((q1, q2) -> q2.getPrompt().compareToIgnoreCase(q1.getPrompt()));

    assertEquals(question2, questionnaire.getQuestion(1));
    assertEquals(question1, questionnaire.getQuestion(2));
  }

  @Test
  public void testFold() {
    Question question1 = new Likert("Test Question 1", true);
    Question question2 = new Likert("Test Question 2", false);

    questionnaire.addQuestion("Q1", question1);
    questionnaire.addQuestion("Q2", question2);

    // Count the total number of questions
    int totalCount = questionnaire.fold((q, count) -> count + 1, 0);
    assertEquals(2, totalCount);
  }

  // Add more test cases for other methods

  @Test
  public void testToString() {
    Question question1 = new Likert("Test Question 1", true);
    Question question2 = new Likert("Test Question 2", false);

    questionnaire.addQuestion("Q1", question1);
    questionnaire.addQuestion("Q2", question2);

    question1.answer("Agree");
    question2.answer("Neutral"); //Invalid input

    String expected = "Question: Test Question 1\n\nAnswer: agree\n\n" +
            "Question: Test Question 2\n\nAnswer: neutral\n\n";

    assertEquals(expected, questionnaire.toString());
  }
}

