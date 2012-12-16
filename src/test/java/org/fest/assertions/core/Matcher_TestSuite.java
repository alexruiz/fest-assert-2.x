/*
 * Created on Oct 2, 2012
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
package org.fest.assertions.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * All tests for {@link Matcher}.
 *
 * @author alruiz@google.com (Alex Ruiz)
 */
@RunWith(Suite.class)
@SuiteClasses({
    Matcher_constructor_with_description_Test.class,
    Matcher_constructor_with_text_description_Test.class,
    Matcher_default_constructor_Test.class,
    Matcher_description_Test.class
})
public class Matcher_TestSuite {}
