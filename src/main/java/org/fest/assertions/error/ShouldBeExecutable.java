/**
 *
 */
package org.fest.assertions.error;

import java.io.File;

/**
 * Creates an error message indicating that an assertion that verifies that a <code>{@link File}</code> is executable
 * failed.
 * 
 * @author Olivier Demeijer
 * 
 */
public class ShouldBeExecutable extends BasicErrorMessageFactory {
  private ShouldBeExecutable(File actual) {
    super("File:<%s> should be executable", actual);
  }

  /**
   * Creates a new <code>{@link ShouldBeExecutable}</code>.
   * @param actual the actual value in the failed assertion.
   * @return the created {@code ErrorMessageFactory}.
   */
  public static ErrorMessageFactory shouldBeExecutable(File actual) {
    return new ShouldBeExecutable(actual);
  }

}
