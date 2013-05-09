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
 * Copyright @2011-2013 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.FakeFile.newNonExistingResource;
import static org.fest.assertions.test.FakeFile.newWritableFile;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.ErrorMessageFactory;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link Files#assertDoesNotExist(Description, File)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Files_assertDoesNotExist_Test {
  @Rule
  public ExpectedException thrown = none();

  private Failures failures;
  private Files files;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    files = new Files();
    files.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    files.assertDoesNotExist(mock(Description.class), null);
  }

  @Test
  public void should_pass_if_actual_does_not_exist() {
    File actual = newNonExistingResource("/usr/local/actual.txt");
    files.assertDoesNotExist(mock(Description.class), actual);
  }

  @Test
  public void should_fail_if_actual_exists() {
    Description description = new TestDescription("Testing");
    File actual = newWritableFile("/usr/local/actual.txt");
    try {
      files.assertDoesNotExist(description, actual);
    } catch (AssertionError e) {
      assertEquals("[Testing] expecting resource in path:</usr/local/actual.txt> not to exist", e.getMessage());
      verify(failures).failure(same(description), any(ErrorMessageFactory.class));
      return;
    }
    expectedAssertionErrorNotThrown();
  }
}
