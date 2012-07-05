package org.fest.assertions.error;

import static org.junit.Assert.assertEquals;

import org.fest.assertions.internal.TestDescription;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link ShouldBeReadable}</code>.
 * 
 * @author Olivier Demeijer
 * 
 */
public class ShouldBeReadableTest {
  ErrorMessageFactory factory;

  @Before public void setup() {
    factory = ShouldBeReadable.shouldBeReadable(new FakeFile("pathname"));
  }

  @Test public void createExpectedMessage() {
    String actualMessage = factory.create(new TestDescription("Test"));
    assertEquals("[Test] File:<pathname> should be readable", actualMessage);
  }
}
