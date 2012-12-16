/*
 * Created on Aug 4, 2010
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

import static org.fest.assertions.error.NotEqualErrorFactory.shouldBeEqual;
import static org.fest.assertions.error.ShouldNotBeEqual.shouldNotBeEqual;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Preconditions.checkNotNull;

import java.util.Comparator;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.BasicErrorMessageFactory;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for {@code Object}s.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Nicolas François
 */
public class Objects {
  private static final Objects INSTANCE = new Objects();

  public static Objects instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  PropertySupport propertySupport = PropertySupport.instance();

  @VisibleForTesting
  Objects() {}

  /**
   * Asserts that the <em>actual</em> object is {@code null}.
   *
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> object.
   * @throws AssertionError if the <em>actual</em> object is not {@code null}.
   */
  public void assertNull(Description description, Object actual) {
    assertEqual(description, actual, null);
  }

  /**
   * Asserts that the <em>actual</em> object is not {@code null}.
   *
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> object.
   * @throws AssertionError if the <em>actual</em> object is {@code null}.
   */
  public void assertNotNull(Description description, Object actual) {
    if (actual == null) {
      throw failures.failure(description, new BasicErrorMessageFactory("expecting actual value to be non-null"));
    }
  }

  /**
   * Asserts that two objects are equal.
   *
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> object.
   * @param expected the <em>expected</em> object.
   * @throws AssertionError if {@code actual} is not equal to {@code expected}. This method will throw a
   *         {@code org.junit.ComparisonFailure} instead if JUnit is in the classpath and the given objects are not
   *         equal.
   */
  public void assertEqual(Description description, Object actual, Object expected) {
    if (!areEqual(actual, expected)) {
      throw shouldBeEqual(actual, expected).newAssertionError(description);
    }
  }

  /**
   * Asserts that the given {@code Comparator} indicates that the given objects are equal.
   * <p>
   * <strong>Warning:</strong> it is recommended to use {@link #assertEqual(Description, Object, Object)} instead, given
   * that a {@link Comparator} should be consistent to {@link Object#equals(Object)}. Use this method with caution.
   * </p>
   *
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> object.
   * @param expected the <em>expected</em> object.
   * @param comparator the given {@code Comparator}.
   * @throws NullPointerException if the given {@code Comparator} is {@code null}.
   * @throws AssertionError if the given {@code Comparator} indicates that {@code actual} is not equal to
   *         {@code expected}.
   */
  public void assertEqual(Description description, Object actual, Object expected, Comparator<Object> comparator) {
    checkNotNull(comparator);
    if (comparator.compare(actual, expected) != 0) {
      String format = "expected:<%s> but was:<%s>, using comparator:<%s>";
      throw failures.failure(
          description, new BasicErrorMessageFactory(format, expected, actual, comparator.getClass()));
    }
  }

  /**
   * Asserts that two objects are not equal.
   *
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> object.
   * @param other the object to compare {@code actual} to.
   * @throws AssertionError if {@code actual} is equal to {@code other}.
   */
  public void assertNotEqual(Description description, Object actual, Object other) {
    if (areEqual(actual, other)) {
      throw failures.failure(description, shouldNotBeEqual(actual, other));
    }
  }

  /**
   * Asserts that two objects refer to the same instance.
   *
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> object.
   * @param expected the <em>expected</em> object.
   * @throws AssertionError if the given objects do not refer to the same instance.
   */
  public void assertSame(Description description, Object actual, Object expected) {
    if (actual != expected) {
      String format = "expected:<%s> and actual:<%s> should refer to the same instance";
      throw failures.failure(description, new BasicErrorMessageFactory(format, expected, actual));
    }
  }

  /**
   * Asserts that two objects do not refer to the same instance.
   *
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> object.
   * @param other the object to compare {@code actual} to.
   * @throws AssertionError if the given objects refer to the same instance.
   */
  public void assertNotSame(Description description, Object actual, Object other) {
    if (actual == other) {
      String format = "expected not same:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual));
    }
  }

  /**
   * Asserts that the <em>actual</em> object is an instance of the given type.
   *
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> object.
   * @param type the type to check the <em>actual</em> object against.
   * @throws NullPointerException if the given type is {@code null}.
   * @throws AssertionError if the <em>actual</em> object is {@code null}.
   * @throws AssertionError if the <em>actual</em> object is not an instance of the given type.
   */
  public void assertIsInstanceOf(Description description, Object actual, Class<?> type) {
    checkNotNull(type);
    assertNotNull(description, actual);
    if (!type.isInstance(actual)) {
      String format = "expecting <%s> to be an instance of:<%s> but was instance of:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, type, actual.getClass()));
    }
  }

  /**
   * Asserts that the <em>actual</em> object is not an instance of the given type.
   *
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> object.
   * @param type the type to check the <em>actual</em> object against.
   * @throws NullPointerException if the given type is {@code null}.
   * @throws AssertionError if the <em>actual</em> object is an instance of the given type.
   */
  public void assertIsNotInstanceOf(Description description, Object actual, Class<?> type) {
    checkNotNull(type);
    if (type.isInstance(actual)) {
      String format = "expecting <%s> not to be an instance of:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, type));
    }
  }

  /**
   * Verifies that the <em>actual</em> object is an instance of any of the given types.
   *
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> object.
   * @param types the types to check the <em>actual</em> object against.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws NullPointerException if the given array has {@code null} elements.
   * @throws AssertionError if the <em>actual</em> object is {@code null}.
   * @throws AssertionError if the <em>actual</em> object is not an instance of any of the given types.
   */
  public void assertIsInstanceOfAny(Description description, Object actual, Class<?>[] types) {
    checkNotNullOrEmpty(types);
    assertNotNull(description, actual);
    if (!isInstanceOfAny(actual, types)) {
      String format = "expecting <%s> to be an instance of any of:<%s> but was instance of:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, types, actual.getClass()));
    }
  }

  /**
   * Verifies that the <em>actual</em> object is not an instance of any of the given types.
   *
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> object.
   * @param types the types to check the <em>actual</em> object against.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws NullPointerException if the given array has {@code null} elements.
   * @throws AssertionError if the <em>actual</em> object is an instance of any of the given types.
   */
  public void assertIsNotInstanceOfAny(Description description, Object actual, Class<?>[] types) {
    checkNotNullOrEmpty(types);
    if (isInstanceOfAny(actual, types)) {
      String format = "expecting <%s> not to be an instance of any of:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, types));
    }
  }

  private void checkNotNullOrEmpty(Class<?>[] types) {
    checkNotNull(types);
    if (types.length == 0) {
      throw new IllegalArgumentException();
    }
  }

  private boolean isInstanceOfAny(Object actual, Class<?>[] types) {
    for (Class<?> type : types) {
      type = checkNotNull(type);
      if (type.isInstance(actual)) {
        return true;
      }
    }
    return false;
  }
}
