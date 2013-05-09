/*
 * Created on Nov 25, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.ObjectArrays.emptyArray;
import static org.fest.assertions.test.ObjectArrays.newArray;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Tests for <code>{@link ObjectArrays#assertNotEmpty(Description, Object[])}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ObjectArrays_assertNotEmpty_Test {

  @Rule
  public ExpectedException thrown = none();

  private ObjectArrays arrays;
  private Failures failures;
  private Description description;

  @Before
  public void setUp() {
    arrays = ObjectArrays.instance();
    Arrays parent = Arrays.instance();
    failures = spy(new Failures());
    parent.failures = failures;
    description = new TestDescription("testing");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, actualIsNull());
    arrays.assertNotEmpty(description, null);
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    try {
      arrays.assertNotEmpty(description, emptyArray());
    } catch (AssertionError e) {
      verify(failures).failure(description, shouldNotBeEmpty());
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_is_not_empty() {
    arrays.assertNotEmpty(description, newArray("Yoda"));
  }
}
