package uk.org.landeg.projecteuler;

import org.junit.Assert;
import org.junit.Test;

public class RomanNumberalTest {
  @Test
  public void assertDescendingNumerals() {
    Assert.assertEquals(4, RomanNumeral.evaluate("IV"));
  }

  @Test
  public void assertToStringResults14() {
    Assert.assertEquals("XIV", RomanNumeral.toString(14));
  }

  @Test
  public void assertToStringResults19() {
    Assert.assertEquals("XIX", RomanNumeral.toString(19));
  }

  @Test
  public void assertToStringResults49() {
    Assert.assertEquals("XLIX", RomanNumeral.toString(49));
  }
  
  @Test
  public void assertToStringResults1606 () {
    Assert.assertEquals("MDCVI", RomanNumeral.toString(1606));
    
  }
  
  @Test
  public void assertEvaluate5 () {
    Assert.assertEquals(5, RomanNumeral.evaluate("V"));
  }
  @Test
  public void assertEvaluates4 () {
    Assert.assertEquals(4, RomanNumeral.evaluate("IV"));
  }

  @Test
  public void assertMinimalisation() {
    final String input = "IIIIIIIIIIIIIIII";
    Assert.assertEquals("XVI", RomanNumeral.toString(RomanNumeral.evaluate(input)));
  }
}
