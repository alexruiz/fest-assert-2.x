/*
 * Created on Oct 1, 2012
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
 * Copyright @2012 Google Inc. and others.
 */
package org.fest.assertions.internal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All tests for {@link Strings}.
 *
 * @author alruiz@google.com (Alex Ruiz)
 */
@RunWith(Suite.class)
@SuiteClasses({
    Strings_assertContains_Test.class,
    Strings_assertDoesNotContain_Test.class,
    Strings_assertDoesNotMatch_Pattern_Test.class,
    Strings_assertDoesNotMatch_String_Test.class,
    Strings_assertEmpty_Test.class,
    Strings_assertEndsWith_Test.class,
    Strings_assertEqualsIgnoringCase_Test.class,
    Strings_assertEqualsIgnoringCase_with_mocks_Test.class,
    Strings_assertHasSize_Test.class,
    Strings_assertMatches_Pattern_Test.class,
    Strings_assertMatches_String_Test.class,
    Strings_assertNotEmpty_Test.class,
    Strings_assertNullOrEmpty_Test.class,
    Strings_assertStartsWith_Test.class
})
public class Strings_TestSuite {}
