package cs5004.questionnaire;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Tests for the {@link YesNo} class.
 */
public class YesNoTest {

  @Test
  public void testConstructor_ValidArguments() {
    YesNo yesNo = new YesNo("Sample Yes/No Question", true);

    assertEquals("Sample Yes/No Question", yesNo.getPrompt());
    assertTrue(yesNo.isRequired());
    assertEquals("", yesNo.getAnswer());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor_NullPrompt() {
    new YesNo(null, true); //invalid input
  }

  @Test
  public void testAnswer_ValidInput_Yes() {
    YesNo yesNo = new YesNo("Sample Yes/No Question", true);

    yesNo.answer("Yes");
    assertEquals("Yes", yesNo.getAnswer());
  }

  @Test
  public void testAnswer_ValidInput_No() {
    YesNo yesNo = new YesNo("Sample Yes/No Question", true);

    yesNo.answer("No");
    assertEquals("No", yesNo.getAnswer());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAnswer_InvalidAnswer() {
    YesNo yesNo = new YesNo("Sample Yes/No Question", true);

    yesNo.answer("Maybe");
  }

  @Test
  public void testCopy() {
    YesNo yesNo = new YesNo("Sample Yes/No Question", true);
    yesNo.answer("Yes");

    Question copy = yesNo.copy();
    assertTrue(copy instanceof YesNo);
    assertEquals(yesNo.getPrompt(), copy.getPrompt());
    assertEquals(yesNo.isRequired(), copy.isRequired());
    assertEquals(yesNo.getAnswer(), copy.getAnswer());
  }

  @Test
  public void testGetPrompt() {
    YesNo yesNo = new YesNo("Sample Yes/No Question", true);

    assertEquals("Sample Yes/No Question", yesNo.getPrompt());
  }

  @Test
  public void testIsRequired() {
    YesNo yesNo = new YesNo("Sample Yes/No Question", true);

    assertTrue(yesNo.isRequired());
  }

  @Test
  public void testGetAnswer() {
    YesNo yesNo = new YesNo("Sample Yes/No Question", true);
    yesNo.answer("No");

    assertEquals("No", yesNo.getAnswer());
  }
}
