/*
 * Created on Jan 27, 2011
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
 * Copyright @2011-2012 the original author or authors.
 */
package org.fest.assertions.test;

import static java.nio.charset.Charset.defaultCharset;
import static org.fest.util.Closeables.closeQuietly;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

/**
 * @author Yvonne Wang
 * @author Olivier Michallat
 * @author Alex Ruiz
 */
public class TextFileWriter {
  private final String[] content;
  private Charset charset = defaultCharset();

  public static TextFileWriter write(String...content) {
    return new TextFileWriter(content);
  }

  private TextFileWriter(String[] content) {
    this.content = content;
  }

  public TextFileWriter using(Charset charset) {
    this.charset = charset;
    return this;
  }

  public void to(File file) throws IOException {
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file), charset));
      for (String line : content) {
        writer.println(line);
      }
    } finally {
      closeQuietly(writer);
    }
  }
}
