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

package com.sangupta.andruil.commands.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.sangupta.andruil.commands.base.AbstractMultiFileCommand;

/**
 * Display word count stats for a given file
 * 
 * @author sangupta
 *
 */
public class WordCount extends AbstractMultiFileCommand {
	
	/**
	 * @see com.sangupta.andruil.commands.base.AbstractMultiFileCommand#preProcess()
	 */
	@Override
	protected void preProcess() {
		System.out.println("NumLines MaxLineLength Words Bytes AbsoluteFilePath");
	}
	

	/**
	 * @see com.sangupta.andruil.commands.base.AbstractMultiFileCommand#processFile(java.io.File)
	 */
	@Override
	protected boolean processFile(File file) throws IOException {
		if(file.isDirectory()) {
			return true;
		}
		
		long lines = 0;
		long words = 0;
		int maxLength = 0;
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while((line = reader.readLine()) != null) {
			lines++;
			int max = line.length();
			if(maxLength < max) {
				maxLength = max;
			}
			
			for(int index = 0; index < max; index++) {
				char c = line.charAt(index);
				switch(c) {
					case ' ':
					case '\t':
						words++;
						break;
						
					default:
						// do nothing
				}
			}
		}
		
		System.out.print(lines);
		System.out.print(" ");
		System.out.print(maxLength);
		System.out.print(" ");
		System.out.print(words);
		System.out.print(" ");
		System.out.print(file.length());
		System.out.print(" ");
		System.out.println(file.getAbsoluteFile().getAbsolutePath());
		
		reader.close();
		return true;
	}

	/**
	 * @see com.sangupta.andruil.commands.base.AbstractAndruilCommand#getCommandName()
	 */
	@Override
	public String getName() {
		return "wc";
	}

	/**
	 * @see com.sangupta.andruil.commands.base.AbstractAndruilCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Count number of new lines, words and bytes in file";
	}

}