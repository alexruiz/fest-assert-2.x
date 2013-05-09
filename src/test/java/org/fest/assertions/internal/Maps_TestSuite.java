/*
 * Created on Apr 30, 2013
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
 * All tests for {@link Maps}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@SuiteClasses({
  Maps_assertNullOrEmpty_Test.class,
  Maps_assertEmpty_Test.class,
  Maps_assertContains_Test.class,
  Maps_assertContainsKey_Test.class,
  Maps_assertContainsValue_Test.class,
  Maps_assertContainsOnly_Test.class,
  //Maps_assertContainsAll_Test.class,
  Maps_assertHasSize_Test.class,
  Maps_assertNotEmpty_Test.class,
  Maps_assertDoesNotContain_Test.class,
  Maps_assertDoesNotContainKey_Test.class,
  Maps_assertDoesNotContainValue_Test.class,
  Maps_assertDoesNotHaveDuplicateValues_Test.class
})
public class Maps_TestSuite {

}
