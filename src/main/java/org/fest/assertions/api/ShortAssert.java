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
import org.fest.assertions.internal.Shorts;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for shorts.
 * <p>
 * To create an instance of this class, invoke <code>{@link Assertions#assertThat(Short)}</code> or
 * <code>{@link Assertions#assertThat(short)}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class ShortAssert extends AbstractComparableAssert<ShortAssert, Short> {

  @VisibleForTesting
  Shorts shorts = Shorts.instance();

  protected ShortAssert(Short actual) {
    super(actual, ShortAssert.class);
  }

  protected ShortAssert(Short actual, Description description) {
    super(actual, ShortAssert.class, description);
  }

  public ShortAssert isEqualTo(short expected) {
    shorts.assertEqualTo(description, actual, expected);
    return this;
  }

  public ShortAssert isNotEqualTo(short expected) {
    shorts.assertNotEqualTo(description, actual, expected);
    return this;
  }

  public ShortAssert isLessThan(short expected) {
    shorts.assertLessThan(description, actual, expected);
    return this;
  }

  public ShortAssert isNotLessThan(short expected) {
    shorts.assertNotLessThan(description, actual, expected);
    return this;
  }

  public ShortAssert isGreaterThan(short expected) {
    shorts.assertGreaterThan(description, actual, expected);
    return this;
  }

  public ShortAssert isNotGreaterThan(short expected) {
    shorts.assertGreaterThan(description, actual, expected);
    return this;
  }
}
