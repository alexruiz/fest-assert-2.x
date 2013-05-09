/*
 * Created on Dec 22, 2010
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

import static org.fest.assertions.error.ShouldBeEmpty.shouldBeEmpty;
import static org.fest.assertions.error.ShouldBeNullOrEmpty.shouldBeNullOrEmpty;
import static org.fest.assertions.error.ShouldEndWith.shouldEndWith;
import static org.fest.assertions.error.ShouldHaveSize.shouldHaveSize;
import static org.fest.assertions.error.ShouldNotBeEmpty.shouldNotBeEmpty;
import static org.fest.assertions.error.ShouldStartWith.shouldStartWith;
import static org.fest.util.Preconditions.checkNotNull;
import static org.fest.util.Strings.isNullOrEmpty;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.BasicErrorMessageFactory;
import org.fest.util.InternalApi;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for {@link String}s.
 *
 * @author Alex Ruiz
 */
@InternalApi
public class Strings {
  private static final Strings INSTANCE = new Strings();

  public static @Nonnull Strings instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  Strings() {}

  /**
   * Asserts that the <em>actual</em> {@code String} is {@code null} or empty.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @throws AssertionError if the <em>actual</em> {@code String} is not {@code null} and not empty.
   */
  public void assertNullOrEmpty(@Nullable Description description, @Nullable String actual) {
    if (!isNullOrEmpty(actual)) {
      throw failures.failure(description, shouldBeNullOrEmpty(actual));
    }
  }

  /**
   * Asserts that the <em>actual</em> {@code String} is empty.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is not empty.
   */
  public void assertEmpty(@Nullable Description description, @Nullable String actual) {
    assertNotNull(description, actual);
    if (!actual.isEmpty()) {
      throw failures.failure(description, shouldBeEmpty(actual));
    }
  }

  /**
   * Asserts that the <em>actual</em> {@code String} is not empty.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is empty.
   */
  public void assertNotEmpty(@Nullable Description description, @Nullable String actual) {
    assertNotNull(description, actual);
    if (actual.isEmpty()) {
      throw failures.failure(description, shouldNotBeEmpty());
    }
  }

  /**
   * Asserts that the size of the <em>actual</em> {@code String} is equal to the expected one.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @param expectedSize the expected size of {@code actual}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the size of the <em>actual</em> {@code String} is different than the expected one.
   */
  public void assertHasSize(@Nullable Description description, @Nullable String actual, int expectedSize) {
    assertNotNull(description, actual);
    int actualSize = actual.length();
    if (actualSize != expectedSize) {
      throw failures.failure(description, shouldHaveSize(actual, actualSize, expectedSize));
    }
  }

