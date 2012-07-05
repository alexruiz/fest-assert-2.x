/*
 * Created on Jan 29, 2011
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

import static org.fest.assertions.error.ShouldBeRelativePath.shouldBeRelativePath;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.mockito.Mockito.*;

import java.io.File;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;
import org.junit.*;

/**
 * Tests for <code>{@link Files#assertIsRelative(AssertionInfo, File)}</code>.
 *
 * @author Yvonne Wang
 */
public class Files_assertIsRelative_Test {

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
    files.assertIsRelative(someInfo(), null);
  }

  @Test public void should_fail_if_actual_is_not_relative_path() {
    when(actual.isAbsolute()).thenReturn(true);
    AssertionInfo info = someInfo();
    try {
      files.assertIsRelative(info, actual);
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeRelativePath(actual));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

  @Test public void should_pass_if_actual_is_relative_path() {
    when(actual.isAbsolute()).thenReturn(false);
    files.assertIsRelative(someInfo(), actual);
  }
}
