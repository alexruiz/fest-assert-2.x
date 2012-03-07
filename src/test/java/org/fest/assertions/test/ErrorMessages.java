/*
 * Created on Dec 22, 2010
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
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.test;

import static org.fest.util.ToString.toStringOf;

/**
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class ErrorMessages {

  public static String arrayIsNull() {
    return "The given array should not be null";
  }

  public static String arrayIsEmpty() {
    return "The given array should not be empty";
  }

  public static String collectionIsNull() {
    return "The given collection should not be null";
  }

  public static String collectionIsEmpty() {
    return "The given collection should not be empty";
  }

  public static String descriptionIsNull() {
    return "The description to set should not be null";
  }

  public static String entriesToLookForIsEmpty() {
    return "The array of entries to look for should not be empty";
  }

  public static String entriesToLookForIsNull() {
    return "The array of entries to look for should not be null";
  }

  public static String entryToLookForIsNull() {
    return "Entries to look for should not be null";
  }

  public static String isNotArray(Object o) {
    return String.format("The object <%s> should be an array", toStringOf(o));
  }
  
  public static String iterableToLookForIsNull() {
    return "The iterable to look for should not be null";
  }

  public static String offsetIsNull() {
    return "The given offset should not be null";
  }

  public static String offsetValueIsNotPositive() {
    return "The value of the offset should be greater than zero";
  }

  public static String offsetValueIsNull() {
    return "The value of the offset to create should not be null";
  }

  public static String regexPatternIsNull() {
    return "The regular expression pattern to match should not be null";
  }

  public static String sequenceToLookForIsNull() {
    return "The sequence to look for should not be null";
  }

  public static String valuesToLookForIsEmpty() {
    return "The array of values to look for should not be empty";
  }

  public static String valuesToLookForIsNull() {
    return "The array of values to look for should not be null";
  }

  public static String dateToCompareActualWithIsNull() {
    return "The date to compare actual with should not be null";
  }

  public static String startDateToCompareActualWithIsNull() {
    return "The start date of period to compare actual with should not be null";
  }

  public static String endDateToCompareActualWithIsNull() {
    return "The end date of period to compare actual with should not be null";
  }

  private ErrorMessages() {}
}
