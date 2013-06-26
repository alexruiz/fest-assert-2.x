/*
 * Created on Jun 24, 2013
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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All tests in the assertion api package.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  AbstractAssert_TestSuite.class,
  AbstractComparableAssert_TestSuite.class,
  AbstractUnevenComparableAssert_TestSuite.class,
  AbstractObjectAssert_TestSuite.class,
  ArrayAsserts_TestSuite.class,
  Assertions_TestSuite.class,
  CollectionAsserts_TestSuite.class,
  FileAssert_TestSuite.class,
  NoneEnumerableObjects_TestSuite.class
})
public class AssertionsAPI_TestSuite {
}
