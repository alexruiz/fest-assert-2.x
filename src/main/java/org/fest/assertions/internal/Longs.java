/*
 * Created on Oct 20, 2010
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

import static org.fest.assertions.error.ShouldBeEqual.shouldBeEqual;
import static org.fest.assertions.error.ShouldBeGreaterThan.shouldBeGreaterThan;
import static org.fest.assertions.error.ShouldBeLessThan.shouldBeLessThan;
import static org.fest.assertions.error.ShouldNotBeEqual.shouldNotBeEqual;
import static org.fest.assertions.error.ShouldNotBeGreaterThan.shouldNotBeGreaterThan;
import static org.fest.assertions.error.ShouldNotBeLessThan.shouldNotBeLessThan;
import static org.fest.assertions.internal.Comparables.assertNotNull;

import org.fest.assertions.description.Description;
import org.fest.util.VisibleForTesting;

/**
 * Reusable assertions for <code>{@link Long}</code>s.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Longs {

  private static final Longs INSTANCE = new Longs();

  /**
   * Returns the singleton instance of this class.
   *
   * @return the singleton instance of this class.
   */
  public static Longs instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Comparables comparables = Comparables.instance();
  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  Longs() {
  }

  /**
   * Verifies that two longs are equal.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertEqualTo(Description description, Long actual, long expected) {
    assertNotNull(description, actual);
    if (!isEqualTo(actual, expected)) {
      throw failures.failure(description, shouldBeEqual(actual, expected));
    }
  }

  /**
   * Verifies that two longs are not equal.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertNotEqualTo(Description description, Long actual, long expected) {
    assertNotNull(description, actual);
    if (isEqualTo(actual, expected)) {
      throw failures.failure(description, shouldNotBeEqual(actual, expected));
    }
  }

  /**
   * Verifies that the actual value is greater than the expected one.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertGreaterThan(Description description, Long actual, long expected) {
    assertNotNull(description, actual);
    if (!isGreaterThan(actual, expected)) {
      throw failures.failure(description, shouldBeGreaterThan(actual, expected));
    }
  }

  /**
   * Verifies that the actual value is greater than or equal to the expected one.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertNotLessThan(Description description, Long actual, long expected) {
    assertNotNull(description, actual);
    if (isLessThan(actual, expected)) {
      throw failures.failure(description, shouldNotBeLessThan(actual, expected));
    }
  }

  /**
   * Verifies that the actual value is less than the expected one.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertLessThan(Description description, Long actual, long expected) {
    assertNotNull(description, actual);
    if (!isLessThan(actual, expected)) {
      throw failures.failure(description, shouldBeLessThan(actual, expected));
    }
  }

  /**
   * Verifies that the actual value is less than or equal to the expected one.
   *
   * @param description the description of the <em>actual</em> value.
   * @param actual the <em>actual</em> value.
   * @param expected the <em>expected</em> value.
   */
  public void assertNotGreaterThan(Description description, Long actual, long expected) {
    assertNotNull(description, actual);
    if (isGreaterThan(actual, expected)) {
      throw failures.failure(description, shouldNotBeGreaterThan(actual, expected));
    }
  }

  private boolean isEqualTo(Long actual, long expected) {
    return actual.intValue() == expected;
  }

  private boolean isGreaterThan(Long actual, long expected) {
    return actual.intValue() > expected;
  }

  private boolean isLessThan(Long actual, long expected) {
    return actual.intValue() < expected;
  }
}
