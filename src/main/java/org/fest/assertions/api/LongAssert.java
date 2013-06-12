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
package org.fest.assertions.api;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Longs;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for longs.
 * <p>
 * To create an instance of this class, invoke <code>{@link Assertions#assertThat(Long)}</code> or
 * <code>{@link Assertions#assertThat(long)}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class LongAssert extends AbstractComparableAssert<LongAssert, Long> {

  @VisibleForTesting
  Longs longs = Longs.instance();

  protected LongAssert(Long actual) {
    super(actual, LongAssert.class);
  }

  protected LongAssert(Long actual, Description description) {
    super(actual, LongAssert.class, description);
  }

  public LongAssert isEqualTo(long expected) {
    longs.assertEqualTo(description, actual, expected);
    return this;
  }

  public LongAssert isNotEqualTo(long expected) {
    longs.assertNotEqualTo(description, actual, expected);
    return this;
  }

  public LongAssert isLessThan(long expected) {
    longs.assertLessThan(description, actual, expected);
    return this;
  }

  public LongAssert isNotLessThan(long expected) {
    longs.assertNotLessThan(description, actual, expected);
    return this;
  }

  public LongAssert isGreaterThan(long expected) {
    longs.assertGreaterThan(description, actual, expected);
    return this;
  }

  public LongAssert isNotGreaterThan(long expected) {
    longs.assertNotGreaterThan(description, actual, expected);
    return this;
  }
}
