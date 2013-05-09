/*
 * Created on Apr 24, 2013
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
 * All tests for {@link Collections}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@SuiteClasses({
  Collections_assertNullOrEmpty_Test.class,
  Collections_assertEmpty_Test.class,
  Collections_assertContainsNull_Test.class,
  Collections_assertContains_Test.class,
  Collections_assertContainsOnly_Test.class,
  Collections_assertContainsSequence_Test.class,
  Collections_assertStartsWith_Test.class,
  Collections_assertEndsWith_Test.class,
  Collections_assertHasSize_Test.class,
  Collections_assertNotEmpty_Test.class,
  Collections_assertDoesNotContainNull_Test.class,
  Collections_assertDoesNotContain_Test.class,
  Collections_assertDoesNotHaveDuplicates_Test.class
})
public class Collections_TestSuite {

}
