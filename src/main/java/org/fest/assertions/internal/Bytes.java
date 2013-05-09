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
 * Reusable assertions for {@link Byte}s.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Bytes {

  private static final Bytes INSTANCE = new Bytes();

  /**
   * Returns the singleton instance of this class.
   *
   * @return the singleton instance of this class.
   */
  public static Bytes instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  Failures failures = Failures.instance();

  @VisibleForTesting
  Bytes() {
  }

  public void assertEqualTo(Description description, Byte actual, byte expected) {
    assertNotNull(description, actual);
    if (!isEqualTo(actual, expected)) {
      throw failures.failure(description, shouldBeEqual(actual, expected));
    }
  }

  public void assertNotEqualTo(Description description, Byte actual, byte expected) {
    assertNotNull(description, actual);
    if (isEqualTo(actual, expected)) {
      throw failures.failure(description, shouldNotBeEqual(actual, expected));
    }
  }

  public void assertGreaterThan(Description description, Byte actual, byte expected) {
    assertNotNull(description, actual);
    if (!isGreaterThan(actual, expected)) {
      throw failures.failure(description, shouldBeGreaterThan(actual, expected));
    }
  }

  public void assertNotGreaterThan(Description description, Byte actual, byte expected) {
    assertNotNull(description, actual);
    if (isGreaterThan(actual, expected)) {
      throw failures.failure(description, shouldNotBeGreaterThan(actual, expected));
    }
  }

  public void assertLessThan(Description description, Byte actual, byte expected) {
    assertNotNull(description, actual);
    if (!isLessThan(actual, expected)) {
      throw failures.failure(description, shouldBeLessThan(actual, expected));
    }
  }

  public void assertNotLessThan(Description description, Byte actual, byte expected) {
    assertNotNull(description, actual);
    if (isLessThan(actual, expected)) {
      throw failures.failure(description, shouldNotBeLessThan(actual, expected));
    }
  }

  private boolean isGreaterThan(Byte actual, byte expected) {
    return actual.byteValue() > expected;
  }

  private boolean isLessThan(Byte actual, byte expected) {
    return actual.byteValue() < expected;
  }

  private boolean isEqualTo(Byte actual, byte expected) {
    return actual.byteValue() == expected;
  }
}
