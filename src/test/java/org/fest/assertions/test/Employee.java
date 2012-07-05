/*
 * Created on Feb 22, 2011
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
 * Copyright @2011 the original author or authors.
 */
package org.fest.assertions.test;

import static java.lang.String.format;

/**
 * @author Yvonne Wang
 */
public class Employee {

  private long id;
  private Name name;
  private int age;

  public Employee() {}

  public Employee(long id, Name name, int age) {
    setId(id);
    setName(name);
    setAge(age);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Name getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override public String toString() {
    return format("%s[id=%d, name=%s, age=%d]", getClass().getSimpleName(), id, name, age);
  }
}
