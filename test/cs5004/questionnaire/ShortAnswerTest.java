package cs5004.questionnaire;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link ShortAnswer} class.
 */
public class ShortAnswerTest {

  private ShortAnswer question;

  /**
   * Sets up the test fixture by creating a {@link ShortAnswer} instance.
   */
  @Before
  public void setUp() {
    question = new ShortAnswer("Enter your name:", true);
  }

  /**
   * Tests the {@link ShortAnswer#getPrompt()} method.
   */
  @Test
  public void testGetPrompt() {
    assertEquals("Enter your name:", question.getPrompt());
  }

  /**
   * Tests the {@link ShortAnswer#isRequired()} method.
   */
  @Test
  public void testIsRequired() {
    assertTrue(question.isRequired());
  }

  /**
   * Tests the {@link ShortAnswer#answer(String)} method with a valid input (within the character limit).
   */
  @Test
  public void testAnswer_ValidInput() {
    String answer = "John Doe";
    question.answer(answer);
    assertEquals(answer, question.getAnswer());
  }

  /**
   * Tests the {@link ShortAnswer#answer(String)} method with an invalid input (exceeds the character limit).
   * Expects an {@link IllegalArgumentException} to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnswer_InvalidInput() {
    String answer = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    question.answer(answer);
  }

  /**
   * Tests the {@link ShortAnswer#getAnswer()} method.
   */
  @Test
  public void testGetAnswer() {
    assertEquals("", question.getAnswer());
  }

  /**
   * Tests the {@link ShortAnswer#copy()} method.
   */
  @Test
  public void testCopy() {
    String answer = "John Doe";
    question.answer(answer);
    Question copy = question.copy();
    assertEquals(question.getPrompt(), copy.getPrompt());
    assertEquals(question.isRequired(), copy.isRequired());
    assertEquals(question.getAnswer(), copy.getAnswer());
  }
}
