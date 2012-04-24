/*
 * Created on Jun 26, 2010
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
package org.fest.assertions.internal;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static org.fest.util.Collections.isEmpty;
import static org.fest.util.Collections.nonNullElements;
import static org.fest.util.Introspection.descriptorForProperty;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fest.util.IntrospectionError;
import org.fest.util.VisibleForTesting;

/**
 * Utility methods for properties access.
 * 
 * @author Joel Costigliola
 * @author Alex Ruiz
 * @author Nicolas François
 */
public class PropertySupport {

  private static final String SEPARATOR = ".";

  private static final PropertySupport INSTANCE = new PropertySupport();

  /**
   * Returns the singleton instance of this class.
   * @return the singleton instance of this class.
   */
  public static PropertySupport instance() {
    return INSTANCE;
  }

  @VisibleForTesting
  JavaBeanDescriptor javaBeanDescriptor = new JavaBeanDescriptor();

  @VisibleForTesting
  PropertySupport() {}

  /**
   * Returns a <code>{@link List}</code> containing the values of the given property name, from the elements of the
   * given <code>{@link Collection}</code>. If the given {@code Collection} is empty or {@code null}, this method will
   * return an empty {@code List}. This method supports nested properties (e.g. "address.street.number").
   * @param propertyName the name of the property. It may be a nested property. It is left to the clients to validate
   *          for {@code null} or empty.
   * @param target the given {@code Collection}.
   * @return a {@code List} containing the values of the given property name, from the elements of the given
   *         {@code Collection}.
   * @throws IntrospectionError if an element in the given {@code Collection} does not have a property with a matching
   *           name.
   */
  public List<Object> propertyValues(String propertyName, Collection<?> target) {
    // ignore null elements as we can't extract a property from a null object
    Collection<?> cleanedUp = nonNullElements(target);
    if (isEmpty(cleanedUp)) return emptyList();
    if (isNestedProperty(propertyName)) {
      String firstPropertyName = popPropertyNameFrom(propertyName);
      List<Object> propertyValues = propertyValues(firstPropertyName, cleanedUp);
      // extract next sub-property values until reaching the last sub-property
      return propertyValues(nextPropertyNameFrom(propertyName), propertyValues);
    }
    return simplePropertyValues(propertyName, cleanedUp);
  }

  /**
   * Static variant of {@link #propertyValue(String, Object)} for synthetic sugar.
   * <p>
   * Returns a <code>{@link List}</code> containing the values of the given property name, from the elements of the
   * given <code>{@link Collection}</code>. If the given {@code Collection} is empty or {@code null}, this method will
   * return an empty {@code List}. This method supports nested properties (e.g. "address.street.number").
   * @param propertyName the name of the property. It may be a nested property. It is left to the clients to validate
   *          for {@code null} or empty.
   * @param target the given {@code Collection}.
   * @return a {@code List} containing the values of the given property name, from the elements of the given
   *         {@code Collection}.
   * @throws IntrospectionError if an element in the given {@code Collection} does not have a property with a matching
   *           name.
   */
  public static List<Object> propertyValuesOf(String propertyName, Collection<?> target) {
    return instance().propertyValues(propertyName, target);
  }

  /**
   * Returns a <code>{@link List}</code> containing the values of the given property name, from the elements of the
   * given array. If the given array is empty or {@code null}, this method will return an empty {@code List}. This
   * method supports nested properties (e.g. "address.street.number").
   * @param propertyName the name of the property. It may be a nested property. It is left to the clients to validate
   *          for {@code null} or empty.
   * @param target the given array.
   * @return a {@code List} containing the values of the given property name, from the elements of the given array.
   * @throws IntrospectionError if an element in the given array does not have a property with a matching name.
   */
  public static List<Object> propertyValuesOf(String propertyName, Object[] target) {
    return instance().propertyValues(propertyName, asList(target));
  }
  
  /**
   * Static varient of {@link #propertyValue(String, Object, Class)}  for synthetic sugar.
   * <p>
   * @param propertyName the name of the property. It may be a nested property. It is left to the clients to validate
   *          for {@code null} or empty.
   * @param target the given object
   * @param clazz type of property
   * @return a the values of the given property name
   * @throws IntrospectionError if the given target does not have a property with a matching name.
   */
  public static <T>  T propertyValueOf(String propertyName, Object target, Class<T> clazz){
	  return instance().propertyValue(propertyName, target, clazz);
  }
  
  /**
   * Return the value of property from a target object.
   * @param propertyName the name of the property. It may be a nested property. It is left to the clients to validate
   *          for {@code null} or empty.
   * @param target the given object
   * @param clazz type of property
   * @return a the values of the given property name
   * @throws IntrospectionError if the given target does not have a property with a matching name.
   */
  @SuppressWarnings("unchecked")
  public <T>  T propertyValue(String propertyName, Object target, Class<T> clazz){
	  return  (T) propertyValue(propertyName, target);
  }  

  private List<Object> simplePropertyValues(String propertyName, Collection<?> target) {
    List<Object> propertyValues = new ArrayList<Object>();
    for (Object e : target)
      propertyValues.add(propertyValue(propertyName, e));
    return unmodifiableList(propertyValues);
  }

  private String popPropertyNameFrom(String propertyNameChain) {
    if (!isNestedProperty(propertyNameChain)) return propertyNameChain;
    return propertyNameChain.substring(0, propertyNameChain.indexOf(SEPARATOR));
  }

  private String nextPropertyNameFrom(String propertyNameChain) {
    if (!isNestedProperty(propertyNameChain)) return "";
    return propertyNameChain.substring(propertyNameChain.indexOf(SEPARATOR) + 1);
  }

  /*
   * isNestedProperty("address.street"); // true isNestedProperty("address.street.name"); // true
   * isNestedProperty("person"); // false isNestedProperty(".name"); // false isNestedProperty("person."); // false
   * isNestedProperty("person.name."); // false isNestedProperty(".person.name"); // false isNestedProperty("."); //
   * false isNestedProperty(""); // false
   */
  private boolean isNestedProperty(String propertyName) {
    return propertyName.contains(SEPARATOR) && !propertyName.startsWith(SEPARATOR) && !propertyName.endsWith(SEPARATOR);
  }

  private Object propertyValue(String propertyName, Object target) {
    PropertyDescriptor descriptor = descriptorForProperty(propertyName, target);
    try {
      return javaBeanDescriptor.invokeReadMethod(descriptor, target);
    } catch (Throwable unexpected) {
      String msg = String.format("Unable to obtain the value of the property <'%s'> from <%s>", propertyName, target);
      throw new IntrospectionError(msg, unexpected);
    }
  }
}
