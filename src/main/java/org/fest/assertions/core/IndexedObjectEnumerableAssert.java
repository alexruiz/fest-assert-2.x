/*
 * Created on Nov 24, 2010
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
package org.fest.assertions.core;

import org.fest.assertions.data.Index;

/**
 * Assertions methods applicable to indexed groups of objects (e.g. arrays or lists.)
 *
 * @author Alex Ruiz
 * @author Mikhail Mazursky
 */
public interface IndexedObjectEnumerableAssert<T> {

  /**
   * Verifies that the actual group contains the given object at the given index.
   * @param value the object to look for.
   * @param index the index where the object should be stored in the actual group.
   * @return this assertion object.
   * @throws AssertionError if the actual group is {@code null} or empty.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws IndexOutOfBoundsException if the value of the given {@code Index} is equal to or greater than the size of
   * the actual group.
   * @throws AssertionError if the actual group does not contain the given object at the given index.
   */
  IndexedObjectEnumerableAssert<T> contains(T value, Index index);

  /**
   * Verifies that the actual group does not contain the given object at the given index.
   * @param value the object to look for.
   * @param index the index where the object should be stored in the actual group.
   * @return this assertion object.
   * @throws AssertionError if the actual group is {@code null}.
   * @throws NullPointerException if the given {@code Index} is {@code null}.
   * @throws AssertionError if the actual group contains the given object at the given index.
   */
  IndexedObjectEnumerableAssert<T> doesNotContain(T value, Index index);
}