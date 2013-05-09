/*
 * Created on Oct 26, 2010
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

import java.util.List;

import org.fest.assertions.core.IndexedObjectEnumerableAssert;
import org.fest.assertions.data.Index;
import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Lists;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for {@link List}s.
 * <p>
 * To create an instance of this class, invoke {@link Assertions#assertThat(List)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ListAssert extends AbstractCollectionAssert<ListAssert, List<?>> implements
    IndexedObjectEnumerableAssert<ListAssert, Object> {

  @VisibleForTesting
  Lists lists = Lists.instance();

  protected ListAssert(List<?> actual) {
    super(actual, ListAssert.class);
  }

  protected ListAssert(List<?> actual, Description description) {
    super(actual, ListAssert.class, description);
  }

  /** {@inheritDoc} */
  @Override
  public ListAssert contains(Object value, Index index) {
    lists.assertContains(description, actual, value, index);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public ListAssert doesNotContain(Object value, Index index) {
    lists.assertDoesNotContain(description, actual, value, index);
    return this;
  }
}
