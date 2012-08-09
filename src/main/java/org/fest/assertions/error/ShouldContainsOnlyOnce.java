package org.fest.assertions.error;

public class ShouldContainsOnlyOnce extends BasicErrorMessageFactory {

  public static ErrorMessageFactory shouldContainsOnlyOnce(String actual, String expected, int occurences) {
	if (actual == null || expected == null) {
		return new ShouldContainsOnlyOnce();
	}
	if (occurences == 0) {
		return new ShouldContainsOnlyOnce(actual, expected);
	}
    return new ShouldContainsOnlyOnce(actual, expected, occurences);
  }

  private ShouldContainsOnlyOnce() {
	super("expecting:<%s> or <%s> not to be null", "", "");
  }
  
  private ShouldContainsOnlyOnce(String actual, String expected, int occurences) {
    super("expecting:\n<%s>\n to appear only once in:\n<%s>\n but appears %s times", expected, actual, occurences);
  }
  
  private ShouldContainsOnlyOnce(String actual, String expected) {
	super("expecting:\n<%s>\n to appear only once in:\n<%s>\n but it doesn't appear", expected, actual);
  }
}
