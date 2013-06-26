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
 * All tests for {@link CharacterAssert}.
 *
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@SuiteClasses({
  CharacterAssert_isNull_Test.class,
  CharacterAssert_isNotNull_Test.class,
  CharacterAssert_isEqualTo_char_Test.class,
  CharacterAssert_isNotEqualTo_char_Test.class,
  CharacterAssert_isGreaterThan_char_Test.class,
  CharacterAssert_isNotGreaterThan_char_Test.class,
  CharacterAssert_isLessThan_char_Test.class,
  CharacterAssert_isNotLessThan_char_Test.class,
  CharacterAssert_isLowerCase_Test.class,
  CharacterAssert_isUpperCase_Test.class,
  CharacterAssert_isEqualTo_Character_Test.class,
  CharacterAssert_isNotEqualTo_Character_Test.class,
  CharacterAssert_isGreaterThan_Character_Test.class,
  CharacterAssert_isNotGreaterThan_Character_Test.class,
  CharacterAssert_isLessThan_Character_Test.class,
  CharacterAssert_isNotLessThan_Character_Test.class
})
public class CharacterAssert_TestSuite {
}
