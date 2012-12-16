/*
 * Created on Oct 21, 2010
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
package org.fest.assertions.api;

import org.fest.assertions.description.Description;

/**
 * Assertions for {@code boolean}s.
 * <p>
 * To create an instance of this class, invoke {@link Assertions#assertThat(Boolean)} or
 * {@link Assertions#assertThat(boolean)}.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 */
public class BooleanAssert extends AbstractAssert<BooleanAssert, Boolean> {
  protected BooleanAssert(Boolean actual) {
    super(actual, BooleanAssert.class);
  }

  protected BooleanAssert(Boolean actual, Description description) {
    super(actual, BooleanAssert.class, description);
  }

  /**
   * Verifies that the <em>actual</em> value is {@code true}.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is not {@code true}.
   */
  public BooleanAssert isTrue() {
    return isEqualTo(true);
  }

  /**
   * Verifies that the <em>actual</em> value is {@code false}.
   *
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is not {@code false}.
   */
  public BooleanAssert isFalse() {
    return isEqualTo(false);
  }

  /**
   * Verifies that the <em>actual</em> value is equal to the given one.
   *
   * @param expected the given value to compare the <em>actual</em> value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is not equal to the given one.
   */
  public BooleanAssert isEqualTo(boolean expected) {
    return super.isEqualTo(Boolean.valueOf(expected));
  }

  /**
   * Verifies that the <em>actual</em> value is not equal to the given one.
   *
   * @param other the given value to compare the <em>actual</em> value to.
   * @return {@code this} assertion object.
   * @throws AssertionError if the <em>actual</em> value is {@code null}.
   * @throws AssertionError if the <em>actual</em> value is equal to the given one.
   */
  public BooleanAssert isNotEqualTo(boolean other) {
    return super.isNotEqualTo(Boolean.valueOf(other));
  }
}
