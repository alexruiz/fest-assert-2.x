/*
 * Created on Mar 17, 2012
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
 * Copyright @2012 the original author or authors.
 */
package org.fest.assertions.internal;

import static org.fest.assertions.error.ElementsShouldNotBeAtMost.elementsShouldNotBeAtMost;
import static org.fest.assertions.test.TestData.someInfo;
import static org.fest.assertions.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.fest.util.Collections.list;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import org.fest.assertions.condition.JediCondition;
import org.fest.assertions.core.AssertionInfo;
import org.fest.assertions.core.Condition;
import org.fest.assertions.core.TestCondition;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for
 * <code>{@link Iterables#assertAreNotAtMost(AssertionInfo, Iterable, Condition, int)}</code>
 * .
 * 
 * @author Nicolas François
 * @author Mikhail Mazursky
 */
public class Iterables_assertAreNotAtMost_Test extends AbstractTest_for_Iterables {

	private Condition<String> jedi = new JediCondition();
	private Failures failures;
	private TestCondition<Object> testCondition;
	private Conditions conditions;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		failures = spy(new Failures());
		testCondition = new TestCondition<Object>();
		iterables = new Iterables();
		iterables.failures = failures;
		conditions = spy(new Conditions());
		iterables.conditions = conditions;
	}
	

	@Test
	public void should_pass_if_not_satisfies_at_most_times_condition() {
		actual = list("Yoda", "Solo", "Leia");
		iterables.assertAreNotAtMost(someInfo(), actual, 2, jedi);
		verify(conditions).assertIsNotNull(jedi);
	}
	
	@Test
	public void should_pass_if_never_satisfies_condition_() {
		actual = list("Yoda", "Luke", "Obiwan");
		iterables.assertAreNotAtMost(someInfo(), actual, 2, jedi);
		verify(conditions).assertIsNotNull(jedi);
	}	

	@Test
	public void should_throw_error_if_condition_is_null() {
		thrown.expectNullPointerException("The condition to evaluate should not be null");
		actual = list("Yoda", "Luke");
		iterables.assertAreNotAtMost(someInfo(), actual, 2, null);
		verify(conditions).assertIsNotNull(null);
	}
	
	@Test
	public void should_fail_if_condition_is_not_met_much() {
	    testCondition.shouldMatch(false);
	    AssertionInfo info = someInfo();
	    try {
	    	actual = list("Solo", "Leia", "Chewbacca");
	    	iterables.assertAreNotAtMost(someInfo(), actual, 2, jedi);
	    } catch (AssertionError e) {
	      verify(conditions).assertIsNotNull(jedi);	
	      verify(failures).failure(info, elementsShouldNotBeAtMost(actual, 2, jedi));
	      return;
	    }
	    failBecauseExpectedAssertionErrorWasNotThrown();
	}

}
