/*
 * Created on Oct 24, 2010
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

import org.fest.assertions.core.FloatingPointNumberAssert;
import org.fest.assertions.data.Offset;
import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Floats;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for floats.
 * <p>
 * To create an instance of this class, invoke <code>{@link Assertions#assertThat(Float)}</code> or
 * <code>{@link Assertions#assertThat(float)}</code>.
 * </p>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Ansgar Konermann
 */
public class FloatAssert extends AbstractComparableAssert<FloatAssert, Float> implements
    FloatingPointNumberAssert<FloatAssert, Float> {

  @VisibleForTesting
  Floats floats = Floats.instance();

  protected FloatAssert(Float actual) {
    super(actual, FloatAssert.class);
  }

  protected FloatAssert(Float actual, Description description) {
    super(actual, FloatAssert.class, description);
  }

  /** {@inheritDoc} */
  @Override
  public FloatAssert isNaN() {
    floats.assertIsNaN(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public FloatAssert isNotNaN() {
    floats.assertIsNotNaN(description, actual);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public FloatAssert isEqualTo(Float expected, Offset<Float> offset) {
    floats.assertEqual(description, actual, expected, offset);
    return this;
  }
}
