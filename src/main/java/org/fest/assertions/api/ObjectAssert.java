/*
 * Created on Dec 26, 2010
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
import org.fest.assertions.internal.Objects;
import org.fest.util.VisibleForTesting;

/**
 * Assertion methods for {@code Object}s.
 * <p>
 * To create a new instance of this class, invoke {@link Assertions#assertThat(Object)}.
 * </p>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ObjectAssert extends AbstractObjectAssert<ObjectAssert, Object> {

  @VisibleForTesting
  Objects objects = Objects.instance();

  protected ObjectAssert(Object actual) {
    super(actual, ObjectAssert.class);
  }

  protected ObjectAssert(Object actual, Description description) {
    super(actual, ObjectAssert.class, description);
  }
}
