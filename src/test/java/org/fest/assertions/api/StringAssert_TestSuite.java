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
 * Copyright @2012-2013 Google Inc. and others.
 */
package org.fest.assertions.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * All tests for {@link StringAssert}.
 *
 * @author alruiz@google.com (Alex Ruiz)
 * @author Yvonne Wang
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    StringAssert_contains_String_Test.class,
    StringAssert_doesNotContain_Test.class,
    StringAssert_doesNotMatch_Pattern_Test.class,
    StringAssert_doesNotMatch_String_Test.class,
    StringAssert_hasSize_Test.class,
    StringAssert_isEmpty_Test.class,
    StringAssert_isEqualToIgnoringCase_Test.class,
    StringAssert_isNotEmpty_Test.class,
    StringAssert_isNullOrEmpty_Test.class,
    StringAssert_matches_Pattern_Test.class,
    StringAssert_matches_String_Test.class,
    StringAssert_startsWith_Test.class,
    StringAssert_endsWith_Test.class
})
public class StringAssert_TestSuite {}
