/*
 * Created on Aug 5, 2010
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
 * Copyright @2010-2012 the original author or authors.
 */
package org.fest.assertions.error;

import static org.fest.util.Lists.newArrayList;
import static org.fest.util.Objects.HASH_CODE_PRIME;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Objects.hashCodeFor;
import static org.fest.util.ToString.toStringOf;

import java.util.List;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Failures;
import org.fest.util.VisibleForTesting;

/**
 * Creates an {@link AssertionError} indicating that an assertion that verifies that two objects are equal failed.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author Joel Costigliola
 */
public class NotEqualErrorFactory implements AssertionErrorFactory {
  private static final Class<?>[] MSG_ARG_TYPES = new Class<?>[] { String.class, String.class, String.class };
  private static final String DETAILED_TO_STRING_FORMAT = "%s (@%d)";

  @VisibleForTesting
  ConstructorInvoker constructorInvoker = new ConstructorInvoker();

  @VisibleForTesting
  MessageFormatter messageFormatter = MessageFormatter.instance();

  @VisibleForTesting
  DescriptionFormatter descriptionFormatter = DescriptionFormatter.instance();

  private final Object actual;
  private final Object expected;

  /**
   * Creates a new {@link NotEqualErrorFactory}.
   *
   * @param actual the <em>actual</em> value in the failed assertion.
   * @param expected the <em>expected value</em> in the failed assertion.
   * @return the created {@code NotEqualErrorFactory}.
   */
  public static NotEqualErrorFactory shouldBeEqual(Object actual, Object expected) {
    return new NotEqualErrorFactory(actual, expected);
  }

  @VisibleForTesting
  NotEqualErrorFactory(Object actual, Object expected) {
    this.actual = actual;
    this.expected = expected;
  }

  /**
   * Creates an {@link AssertionError} indicating that an assertion that verifies that two objects are equal, failed.
   * <p>
   * If JUnit 4 is in the classpath, this method will instead create a {@code org.junit.ComparisonFailure} that
   * highlights the difference(s) between the expected and actual objects.
   * </p>
   *
   * @param description the description of the failed assertion.
   * @return the created {@code AssertionError}.
   */
  @Override
  public AssertionError newAssertionError(Description description) {
    AssertionError error = comparisonFailure(description);
    if (error != null) {
      return error;
    }
    return Failures.instance().failure(defaultErrorMessage(description));
  }

  private String defaultErrorMessage(Description d) {
    return messageFormatter.format(d, "expected:<%s> but was:<%s>", expected, actual);
  }

  private AssertionError comparisonFailure(Description description) {
    try {
      AssertionError comparisonFailure = newComparisonFailure(descriptionFormatter.format(description).trim());
      Failures.instance().removeFestRelatedElementsFromStackTraceIfNeeded(comparisonFailure);
      return comparisonFailure;
    } catch (Throwable e) {
      return null;
    }
  }

  private AssertionError newComparisonFailure(String description) throws Exception {
    Object o = constructorInvoker.newInstance("org.junit.ComparisonFailure", MSG_ARG_TYPES, msgArgs(description));
    if (o instanceof AssertionError) {
      return (AssertionError) o;
    }
    return null;
  }

  private Object[] msgArgs(String description) {
    List<Object> args = newArrayList();
    args.add(description);
    args.addAll(expectedAndActualToString());
    return args.toArray();
  }

  private List<String> expectedAndActualToString() {
    String e = toStringOf(expected);
    String a = toStringOf(actual);
    if (e != null && e.equals(a)) {
      e = String.format(DETAILED_TO_STRING_FORMAT, e, System.identityHashCode(expected));
      a = String.format(DETAILED_TO_STRING_FORMAT, a, System.identityHashCode(actual));
    }
    return newArrayList(e, a);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null) {
      return false;
    }
    if (getClass() != o.getClass()) {
      return false;
    }
    NotEqualErrorFactory other = (NotEqualErrorFactory) o;
    if (!areEqual(actual, other.actual)) {
      return false;
    }
    return areEqual(expected, other.expected);
  }

  @Override
  public int hashCode() {
    int result = 1;
    result = HASH_CODE_PRIME * result + hashCodeFor(actual);
    result = HASH_CODE_PRIME * result + hashCodeFor(expected);
    return result;
  }
}
