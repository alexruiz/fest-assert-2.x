package org.fest.assertions.error;

import static junit.framework.Assert.assertEquals;
import static org.fest.assertions.error.ShouldContainsOnlyOnce.shouldContainsOnlyOnce;

import org.fest.assertions.internal.TestDescription;
import org.junit.Before;
import org.junit.Test;

public class ShouldContainsOnlyOnce_Test {

  private ErrorMessageFactory factoryWithSeveralOccurences;
  
  private ErrorMessageFactory factoryWithNoOccurence;

  private ErrorMessageFactory factoryWithBothParameterNull;

  private ErrorMessageFactory factoryWithActualParameterNull;

  private ErrorMessageFactory factoryWithExpectedParameterNull;

  @Before
  public void setUp() {
    factoryWithSeveralOccurences = shouldContainsOnlyOnce("aaamotifmotifaabbbmotifaaa", "motif", 3);
    factoryWithNoOccurence = shouldContainsOnlyOnce("aaamodifmoifaabbbmotfaaa", "motif", 0);
    factoryWithBothParameterNull = shouldContainsOnlyOnce(null, null, 0);
    factoryWithActualParameterNull = shouldContainsOnlyOnce(null, "motif", 0);
    factoryWithExpectedParameterNull = shouldContainsOnlyOnce("actual", null, 0);
  }

  @Test
  public void should_create_error_message_when_expected_string_appears_several_times() {
    String message = factoryWithSeveralOccurences.create(new TestDescription("Test"));
    assertEquals("[Test] expecting:\n<'motif'>\n to appear only once in:\n<'aaamotifmotifaabbbmotifaaa'>\n but appears 3 times",
        message);
  }

  @Test
  public void should_create_error_message_when_expected_string_does_not_appear() {
    String message = factoryWithNoOccurence.create(new TestDescription("Test"));
    assertEquals("[Test] expecting:\n<'motif'>\n to appear only once in:\n<'aaamodifmoifaabbbmotfaaa'>\n but it doesn't appear",
        message);
  }
  
  @Test
  public void should_create_error_message_when_expected_string_is_null() {
    String message = factoryWithExpectedParameterNull.create(new TestDescription("Test"));
    assertEquals("[Test] expecting:<actual> or <pattern> not to be null",
        message);
  }
  
  @Test
  public void should_create_error_message_when_actual_string_is_null() {
    String message = factoryWithActualParameterNull.create(new TestDescription("Test"));
    assertEquals("[Test] expecting:<actual> or <pattern> not to be null",
        message);
  }
  
  @Test
  public void should_create_error_message_when_both_string_are_null() {
    String message = factoryWithBothParameterNull.create(new TestDescription("Test"));
    assertEquals("[Test] expecting:<actual> or <pattern> not to be null",
        message);
  }

}
