/*
 * Created on Apr 25, 2013
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
 * All tests for {@link LongArrays}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@SuiteClasses({
  LongArrays_assertNullOrEmpty_Test.class,
  LongArrays_assertEmpty_Test.class,
  LongArrays_assertHasSize_Test.class,
  LongArrays_assertContains_Test.class,
  LongArrays_assertContains_At_Index_Test.class,
  LongArrays_assertContainsOnly_Test.class,
  LongArrays_assertContainsSequence_Test.class,
  LongArrays_assertStartsWith_Test.class,
  LongArrays_assertEndsWith_Test.class,
  LongArrays_assertDoesNotContain_Test.class,
  LongArrays_assertDoesNotContain_At_Index_Test.class,
  LongArrays_assertNotEmpty_Test.class,
  LongArrays_assertDoesNotHaveDuplicates_Test.class,
  LongArrays_assertIsSorted_Test.class
})
public class LongArrays_TestSuite {

}
