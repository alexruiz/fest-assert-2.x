package org.fest.assertions.api;

import static junit.framework.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.fest.assertions.internal.Strings;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for <code>{@link StringAssert#containsOnlyOnce(String)}</code>.
 * 
 */
public class StringAssert_containsOnlyOnce_Test {

  private Strings strings;
  private StringAssert assertions;

  @Before
  public void setUp() {
    strings = mock(Strings.class);
    assertions = new StringAssert("Yodayoda");
    assertions.strings = strings;
  }

  @Test
  public void should_verify_that_actual_contains_only_once_given_String() {
    assertions.containsOnlyOnce("dayo");
    verify(strings).assertContainsOnlyOnce(assertions.info, assertions.actual, "dayo");
  }
  
  @Test
  public void should_return_this() {
    StringAssert returned = assertions.containsOnlyOnce("dayo");
    assertSame(assertions, returned);
  }
}