/**
 *
 */
package org.fest.assertions.error;

import java.io.File;

/**
 * Creates an error message indicating that an assertion that verifies that a <code>{@link File}</code> is readable
 * failed.
 * 
 * @author Olivier Demeijer
 * 
 */
public class ShouldBeReadable extends BasicErrorMessageFactory {
  private ShouldBeReadable(File actual) {
    super("File:<%s> should be readable", actual);
  }

  /**
   * Creates a new <code>{@link ShouldBeReadable}</code>.
   * @param actual the actual value in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeReadable(File actual) {
    return new ShouldBeReadable(actual);
  }

}
