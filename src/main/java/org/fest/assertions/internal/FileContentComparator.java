/*
 * Created on Feb 9, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.assertions.internal;

import static java.lang.String.format;

import static org.fest.util.Closeables.closeQuietly;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Strings.quote;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.charset.Charset;

/**
 * Compares the contents of two files.
 *
 * @author David DIDIER
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
class FileContentComparator {
  private static final String EOF = "EOF";

  FileDiffs compareContents(File actual, File expected, Charset charset) throws IOException {
    LineNumberReader readerForActual = null;
    LineNumberReader readerForExpected = null;
    try {
      readerForActual = readerFor(actual, charset);
      readerForExpected = readerFor(expected, charset);
      return compare(readerForActual, readerForExpected);
    } finally {
      closeQuietly(readerForActual);
      closeQuietly(readerForExpected);
    }
  }

  private LineNumberReader readerFor(File file, Charset charset) throws IOException {
    FileInputStream in = new FileInputStream(file);
    return new LineNumberReader(new BufferedReader(new InputStreamReader(in, charset)));
  }

  private FileDiffs compare(LineNumberReader readerOfActual, LineNumberReader readerOfExpected) throws IOException {
    FileDiffs diffs = new FileDiffs();
    while (readerOfActual.ready() && readerOfExpected.ready()) {
      int lineNumber = readerOfActual.getLineNumber();
      String lineInExpected = readerOfExpected.readLine();
      String lineInActual = readerOfActual.readLine();
      if (!areEqual(lineInExpected, lineInActual)) {
        diffs.add(diff(lineNumber, quote(lineInExpected), quote(lineInActual)));
      }
    }
    if (!readerOfActual.ready() && readerOfExpected.ready()) {
      int lineNumber = readerOfExpected.getLineNumber();
      String lineInExpected = readerOfExpected.readLine();
      diffs.add(diff(lineNumber, quote(lineInExpected), EOF));
    }
    if (readerOfActual.ready() && !readerOfExpected.ready()) {
      int lineNumber = readerOfActual.getLineNumber();
      String lineInActual = readerOfActual.readLine();
      diffs.add(diff(lineNumber, EOF, quote(lineInActual)));
    }
    return diffs;
  }

  private String diff(int lineNumber, String expected, String actual) {
    return format("line:<%d>, expected:<%s> but was:<%s>", lineNumber, expected, actual);
  }
}
