/*
 * Created on Mar 29, 2009
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
import org.junit.runners.Suite.SuiteClasses;

/**
 * All tests for {@link ByteAssert}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@SuiteClasses({
  ByteAssert_isNull_Test.class,
  ByteAssert_isNotNull_Test.class,
  ByteAssert_isEqualTo_byte_Test.class,
  ByteAssert_isNotEqualTo_byte_Test.class,
  ByteAssert_isGreaterThan_byte_Test.class,
  ByteAssert_isNotGreaterThan_byte_Test.class,
  ByteAssert_isLessThan_byte_Test.class,
  ByteAssert_isNotLessThan_byte_Test.class,
  ByteAssert_isEqualTo_byte_wrapper_Test.class,
  ByteAssert_isNotEqualTo_byte_wrapper_Test.class,
  ByteAssert_isGreaterThan_byte_wrapper_Test.class,
  ByteAssert_isNotGreaterThan_byte_wrapper_Test.class,
  ByteAssert_isLessThan_byte_wrapper_Test.class,
  ByteAssert_isNotLessThan_byte_wrapper_Test.class
})
public class ByteAssert_TestSuite {
}
