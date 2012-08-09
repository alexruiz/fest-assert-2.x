package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldContainsOnlyOnce.shouldContainsOnlyOnce;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.mockito.Mockito.verify;

import org.fest.assertions.core.AssertionInfo;
import org.junit.Test;

public class Strings_assertContainsOnlyOnce_Test extends AbstractTest_for_Strings {
  
  @Test
  public void should_fail_if_actual_contains_more_than_once_given_string() {
    AssertionInfo info = someInfo();
    try {
      strings.assertContainsOnlyOnce(info, "Yodayoda", "oda");
    } catch (AssertionError e) {
      verifyFailureThrownWhenDoesNotContainsOnlyOnce(info, "Yodayoda", "oda", 2);
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
  
  @Test
  public void should_fail_if_actual_does_not_contains_given_string() {
    AssertionInfo info = someInfo();
    try {
      strings.assertContainsOnlyOnce(info, "Yoda", "Luke");
    } catch (AssertionError e) {
      verifyFailureThrownWhenDoesNotContainsOnlyOnce(info, "Yoda", "Luke", 0);
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
	
  @Test
  public void should_fail_if_both_Strings_are_null() {
	AssertionInfo info = someInfo();
    try {
    	strings.assertContainsOnlyOnce(someInfo(), null, null);
      } catch (AssertionError e) {
        verifyFailureThrownWhenDoesNotContainsOnlyOnce(info, null, null, 0);
        return;
      }
      failBecauseExpectedAssertionErrorWasNotThrown();
  }

private void verifyFailureThrownWhenDoesNotContainsOnlyOnce(AssertionInfo info, String actual, String expected, int occurences) {
    verify(failures).failure(info, shouldContainsOnlyOnce(actual, expected, occurences));
  }
}
