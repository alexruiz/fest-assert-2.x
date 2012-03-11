/*
 * Created on Mar 5, 2012
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
package org.fest.assertions.error;

import org.fest.assertions.core.Condition;

/**
 * Creates an error message indicating that an assertion that verifies  that each element of a group satisfies a {@code Condition}
 * A group of elements can be a collection, an array.<br>
 * 
 * @author Nicolas François
 */
public class ElementsShouldNotHave extends BasicErrorMessageFactory {

	  /**
	   * Creates a new </code>{@link ElementsShouldNotHave}</code>.
	   * @param actual the actual value in the failed assertion.
	   * @param notSatisfies elements that not satisfies the condition
	   * @param condition the {@code Condition}.
	   * @return the created {@code ErrorMessageFactory}.
	   */
	  public static <E> ErrorMessageFactory elementsShouldNotHave(Object actual, Object notSatisfies, Condition<E> condition) {
	    return new ElementsShouldNotHave(actual, notSatisfies, condition);
	  }
  
	  public ElementsShouldNotHave(Object actual, Object notSatisfies,	Condition<?> condition) {
		  super("expecting: elements <%s> of <%s> not to have <%s>", notSatisfies, actual, condition);
	  }

}
