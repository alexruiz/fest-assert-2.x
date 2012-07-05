/*
 * Created on Jan 27, 2011
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static junit.framework.Assert.*;

import static org.fest.assertions.error.ShouldHaveEqualContent.shouldHaveEqualContent;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.fest.util.Collections.list;

import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;

/**
 * Tests for <code>{@link InputStreams#assertEqualContent(AssertionInfo, InputStream, InputStream)}</code>.
 * 
 * @author Matthieu Baechler
 */
public class InputStreams_assertEqualContent_Test {

  private static InputStream actual;
  private static InputStream expected;

  @BeforeClass public static void setUpOnce() {
    actual = new ByteArrayInputStream(new byte[0]);
    expected = new ByteArrayInputStream(new byte[0]);
  }

  @Rule public ExpectedException thrown = none();

  private Diff diff;
  private Failures failures;
  private InputStreams inputStreams;

  @Before public void setUp() {
    diff = mock(Diff.class);
    failures = spy(new Failures());
    inputStreams = new InputStreams();
    inputStreams.diff = diff;
    inputStreams.failures = failures;
  }

  @Test public void should_throw_error_if_expected_is_null() {
    thrown.expectNullPointerException("The InputStream to compare to should not be null");
    inputStreams.assertEqualContent(someInfo(), actual, null);
  }

  @Test public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    inputStreams.assertEqualContent(someInfo(), null, expected);
  }

  @Test public void should_pass_if_inputstreams_have_equal_content() throws IOException {
    when(diff.diff(actual, expected)).thenReturn(new ArrayList<String>());
    inputStreams.assertEqualContent(someInfo(), actual, expected);
  }

  @Test public void should_throw_error_wrapping_catched_IOException() throws IOException {
    IOException cause = new IOException();
    when(diff.diff(actual, expected)).thenThrow(cause);
    try {
      inputStreams.assertEqualContent(someInfo(), actual, expected);
      fail("Expected a InputStreamsException to be thrown");
    } catch (InputStreamsException e) {
      assertSame(cause, e.getCause());
    }
  }

  @Test public void should_fail_if_inputstreams_do_not_have_equal_content() throws IOException {
    List<String> diffs = list("line:1, expected:<line1> but was:<EOF>");
    when(diff.diff(actual, expected)).thenReturn(diffs);
    AssertionInfo info = someInfo();
    try {
      inputStreams.assertEqualContent(info, actual, expected);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldHaveEqualContent(actual, expected, diffs));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
