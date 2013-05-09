/*
 * Created on Jul 26, 2010
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

import java.util.Collection;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Collections;
import org.fest.util.VisibleForTesting;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class CollectionAssert extends AbstractCollectionAssert<CollectionAssert, Collection<?>> {

  @VisibleForTesting
  Collections collections = Collections.instance();

  protected CollectionAssert(Collection<?> actual) {
    super(actual, CollectionAssert.class);
  }

  protected CollectionAssert(Collection<?> actual, Description description) {
    super(actual, CollectionAssert.class, description);
  }
}
