package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldBeWritable.shouldBeWritable;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link Files#assertCanWrite(AssertionInfo, File)}</code>.
 * 
 * @author Olivier Demeijer
 * 
 */
public class Files_assertCanWrite_Test {
  @Rule public ExpectedException thrown = none();

  private File actual;
  private Failures failures;
  private Files files;

  @Before public void setUp() {
    actual = mock(File.class);
    failures = spy(new Failures());
    files = new Files();
    files.failures = failures;
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    files.assertCanWrite(someInfo(), null);
  }

  @Test public void should_fail_if_can_not_write() {
    when(actual.canWrite()).thenReturn(false);
    AssertionInfo info = someInfo();
    try {
      files.assertCanWrite(info, actual);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeWritable(actual));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test public void should_pass_if_actual_can_write() {
    when(actual.canWrite()).thenReturn(true);
    files.assertCanWrite(someInfo(), actual);
  }

}
