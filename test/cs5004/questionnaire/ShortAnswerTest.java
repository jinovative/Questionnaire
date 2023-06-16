package cs5004.questionnaire;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Tests for the {@link ShortAnswer} class.
 */
public class ShortAnswerTest {

  @Test
  public void testConstructor_ValidArguments() {
    ShortAnswer shortAnswer = new ShortAnswer("Sample Short Answer Question", true);

    assertEquals("Sample Short Answer Question", shortAnswer.getPrompt());
    assertTrue(shortAnswer.isRequired());
    assertEquals("", shortAnswer.getAnswer());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_NullPrompt() {
    new ShortAnswer(null, true); //Invalid input
  }

  @Test
  public void testAnswer_ValidInput() {
    ShortAnswer shortAnswer = new ShortAnswer("Sample Short Answer Question", true);

    shortAnswer.answer("Valid Answer");
    assertEquals("Valid Answer", shortAnswer.getAnswer());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnswer_NullAnswer() {
    ShortAnswer shortAnswer = new ShortAnswer("Sample Short Answer Question", true);

    shortAnswer.answer(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnswer_InvalidAnswer() { //Invalid input
    ShortAnswer shortAnswer = new ShortAnswer("Sample Short Answer Question", true);

    shortAnswer.answer("This is a long answer that exceeds the maximum limit of 280 characters");
  }

  @Test
  public void testCopy() {
    ShortAnswer shortAnswer = new ShortAnswer("Sample Short Answer Question", true);
    shortAnswer.answer("Valid Answer");

    Question copy = shortAnswer.copy();
    assertTrue(copy instanceof ShortAnswer);
    assertEquals(shortAnswer.getPrompt(), copy.getPrompt());
    assertEquals(shortAnswer.isRequired(), copy.isRequired());
    assertEquals(shortAnswer.getAnswer(), copy.getAnswer());
  }

  @Test
  public void testGetPrompt() {
    ShortAnswer shortAnswer = new ShortAnswer("Sample Short Answer Question", true);

    assertEquals("Sample Short Answer Question", shortAnswer.getPrompt());
  }

  @Test
  public void testIsRequired() {
    ShortAnswer shortAnswer = new ShortAnswer("Sample Short Answer Question", true);

    assertTrue(shortAnswer.isRequired());
  }

  @Test
  public void testGetAnswer() {
    ShortAnswer shortAnswer = new ShortAnswer("Sample Short Answer Question", true);
    shortAnswer.answer("Valid Answer");

    assertEquals("Valid Answer", shortAnswer.getAnswer());
  }
}
