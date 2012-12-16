/*
 * Created on Jul 15, 2010
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
package org.fest.assertions.internal;

import static org.fest.util.Preconditions.checkNotNull;

import org.fest.assertions.core.Matcher;
import org.fest.assertions.description.Description;
import org.fest.assertions.error.BasicErrorMessageFactory;
import org.fest.util.VisibleForTesting;

/**
 * Verifies that a value satisfies a {@link Matcher}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Matchers {
  private static final Matchers INSTANCE = new Matchers();

  public static Matchers instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  Matchers() {}

  /**
   * Asserts that the <em>actual</em> value satisfies the given {@link Matcher}.
   *
   * @param <T> the type of the actual value and the type of values that given {@code Matcher} takes.
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> value.
   * @param matcher the given {@code Matcher}.
   * @throws NullPointerException if the given {@code Matcher} is {@code null}.
   * @throws AssertionError if the <em>actual</em> value does not satisfy the given {@code Matcher}.
   */
  public <T> void assertIs(Description description, T actual, Matcher<? super T> matcher) {
    checkNotNull(matcher);
    if (!matcher.matches(actual)) {
      String format = "expecting:<%s> to be:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, matcher));
    }
  }

  /**
   * Asserts that the <em>actual</em> value does not satisfy the given {@link Matcher}.
   *
   * @param <T> the type of the <em>actual</em> value and the type of values that given {@code Matcher} takes.
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> value.
   * @param matcher the given {@code Matcher}.
   * @throws NullPointerException if the given {@code Matcher} is {@code null}.
   * @throws AssertionError if the <em>actual</em> value satisfies the given {@code Matcher}.
   */
  public <T> void assertIsNot(Description description, T actual, Matcher<? super T> matcher) {
    checkNotNull(matcher);
    if (matcher.matches(actual)) {
      String format = "expecting:<%s> not to be:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, matcher));
    }
  }

  /**
   * Asserts that the <em>actual</em> value satisfies the given {@link Matcher}.
   *
   * @param <T> the type of the <em>actual</em> value and the type of values that given {@code Matcher} takes.
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> value.
   * @param matcher the given {@code Matcher}.
   * @throws NullPointerException if the given {@code Matcher} is {@code null}.
   * @throws AssertionError if the <em>actual</em> value does not satisfy the given {@code Matcher}.
   */
  public <T> void assertHas(Description description, T actual, Matcher<? super T> matcher) {
    checkNotNull(matcher);
    if (!matcher.matches(actual)) {
      String format = "expecting:<%s> to have:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, matcher));
    }
  }

  /**
   * Asserts that the <em>actual</em> value does not satisfy the given {@link Matcher}.
   *
   * @param <T> the type of the <em>actual</em> value and the type of values that given {@code Matcher} takes.
   * @param description the description of the <em>actual</em> object.
   * @param actual the <em>actual</em> value.
   * @param matcher the given {@code Matcher}.
   * @throws NullPointerException if the given {@code Matcher} is {@code null}.
   * @throws AssertionError if the <em>actual</em> value satisfies the given {@code Matcher}.
   */
  public <T> void assertDoesNotHave(Description description, T actual, Matcher<? super T> matcher) {
    if (matcher.matches(actual)) {
      String format = "expecting:<%s> not to have:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, matcher));
    }
  }
}
