package cs5004.questionnaire;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Tests for the {@link Likert} class.
 */
public class LikertTest {

  @Test
  public void testConstructor_ValidArguments() {
    Likert likert = new Likert("Sample Likert Question", true);

    assertEquals("Sample Likert Question", likert.getPrompt());
    assertTrue(likert.isRequired());
    assertEquals("", likert.getAnswer());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_NullPrompt() {
    new Likert(null, true); //Invalid input
  }

  @Test
  public void testAnswer_ValidOption() {
    Likert likert = new Likert("Sample Likert Question", true);

    likert.answer("Agree");
    assertEquals("agree", likert.getAnswer());

    likert.answer("Strongly Disagree");
    assertEquals("strongly disagree", likert.getAnswer());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnswer_InvalidOption() {
    Likert likert = new Likert("Sample Likert Question", true);

    likert.answer("Invalid Option");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnswer_NullOption() {
    Likert likert = new Likert("Sample Likert Question", true);

    likert.answer(null); //Invalid input
  }

  @Test
  public void testCopy() {
    Likert likert = new Likert("Sample Likert Question", true);
    likert.answer("Agree");

    Question copy = likert.copy();
    assertTrue(copy instanceof Likert);
    assertEquals(likert.getPrompt(), copy.getPrompt());
    assertEquals(likert.isRequired(), copy.isRequired());
    assertEquals(likert.getAnswer(), copy.getAnswer());
  }

  @Test
  public void testGetPrompt() {
    Likert likert = new Likert("Sample Likert Question", true);

    assertEquals("Sample Likert Question", likert.getPrompt());
  }

  @Test
  public void testIsRequired() {
    Likert likert = new Likert("Sample Likert Question", true);

    assertTrue(likert.isRequired());
  }

  @Test
  public void testGetAnswer() {
    Likert likert = new Likert("Sample Likert Question", true);
    likert.answer("Agree");

    assertEquals("agree", likert.getAnswer());
  }
}
