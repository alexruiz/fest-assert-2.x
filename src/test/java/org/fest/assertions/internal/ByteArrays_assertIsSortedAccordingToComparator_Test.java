/*
 * Created on Nov 29, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ShouldBeSorted.shouldBeSortedAccordingToGivenComparator;
import static org.fest.assertions.test.ByteArrayFactory.emptyArray;
import static org.fest.assertions.test.ExpectedException.none;
import static org.fest.assertions.test.FailureMessages.actualIsNull;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;

import static org.mockito.Mockito.*;

import java.util.Comparator;

import org.junit.*;

import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.test.ExpectedException;

/**
 * Tests for <code>{@link ByteArrays#assertIsSortedAccordingToComparator(AssertionInfo, byte[], Comparator)}</code>
 * 
 * @author Joel Costigliola
 */
public class ByteArrays_assertIsSortedAccordingToComparator_Test {

  @Rule
  public ExpectedException thrown = none();

  private Failures failures;
  private byte[] actual;
  private ByteArrays arrays;
  private Comparator<Byte> byteDescendingOrderComparator;
  private Comparator<Byte> byteAscendingOrderComparator;

  @Before
  public void setUp() {
    failures = spy(new Failures());
    actual = new byte[] { 4, 3, 2, 2, 1 };
    arrays = new ByteArrays();
    arrays.failures = failures;
    byteDescendingOrderComparator = new Comparator<Byte>() {
      public int compare(Byte byte1, Byte byte2) {
        return -byte1.compareTo(byte2);
      }
    };
    byteAscendingOrderComparator = new Comparator<Byte>() {
      public int compare(Byte byte1, Byte byte2) {
        return byte1.compareTo(byte2);
      }
    };
  }

  @Test
  public void should_pass_if_actual_is_sorted_according_to_given_comparator() {
    arrays.assertIsSortedAccordingToComparator(someInfo(), actual, byteDescendingOrderComparator);
  }

  @Test
  public void should_pass_if_actual_is_empty_whatever_given_comparator_is() {
    arrays.assertIsSortedAccordingToComparator(someInfo(), emptyArray(), byteDescendingOrderComparator);
    arrays.assertIsSortedAccordingToComparator(someInfo(), emptyArray(), byteAscendingOrderComparator);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    arrays.assertIsSortedAccordingToComparator(someInfo(), null, byteDescendingOrderComparator);
  }

  @Test
  public void should_fail_if_comparator_is_null() {
    thrown.expect(NullPointerException.class);
    arrays.assertIsSortedAccordingToComparator(someInfo(), emptyArray(), null);
  }

  @Test
  public void should_fail_if_actual_is_not_sorted_according_to_given_comparator() {
    AssertionInfo info = someInfo();
    actual = new byte[] { 3, 2, 1, 9 };
    try {
      arrays.assertIsSortedAccordingToComparator(info, actual, byteDescendingOrderComparator);
    } catch (AssertionError e) {
      verify(failures)
          .failure(info, shouldBeSortedAccordingToGivenComparator(2, actual, byteDescendingOrderComparator));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }

}
