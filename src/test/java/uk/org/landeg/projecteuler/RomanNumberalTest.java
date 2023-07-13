package uk.org.landeg.projecteuler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RomanNumberalTest {
  @Test
  public void assertDescendingNumerals() {
    assertEquals(4, RomanNumeral.evaluate("IV"));
  }

  @Test
  public void assertToStringResults14() {
    assertEquals("XIV", RomanNumeral.toString(14));
  }

  @Test
  public void assertToStringResults19() {
    assertEquals("XIX", RomanNumeral.toString(19));
  }

  @Test
  public void assertToStringResults49() {
    assertEquals("XLIX", RomanNumeral.toString(49));
  }
  
  @Test
  void assertToStringResults1606() {
    assertEquals("MDCVI", RomanNumeral.toString(1606));
    
  }
  
  @Test
  void assertEvaluate5() {
    assertEquals(5, RomanNumeral.evaluate("V"));
  }
  @Test
  void assertEvaluates4() {
    assertEquals(4, RomanNumeral.evaluate("IV"));
  }

  @Test
  public void assertMinimalisation() {
    final String input = "IIIIIIIIIIIIIIII";
    assertEquals("XVI", RomanNumeral.toString(RomanNumeral.evaluate(input)));
  }
}
