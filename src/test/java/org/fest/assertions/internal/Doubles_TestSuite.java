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
 * All tests for {@link Doubles}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@SuiteClasses({
  Doubles_assertEqual_Test.class,
  Doubles_assertEqual_With_Offset_Test.class,
  Doubles_assertNotEqual_Test.class,
  Doubles_assertNotEqual_With_Offset_Test.class,
  Doubles_assertIsNaN_Test.class,
  Doubles_assertIsNotNaN_Test.class,
  Doubles_assertGreaterThan_Test.class,
  Doubles_assertLessThan_Test.class,
  Doubles_assertNotGreaterThan_Test.class,
  Doubles_assertNotLessThan_Test.class
})
public class Doubles_TestSuite {

}
