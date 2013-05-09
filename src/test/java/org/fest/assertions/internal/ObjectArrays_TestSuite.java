/*
 * Created on May 2, 2013
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
package org.fest.assertions.internal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All tests for {@link ObjectArrays}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@SuiteClasses({
  ObjectArrays_assertEmpty_Test.class,
  ObjectArrays_assertNullOrEmpty_Test.class,
  ObjectArrays_assertNotEmpty_Test.class,
  ObjectArrays_assertContains_Test.class,
  ObjectArrays_assertContains_at_Index_Test.class,
  ObjectArrays_assertContainsOnly_Test.class,
  ObjectArrays_assertContainsNull_Test.class,
  ObjectArrays_assertContainsSequence_Test.class,
  ObjectArrays_assertDoesNotContain_Test.class,
  ObjectArrays_assertDoesNotContain_at_Index_Test.class,
  ObjectArrays_assertDoesNotHaveDuplicates_Test.class,
  ObjectArrays_assertHasSize_Test.class,
  ObjectArrays_assertStartsWith_Test.class,
  ObjectArrays_assertEndsWith_Test.class
})
public class ObjectArrays_TestSuite {
}
