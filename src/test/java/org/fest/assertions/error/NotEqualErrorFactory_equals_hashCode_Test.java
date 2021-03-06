/*
 * Created on Jan 15, 2011
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
 * Copyright @2011-2013 the original author or authors.
 */
package org.fest.assertions.error;

import static junit.framework.Assert.assertFalse;

import static org.fest.assertions.error.NotEqualErrorFactory.shouldBeEqual;
import static org.fest.test.EqualsHashCodeContractAssert.assertEqualsIsReflexive;
import static org.fest.test.EqualsHashCodeContractAssert.assertEqualsIsSymmetric;
import static org.fest.test.EqualsHashCodeContractAssert.assertEqualsIsTransitive;
import static org.fest.test.EqualsHashCodeContractAssert.assertMaintainsEqualsAndHashCodeContract;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@link NotEqualErrorFactory#equals(Object)} and {@link NotEqualErrorFactory#hashCode()}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class NotEqualErrorFactory_equals_hashCode_Test {
  private static NotEqualErrorFactory factory;

  @BeforeClass
  public static void setUpOnce() {
    factory = shouldBeEqual("Yoda", "Luke");
  }

  @Test
  public void should_have_reflexive_equals() {
    assertEqualsIsReflexive(factory);
  }

  @Test
  public void should_have_symmetric_equals() {
    assertEqualsIsSymmetric(factory, shouldBeEqual("Yoda", "Luke"));
  }

  @Test
  public void should_have_transitive_equals() {
    assertEqualsIsTransitive(factory, shouldBeEqual("Yoda", "Luke"), shouldBeEqual("Yoda", "Luke"));
  }

  @Test
  public void should_maintain_equals_and_hashCode_contract() {
    assertMaintainsEqualsAndHashCodeContract(factory, shouldBeEqual("Yoda", "Luke"));
  }

  @Test
  public void should_not_be_equal_to_Object_of_different_type() {
    assertFalse(factory.equals("Yoda"));
  }

  @Test
  public void should_not_be_equal_to_null() {
    assertFalse(factory.equals(null));
  }

  @Test
  public void should_not_be_equal_to_ShouldBeEqual_with_different_actual() {
    assertFalse(factory.equals(shouldBeEqual("Leia", "Luke")));
  }

  @Test
  public void should_not_be_equal_to_ShouldBeEqual_with_different_expected() {
    assertFalse(factory.equals(shouldBeEqual("Yoda", "Leia")));
  }
}