  /**
   * Verifies that two {@code String}s are equal, ignoring case considerations.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @param expected the expected {@code String}.
   * @throws AssertionError if the given {@code String}s are not equal.
   */
  public void assertEqualsIgnoringCase(@Nullable Description description, @Nullable String actual,
      @Nullable String expected) {
    if (!areEqualIgnoringCase(actual, expected)) {
      String format = "expecting:<%s> to be equal to:<%s>, ignoring case considerations";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, expected));
    }
  }

  private boolean areEqualIgnoringCase(String actual, String expected) {
    if (actual == null) {
      return expected == null;
    }
    return actual.equalsIgnoreCase(expected);
  }

  /**
   * Verifies that the <em>actual</em> {@code String} contains the given sequence.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @param sequence the sequence to look for.
   * @throws NullPointerException if the given sequence is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} does not contain the given sequence.
   */
  public void assertContains(@Nullable Description description, @Nullable String actual, @Nonnull String sequence) {
    checkNotNull(sequence);
    assertNotNull(description, actual);
    if (!actual.contains(sequence)) {
      String format = "expecting:<%s> to contain:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, sequence));
    }
  }

  /**
   * Verifies that the <em>actual</em> {@code String} does not contain the given sequence.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @param sequence the sequence to look for.
   * @throws NullPointerException if the given sequence is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} contains the given sequence.
   */
  public void assertDoesNotContain(@Nullable Description description, @Nullable String actual,
      @Nonnull String sequence) {
    checkNotNull(sequence);
    assertNotNull(description, actual);
    if (actual.contains(sequence)) {
      String format = "expecting:<%s> not to contain:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, sequence));
    }
  }

  /**
   * Verifies that the <em>actual</em> {@code String} starts with the given prefix.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @param prefix the given prefix.
   * @throws NullPointerException if the given sequence is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} does not start with the given prefix.
   */
  public void assertStartsWith(@Nullable Description description, @Nullable String actual, @Nonnull String prefix) {
    checkNotNull(prefix);
    assertNotNull(description, actual);
    if (!actual.startsWith(prefix)) {
      throw failures.failure(description, shouldStartWith(actual, prefix));
    }
  }

  /**
   * Verifies that the <em>actual</em> {@code String} ends with the given suffix.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @param suffix the given suffix.
   * @throws NullPointerException if the given sequence is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} does not end with the given suffix.
   */
  public void assertEndsWith(@Nullable Description description, @Nullable String actual, @Nonnull String suffix) {
    checkNotNull(suffix);
    assertNotNull(description, actual);
    if (!actual.endsWith(suffix)) {
      throw failures.failure(description, shouldEndWith(actual, suffix));
    }
  }

  /**
   * Verifies that the <em>actual</em> {@code String} matches the given regular expression.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @param regex the regular expression to which the <em>actual</em> {@code String} is to be matched.
   * @throws NullPointerException if the given regular expression is {@code null}.
   * @throws PatternSyntaxException if the regular expression's syntax is invalid.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} does not match the given regular expression.
   */
  public void assertMatches(@Nullable Description description, @Nullable String actual, @Nonnull String regex) {
    checkNotNull(regex);
    assertMatches(description, actual, Pattern.compile(regex));
  }

  /**
   * Verifies that the <em>actual</em> {@code String} matches the given regular expression.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @param pattern the compiled representation of the regular expression pattern to which the <em>actual</em>
   *        {@code String} is to be matched.
   * @throws NullPointerException if the given pattern is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} does not match the given regular expression.
   */
  public void assertMatches(@Nullable Description description, @Nullable String actual, @Nonnull Pattern pattern) {
    checkNotNull(pattern);
    assertNotNull(description, actual);
    if (!pattern.matcher(actual).matches()) {
      String format = "expecting:<%s> to match regular expression:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, pattern.pattern()));
    }
  }

  /**
   * Verifies that the <em>actual</em> {@code String} does not match the given regular expression.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @param regex the regular expression to which the <em>actual</em> {@code String} is to be matched.
   * @throws NullPointerException if the given pattern is {@code null}.
   * @throws PatternSyntaxException if the regular expression's syntax is invalid.
   * @throws AssertionError if the <em>actual</em> {@code String} matches the given regular expression.
   */
  public void assertDoesNotMatch(@Nullable Description description, @Nullable String actual, @Nonnull String regex) {
    checkNotNull(regex);
    assertDoesNotMatch(description, actual, Pattern.compile(regex));
  }

  /**
   * Verifies that the <em>actual</em> {@code String} does not match the given regular expression.
   *
   * @param description the description of the <em>actual</em> {@code String}.
   * @param actual the <em>actual</em> {@code String}.
   * @param pattern the compiled representation of the regular expression pattern to which the <em>actual</em>
   *        {@code String} is to be matched.
   * @throws NullPointerException if the given pattern is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} matches the given regular expression.
   */
  public void assertDoesNotMatch(@Nullable Description description, @Nullable String actual, @Nonnull Pattern pattern) {
    checkNotNull(pattern);
    if (actual != null && pattern.matcher(actual).matches()) {
      String format = "expecting:<%s> not to match regular expression:<%s>";
      throw failures.failure(description, new BasicErrorMessageFactory(format, actual, pattern.pattern()));
    }
  }

  private void assertNotNull(@Nullable Description description, @Nullable String actual) {
    Objects.instance().assertNotNull(description, actual);
  }
}
