/*
 * Created on May 7, 2013
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
 * Copyright @2013 the original author or authors.
 */
package org.fest.assertions.api;

import java.util.Set;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Sets;
import org.fest.util.VisibleForTesting;

/**
 * Assertions for {@link Set}s.<p>
 * To create a new instance of this class, invoke {@link Assertions#assertThat(Set)}.
 *
 * @author Yvonne Wang
 */
public class SetAssert extends AbstractCollectionAssert<SetAssert, Set<?>> {

  @VisibleForTesting
  Sets sets = Sets.instance();

  protected SetAssert(Set<?> actual) {
    super(actual, SetAssert.class);
  }

  protected SetAssert(Set<?> actual, Description description) {
    super(actual, SetAssert.class, description);
  }
}
