/*
 * Created on Sep 24, 2012
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
 * Copyright @2012-2013 the original author or authors.
 */
package org.fest.assertions.api;

import org.fest.assertions.description.Description;

/**
 * Concrete test class for {@link AbstractObjectAssert}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ConcreteObjectAssert extends AbstractObjectAssert<ConcreteObjectAssert, Object> {
  public ConcreteObjectAssert(Object actual) {
    super(actual, ConcreteObjectAssert.class);
  }

  public ConcreteObjectAssert(Object actual, Description description) {
    super(actual, ConcreteObjectAssert.class, description);
  }
}
