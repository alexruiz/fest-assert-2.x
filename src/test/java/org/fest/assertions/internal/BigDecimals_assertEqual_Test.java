/*
 * Created on Mar 29, 2013
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

import static org.fest.assertions.error.ShouldNotBeNull.shouldNotBeNull;
import static org.fest.test.ExpectedException.none;

import java.math.BigDecimal;

import org.fest.assertions.description.Description;
import org.fest.assertions.error.MessageFormatter;
import org.fest.test.ExpectedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Alex
 * @author Alex
 */
public class BigDecimals_assertEqual_Test {

  @Rule
  public ExpectedException thrown = none();

  private BigDecimals bigDecimals;
  private Description description;

  @Before
  public void setUp() {
    bigDecimals = new BigDecimals();
    description = new TestDescription("testing");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    String message = shouldNotBeNull().create(description);
    thrown.expect(AssertionError.class, message);
    bigDecimals.assertEqual(description, null, BigDecimal.ONE);
  }

  @Test
  public void should_fail_if_expected_is_null() {
    thrown.expect(NullPointerException.class, "The given number should not be null");
    bigDecimals.assertEqual(description, BigDecimal.ONE, null);
  }

  @Test
  public void should_fail_if_actual_is_not_equal_to_expectd() {
    MessageFormatter messageFormatter = MessageFormatter.instance();
    String message = messageFormatter.format(description, "expected:<%s> but was:<%s>", BigDecimal.TEN, BigDecimal.ONE);
    try {
      bigDecimals.assertEqual(description, BigDecimal.ONE, BigDecimal.TEN);
    } catch (org.junit.ComparisonFailure e) {
      e.getMessage().equalsIgnoreCase(message);
    }
  }

  @Test
  public void should_pass_if_actual_is_equal_to_expected() {
    bigDecimals.assertEqual(description, BigDecimal.ONE, BigDecimal.ONE);
  }
}
