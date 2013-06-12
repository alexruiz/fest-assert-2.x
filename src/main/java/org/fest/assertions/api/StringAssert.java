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
package org.fest.assertions.api;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.fest.assertions.core.EnumerableAssert;
import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Strings;
import org.fest.util.VisibleForTesting;

/**
 * Assertions for {@code String}s.
 * <p>
 * To create a new instance of this class, invoke {@link Assertions#assertThat(String)}.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class StringAssert extends AbstractAssert<StringAssert, String> implements EnumerableAssert<StringAssert> {

  @VisibleForTesting
  Strings strings = Strings.instance();

  protected StringAssert(String actual) {
    super(actual, StringAssert.class);
  }

  protected StringAssert(String actual, Description description) {
    super(actual, StringAssert.class, description);
  }

  /** {@inheritDoc} */
  @Override
  public StringAssert isNullOrEmpty() {
    strings.assertNullOrEmpty(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public StringAssert isEmpty() {
    strings.assertEmpty(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public @Nonnull
  StringAssert isNotEmpty() {
    strings.assertNotEmpty(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public @Nonnull
  StringAssert hasSize(int expected) {
    strings.assertHasSize(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@code String} is equal to the given one, ignoring case considerations.
   *
   * @param expected the given {@code String} to compare the actual {@code String} to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> {@code String} is not equal to the given one.
   */
  public @Nonnull
  StringAssert isEqualToIgnoringCase(@Nullable String expected) {
    strings.assertEqualsIgnoringCase(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@code String} contains the given sequence.
   *
   * @param sequence the sequence to look for.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given sequence is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} does not contain the given one.
   */
  public @Nonnull
  StringAssert contains(@Nonnull String sequence) {
    strings.assertContains(description, actual, sequence);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@code String} does not contain the given sequence.
   *
   * @param sequence the sequence to look for.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given sequence is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} contains the given one.
   */
  public @Nonnull
  StringAssert doesNotContain(@Nonnull String sequence) {
    strings.assertDoesNotContain(description, actual, sequence);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@code String} starts with the given prefix.
   *
   * @param prefix the given prefix.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given prefix is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} does not start with the given prefix.
   */
  public @Nonnull
  StringAssert startsWith(@Nonnull String prefix) {
    strings.assertStartsWith(description, actual, prefix);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@code String} ends with the given suffix.
   *
   * @param suffix the given suffix.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given suffix is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} does not end with the given suffix.
   */
  public @Nonnull
  StringAssert endsWith(@Nonnull String suffix) {
    strings.assertEndsWith(description, actual, suffix);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@code String} matches the given regular expression.
   *
   * @param regex the regular expression to which the <em>actual</em> {@code String} is to be matched.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given pattern is {@code null}.
   * @throws PatternSyntaxException if the regular expression's syntax is invalid.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} does not match the given regular expression.
   */
  public @Nonnull
  StringAssert matches(@Nonnull String regex) {
    strings.assertMatches(description, actual, regex);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@code String} does not match the given regular expression.
   *
   * @param regex the regular expression to which the <em>actual</em> {@code String} is to be matched.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given pattern is {@code null}.
   * @throws PatternSyntaxException if the regular expression's syntax is invalid.
   * @throws AssertionError if the <em>actual</em> {@code String} matches the given regular expression.
   */
  public @Nonnull
  StringAssert doesNotMatch(@Nonnull String regex) {
    strings.assertDoesNotMatch(description, actual, regex);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@code String} matches the given regular expression.
   *
   * @param pattern the compiled representation of the regular expression to which the <em>actual</em> {@code String} is
   *          to be matched.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given pattern is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} does not match the given regular expression.
   */
  public @Nonnull
  StringAssert matches(@Nonnull Pattern pattern) {
    strings.assertMatches(description, actual, pattern);
    return this;
  }

  /**
   * Verifies that the <em>actual</em> {@code String} does not match the given regular expression.
   *
   * @param pattern the compiled representation of the regular expression to which the <em>actual</em> {@code String} is
   *          to be matched.
   * @return {@code this} assertion object.
   * @throws NullPointerException if the given pattern is {@code null}.
   * @throws AssertionError if the <em>actual</em> {@code String} does not match the given regular expression.
   */
  public @Nonnull
  StringAssert doesNotMatch(@Nonnull Pattern pattern) {
    strings.assertDoesNotMatch(description, actual, pattern);
    return this;
  }
}
