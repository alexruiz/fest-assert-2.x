/*
 * Created on Nov 18, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.api;

import org.fest.assertions.core.ComparableAssert;
import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Comparables;
import org.fest.util.VisibleForTesting;

/**
 * Base class for all implementations of {@link ComparableAssert}.
 *
 * @param <S> the "self" type of this assertion class. Please read &quot;<a href="http://bit.ly/anMa4g"
 *          target="_blank">Emulating 'self types' using Java Generics to simplify fluent API implementation</a>&quot;
 *          for more details.
 * @param <A> the type of the <em>actual</em> value.
 *
 * @author Alex Ruiz
 */
public abstract class AbstractComparableAssert<S, A extends Comparable<A>> extends AbstractAssert<S, A>
    implements ComparableAssert<S, A> {
  @VisibleForTesting
  Comparables comparables = Comparables.instance();

  protected AbstractComparableAssert(A actual, Class<S> selfType) {
    super(actual, selfType);
  }

  protected AbstractComparableAssert(A actual, Class<S> selfType, Description description) {
    super(actual, selfType, description);
  }

  /** {@inheritDoc} */
  @Override
  public S isLessThan(A other) {
    comparables.assertLessThan(description, actual, other);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public S isNotLessThan(A other) {
    comparables.assertNotLessThan(description, actual, other);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public S isGreaterThan(A other) {
    comparables.assertGreaterThan(description, actual, other);
    return myself;
  }

  /** {@inheritDoc} */
  @Override
  public S isNotGreaterThan(A other) {
    comparables.assertNotGreaterThan(description, actual, other);
    return myself;
  }
}
