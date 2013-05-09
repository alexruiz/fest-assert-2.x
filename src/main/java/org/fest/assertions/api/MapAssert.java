/*
 * Created on Dec 21, 2010
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

import java.util.Map;

import org.fest.assertions.description.Description;
import org.fest.assertions.internal.Maps;
import org.fest.util.VisibleForTesting;

/**
 * Assertions for {@link Map}s.
 * <p>
 * To create a new instance of this class, invoke {@link Assertions#assertThat(Map)}.
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class MapAssert extends AbstractMapAssert<MapAssert> {

  @VisibleForTesting
  Maps maps = Maps.instance();

  protected MapAssert(Map<?, ?> actual) {
    super(actual, MapAssert.class);
  }

  protected MapAssert(Map<?, ?> actual, Description description) {
    super(actual, MapAssert.class, description);
  }
}
