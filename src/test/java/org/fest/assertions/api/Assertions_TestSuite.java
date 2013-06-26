/*
 * Created on May 9, 2013
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
 * All tests for array asserts.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@SuiteClasses({
  Assertions_assertThat_with_BigDecimal_Test.class,
  Assertions_assertThat_with_Boolean_Test.class,
  Assertions_assertThat_with_BooleanArray_Test.class,
  Assertions_assertThat_with_Byte_Test.class,
  Assertions_assertThat_with_ByteArray_Test.class,
  Assertions_assertThat_with_Character_Test.class,
  Assertions_assertThat_with_CharArray_Test.class,
  Assertions_assertThat_with_Double_Test.class,
  Assertions_assertThat_with_DoubleArray_Test.class,
  Assertions_assertThat_with_File_Test.class,
  Assertions_assertThat_with_Float_Test.class,
  Assertions_assertThat_with_FloatArray_Test.class,
  Assertions_assertThat_with_Integer_Test.class,
  Assertions_assertThat_with_IntArray_Test.class,
  Assertions_assertThat_with_List_Test.class,
  Assertions_assertThat_with_Long_Test.class,
  Assertions_assertThat_with_LongArray_Test.class,
  Assertions_assertThat_with_Map_Test.class,
  Assertions_assertThat_with_Object_Test.class,
  Assertions_assertThat_with_ObjectArray_Test.class,
  Assertions_assertThat_with_Sets_Test.class,
  Assertions_assertThat_with_Short_Test.class,
  Assertions_assertThat_with_ShortArray_Test.class,
  Assertions_assertThat_with_String_Test.class
})
public class Assertions_TestSuite {
}
