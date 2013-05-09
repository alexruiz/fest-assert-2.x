/*
 * Created on Jul 15, 2010
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
package org.fest.assertions.core;

import static org.fest.util.Preconditions.checkNotNull;

import org.fest.assertions.description.Description;
import org.fest.assertions.description.TextDescription;
import org.fest.util.VisibleForTesting;

/**
 * Indicates whether a value matches some criteria.
 *
 * @param <T> the type of object this matcher accepts.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class Matcher<T> {
  @VisibleForTesting
  Description description;

  /**
   * Creates a new {@link Matcher}. The default description of this matcher will the simple name of the matcher's
   * class.
   */
  public Matcher() {
    this.description = new TextDescription(getClass().getSimpleName());
  }

  /**
   * Creates a new {@link Matcher}.
   *
   * @param description the description of this matcher.
   * @throws NullPointerException if the given description is {@code null}.
   */
  public Matcher(String description) {
    this(new TextDescription(description));
  }

  /**
   * Creates a new {@link Matcher}.
   *
   * @param description the description of this matcher.
   * @throws NullPointerException if the given description is {@code null}.
   */
  public Matcher(Description description) {
    checkNotNull(description);
    this.description = description;
  }

  /**
   * Returns the description of this matcher.
   *
   * @return the description of this matcher.
   */
  public Description description() {
    return description;
  }

  /**
   * Verifies that the given value satisfies this matcher.
   *
   * @param value the value to verify.
   * @return {@code true} if the given value satisfies this matcher; {@code false} otherwise.
   */
  public abstract boolean matches(T value);

  @Override
  public String toString() {
    return description.value();
  }
}
