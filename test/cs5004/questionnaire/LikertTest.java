package cs5004.questionnaire;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link Likert} class.
 */
public class LikertTest {

  private Likert question;

  /**
   * Sets up the test fixture by creating a {@link Likert} instance.
   */
  @Before
  public void setUp() {
    question = new Likert("I enjoy programming.", true);
  }

  /**
   * Tests the {@link Likert#getPrompt()} method.
   */
  @Test
  public void testGetPrompt() {
    assertEquals("I enjoy programming.", question.getPrompt());
  }

  /**
   * Tests the {@link Likert#isRequired()} method.
   */
  @Test
  public void testIsRequired() {
    assertTrue(question.isRequired());
  }

  /**
   * Tests the {@link Likert#answer(String)} method with a valid input.
   */
  @Test
  public void testAnswer_ValidInput() {
    String answer = "Agree";
    question.answer(answer);
    assertEquals(answer.toLowerCase(), question.getAnswer());
  }

  /**
   * Tests the {@link Likert#answer(String)} method with an invalid input.
   * Expects an {@link IllegalArgumentException} to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnswer_InvalidInput() {
    String answer = "Invalid Option";
    question.answer(answer);
  }

  /**
   * Tests the {@link Likert#getAnswer()} method.
   */
  @Test
  public void testGetAnswer() {
    assertEquals("", question.getAnswer());
  }

  /**
   * Tests the {@link Likert#copy()} method.
   */
  @Test
  public void testCopy() {
    String answer = "Agree";
    question.answer(answer);
    Question copy = question.copy();
    assertEquals(question.getPrompt(), copy.getPrompt());
    assertEquals(question.isRequired(), copy.isRequired());
    assertEquals(question.getAnswer(), copy.getAnswer());
  }
}
