/*
 * Created on Mar 27, 2013
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
 * Copyright @2013 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.BasicErrorMessageFactory;
import org.fest.assertions.error.ShouldHaveSize;
import org.fest.assertions.error.ShouldNotBeNull;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class LongArrays_assertHasSize_Test {

  @Rule
  public ExpectedException thrown = none();

  private LongArrays arrays;
  private Description description;
  private Failures failures;

  @Before
  public void setUp() {
    arrays = new LongArrays();
    description = new TestDescription("testing");
    Arrays parent = Arrays.instance();
    failures = spy(new Failures());
    parent.failures = failures;
  }

  @Test
  public void should_fail_if_actual_is_null() {
    String message = ShouldNotBeNull.shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    arrays.assertHasSize(description, null, 8);
  }

  @Test
  public void should_fail_if_expectedSize_is_negative() {
    try {
      arrays.assertHasSize(description, new long[1], -1);
    } catch (AssertionError e) {
      verify(failures).failure(description, new BasicErrorMessageFactory("The expectedSize should not be negative"));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_does_not_have_expected_size() {
    long[] actual = new long[6];
    try {
      arrays.assertHasSize(description, actual, 8);
    } catch (AssertionError e) {
      verify(failures).failure(description, ShouldHaveSize.shouldHaveSize(actual, actual.length, 8));
      return;
    }
    throw expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_has_expected_size() {
    long[] actual = new long[6];
    arrays.assertHasSize(description, actual, 6);
  }
}
