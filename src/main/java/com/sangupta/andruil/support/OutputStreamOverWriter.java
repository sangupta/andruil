/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012-15, Sandeep Gupta
 * 
 * http://www.sangupta/projects/andruil
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.andruil.support;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * A wrapper over a writer object to make it available as an OutputStream. This
 * is needed to redirect the standard output stream of the console to the JLine
 * console that we are using.
 * 
 * @author sangupta
 * 
 */
public class OutputStreamOverWriter extends OutputStream {

	protected Writer writer;
	
	protected String encoding;
	
	private byte[] buffer = new byte[1];

	/**
	 * 
	 * @param writer
	 * @param encoding
	 */
	public OutputStreamOverWriter(Writer writer, String encoding) {
		this.writer = writer;
		this.encoding = encoding;
	}

	/**
	 * 
	 * @param writer
	 */
	public OutputStreamOverWriter(Writer writer) {
		this.writer = writer;
	}

	/**
	 * 
	 * @see java.io.OutputStream#close()
	 */
	public void close() throws IOException {
		writer.close();
		writer = null;
		encoding = null;
	}

	/**
	 * 
	 * @see java.io.OutputStream#flush()
	 */
	public void flush() throws IOException {
		writer.flush();
	}

	/**
	 * 
	 * @see java.io.OutputStream#write(byte[])
	 */
	public void write(byte[] b) throws IOException {
		if (encoding == null) {
			writer.write(new String(b));
		} else {
			writer.write(new String(b, encoding));
		}
	}

	/**
	 * 
	 * @see java.io.OutputStream#write(byte[], int, int)
	 */
	public void write(byte[] b, int off, int len) throws IOException {
		if (encoding == null) {
			writer.write(new String(b, off, len));
		} else {
			writer.write(new String(b, off, len, encoding));
		}
	}

	/**
	 * 
	 * @see java.io.OutputStream#write(int)
	 */
	public synchronized void write(int b) throws IOException {
		buffer[0] = (byte) b;
		write(buffer);
	}

}