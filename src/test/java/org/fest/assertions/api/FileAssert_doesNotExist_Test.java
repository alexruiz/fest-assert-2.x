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
package org.fest.assertions.api;

import static org.fest.assertions.test.FakeFile.newNonExistingResource;
import static org.fest.test.ExpectedException.none;
import static org.junit.Assert.assertSame;

import java.io.File;

import org.fest.assertions.test.FakeFile;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for {@link FileAssert#doesNotExist()}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FileAssert_doesNotExist_Test {
  @Rule
  public ExpectedException thrown = none();
  private File actual = newNonExistingResource("none existing file");
  private FileAssert assertions;

  @Before
  public void setUp() {
    assertions = new FileAssert(actual);
  }

  @Test
  public void should_pass_that_actual_does_not_exist() {
    assertions.doesNotExist();
  }

  @Test
  public void should_return_this() {
    FileAssert returned = assertions.doesNotExist();
    assertSame(assertions, returned);
  }

  @Test
  public void should_throw_error_if_actual_is_null() {
    thrown.expect(AssertionError.class);
    assertions = new FileAssert(null);
    assertions.doesNotExist();
  }

  @Test
  public void should_fail_if_actual_exists() {
    thrown.expect(AssertionError.class);
    actual = FakeFile.newWritableFile("c:\test.text");
    assertions = new FileAssert(actual);
    assertions.doesNotExist();
  }
}
