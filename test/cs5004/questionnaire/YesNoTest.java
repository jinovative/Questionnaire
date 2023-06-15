package cs5004.questionnaire;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests for the {@link YesNo} class.
 */
public class YesNoTest {

  private YesNo question;

  /**
   * Sets up the test fixture by creating a {@link YesNo} instance.
   */
  @Before
  public void setUp() {
    question = new YesNo("Is this a question?", true);
  }

  /**
   * Tests the {@link YesNo#getPrompt()} method.
   */
  @Test
  public void testGetPrompt() {
    assertEquals("Is this a question?", question.getPrompt());
  }

  /**
   * Tests the {@link YesNo#isRequired()} method.
   */
  @Test
  public void testIsRequired() {
    assertTrue(question.isRequired());
  }

  /**
   * Tests the {@link YesNo#answer(String)} method with a valid input ("Yes").
   */
  @Test
  public void testAnswer_ValidInput_Yes() {
    question.answer("Yes");
    assertEquals("Yes", question.getAnswer());
  }

  /**
   * Tests the {@link YesNo#answer(String)} method with a valid input ("No").
   */
  @Test
  public void testAnswer_ValidInput_No() {
    question.answer("No");
    assertEquals("No", question.getAnswer());
  }

  /**
   * Tests the {@link YesNo#answer(String)} method with an invalid input.
   * Expects an {@link IllegalArgumentException} to be thrown.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnswer_InvalidInput() {
    question.answer("Maybe");
  }

  /**
   * Tests the {@link YesNo#getAnswer()} method.
   */
  @Test
  public void testGetAnswer() {
    assertEquals("", question.getAnswer());
  }

  /**
   * Tests the {@link YesNo#copy()} method.
   */
  @Test
  public void testCopy() {
    question.answer("Yes");
    Question copy = question.copy();
    assertEquals(question.getPrompt(), copy.getPrompt());
    assertEquals(question.isRequired(), copy.isRequired());
    assertEquals(question.getAnswer(), copy.getAnswer());
  }
}
