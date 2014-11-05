/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012-2014, Sandeep Gupta
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
import java.util.HashSet;
import java.util.Set;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;
import com.sangupta.jerry.util.HashUtils;

/**
 * Find number of unique lines in a text file.
 * 
 * @author sangupta
 *
 */
public class UniqueLines extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "uniq";
	}

	@Override
	public String getHelpLine() {
		return "Find number of unique lines in a text file";
	}

	@Override
	public void execute(String[] arguments) {
		if(arguments.length == 0) {
			System.out.println("No file specified.");
			return;
		}
		
		File file = new File(arguments[0]);
		if(!file.exists()) {
			System.out.println("File does not exists.");
			return;
		}
		
		if(file.isDirectory()) {
			System.out.println("File is a directory.");
			return;
		}
		
		try {
			process(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		Set<String> hashes = new HashSet<String>();
		String line;
		
		int lineNumber = 0;
		int duplicates = 0;
		while((line = reader.readLine()) != null) {
			lineNumber++;
			String md5 = HashUtils.getMD5Hex(line);
			if(hashes.contains(md5)) {
				// System.out.println("Duplicate line found at line: " + lineNumber);
				duplicates++;
			} else {
				hashes.add(md5);
			}
		}
	
		System.out.println("Total lines in file: " + lineNumber);
		System.out.println("Total unique lines: " + (lineNumber - duplicates));
		System.out.println("Total duplicate lines: " + duplicates);
		
		reader.close();
	}

}
