package org.fest.assertions.error;

import static org.junit.Assert.assertEquals;

import org.fest.assertions.internal.TestDescription;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link ShouldBeExecutable}</code>.
 * 
 * @author Olivier Demeijer
 * 
 */

public class ShouldBeExecutableTest {
  ErrorMessageFactory factory;

  @Before public void setup() {
    factory = ShouldBeExecutable.shouldBeExecutable(new FakeFile("pathname"));
  }

  @Test public void createExpectedMessage() {
    String actualMessage = factory.create(new TestDescription("Test"));
    assertEquals("[Test] File:<pathname> should be executable", actualMessage);
  }
}
