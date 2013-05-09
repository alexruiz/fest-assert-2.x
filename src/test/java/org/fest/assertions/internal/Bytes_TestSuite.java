/*
 * Created on Apr 26, 2013
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
 * All tests for {@link Bytes}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@SuiteClasses({
  Bytes_assertEqualTo_Test.class,
  Bytes_assertNotEqualTo_Test.class,
  Bytes_assertGreaterThan_Test.class,
  Bytes_assertNotGreaterThan_Test.class,
  Bytes_assertLessThan_Test.class,
  Bytes_assertNotLessThan_Test.class
})
public class Bytes_TestSuite {

}
