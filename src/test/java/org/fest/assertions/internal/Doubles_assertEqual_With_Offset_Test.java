/*
 * Created on Mar 28, 2013
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

import static java.lang.Math.abs;

import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.assertions.test.TestFailures.expectedAssertionErrorNotThrown;
import static org.fest.test.ExpectedException.none;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.data.Offset;
import org.fest.assertions.description.Description;
import org.fest.assertions.error.ShouldBeEqualWithinOffset;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Doubles_assertEqual_With_Offset_Test {

  @Rule
  public ExpectedException thrown = none();

  private Doubles doubles;
  private Description description;
  private Failures failures;
  private Offset<Double> offset;

  @Before
  public void setUp() {
    doubles = new Doubles();
    description = new TestDescription("testing");
    failures = spy(new Failures());
    doubles.failures = failures;
    offset = Offset.offset(0.001);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expect(AssertionError.class, shouldNotBeNull().create(description));
    doubles.assertEqual(description, null, 8d, offset);
  }

  @Test
  public void should_fail_if_offset_is_null() {
    thrown.expect(NullPointerException.class, "The given offset should not be null");
    doubles.assertEqual(description, 6d, 8d, null);
  }

  @Test
  public void should_fail_if_expected_is_null() {
    thrown.expect(NullPointerException.class, "The given number should not be null");
    doubles.assertEqual(description, 8d, null, offset);
  }

  @Test
  public void should_fail_if_actual_is_not_equal_to_expected_within_offset() {
    Double actual = new Double(6.123);
    Double expected = new Double(6.125);
    try {
      doubles.assertEqual(description, actual, expected, offset);
    } catch (AssertionError e) {
      verify(failures).failure(
          description,
          ShouldBeEqualWithinOffset.shouldBeEqual(actual, expected, offset,
              abs(expected.doubleValue() - actual.doubleValue())));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_fail_if_actual_is_not_identical_to_expected_with_offset_is_zero() {
    Double actual = new Double(8.122);
    Double expected = new Double(8.123);
    try {
      doubles.assertEqual(description, actual, expected, Offset.offset(0d));
    } catch(AssertionError e) {
      verify(failures).failure(description, ShouldBeEqualWithinOffset.shouldBeEqual(actual, expected, Offset.offset(0d), abs(expected.doubleValue() - actual.doubleValue())));
      return;
    }
    expectedAssertionErrorNotThrown();
  }

  @Test
  public void should_pass_if_actual_is_equal_to_expected_within_offset() {
    doubles.assertEqual(description, new Double(8.122), new Double(8.123), offset);
  }

  @Test
  public void should_pass_if_actual_is_identical_to_expected_with_offset_is_zero() {
    doubles.assertEqual(description, new Double(8.122), new Double(8.122), Offset.offset(0d));
  }
}
