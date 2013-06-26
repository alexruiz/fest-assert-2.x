/*
 * Created on Jun 11, 2013
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
 * All tests for {@link ObjectAssert}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  ObjectAssert_isNull_Test.class,
  ObjectAsset_isNotNull_Test.class,
  ObjectAssert_isEqualTo_Test.class,
  ObjectAssert_isNotEqualTo_Test.class,
  ObjectAssert_isSameAs_Test.class,
  ObjectAssert_isNotSameAs_Test.class,
  ObjectAssert_isInstanceOf_Test.class,
  ObjectAssert_isNotInstanceOf_Test.class,
  ObjectAssert_isInstanceOfAny_Test.class,
  ObjectAssert_isNotInstanceOfAny_Test.class
})
public class ObjectAssert_TestSuite {

}
