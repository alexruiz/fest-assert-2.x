/*
 * Created on Oct 23, 2010
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

import org.fest.assertions.internal.Characters;
import org.fest.assertions.description.Description;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for characters.
 * <p>
 * To create an instance of this class, invoke {@link Assertions#assertThat(Character)} or
 * {@link Assertions#assertThat(char)}.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 * @author Joel Costigliola
 */
public class CharacterAssert extends AbstractComparableAssert<CharacterAssert, Character> {

  @VisibleForTesting
  Characters characters = Characters.instance();

  protected CharacterAssert(Character actual) {
    super(actual, CharacterAssert.class);
  }

  protected CharacterAssert(Character actual, Description description) {
    super(actual, CharacterAssert.class, description);
  }

  /**
   * Verifies that the actual value is a lowercase character.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is not a lowercase character.
   */
  public CharacterAssert isLowerCase() {
    characters.assertLowerCase(description, actual);
    return this;
  }

  /**
   * Verifies that the actual value is a uppercase character.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is not a uppercase character.
   */
  public CharacterAssert isUpperCase() {
    characters.assertUpperCase(description, actual);
    return this;
  }

  /**
   * Verifies that the actual value is equal to the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is not equal to the given one.
   */
  public CharacterAssert isEqualTo(char expected) {
    characters.assertEqual(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the actual value is not equal to the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is equal to the given one.
   */
  public CharacterAssert isNotEqualTo(char expected) {
    characters.assertNotEqual(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the actual value is less than the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is equal to or greater than the given one.
   */
  public CharacterAssert isLessThan(char expected) {
    characters.assertLessThan(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the actual value is greater than or equal to the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is less than the given one.
   */
  public CharacterAssert isNotLessThan(char expected) {
    characters.assertNotLessThan(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the actual value is greater than the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is equal to or less than the given one.
   */
  public CharacterAssert isGreaterThan(char expected) {
    characters.assertGreaterThan(description, actual, expected);
    return this;
  }

  /**
   * Verifies that the actual value is less than or equal to the given one.
   *
   * @param expected the given value to compare the actual value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the actual value is {@code null}.
   * @throws AssertionError if the actual value is greater than the given one.
   */
  public CharacterAssert isNotGreaterThan(char expected) {
    characters.assertGreaterThan(description, actual, expected);
    return this;
  }
}
