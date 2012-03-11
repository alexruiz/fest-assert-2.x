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
package org.fest.assertions.condition;

import static org.fest.util.Collections.set;

import java.util.Set;

import org.fest.assertions.core.Condition;

/**
 * 
 * A {@code Condition} checking is a Jedi
 * 
 * @author Nicolas François
 */
public class JediCondition extends Condition<String> {

	private final Set<String> jedis = set("Luke", "Yoda", "Obiwan");
	
	JediCondition(String description){
		super(description);
	}
	
	public JediCondition(){
		super("Jedi");
	}
	
	@Override
	public boolean matches(String value) {
		return jedis.contains(value);
	};

}
