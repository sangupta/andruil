/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012, Sandeep Gupta
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.sangupta.andruil.commands.base.AbstractCommand;

/**
 * 
 * @author sangupta
 *
 */
public class Tail extends AbstractCommand {

	@Override
	public String getCommandName() {
		return "tail";
	}

	@Override
	public String getHelpLine() {
		return "Displays the last part of the file";
	}
	
	@Override
	protected void execute(String[] args) throws Exception {
		if(args.length == 0) {
			println("No file specified.");
			return;
		}
		
		File file = resolveFile(args[0]);
		if(!file.exists()) {
			println("File does not exists.");
			return;
		}
		
		if(file.isDirectory()) {
			println("File is a directory.");
			return;
		}
		
		tail(file, 10, 1);
	}
	
	/**
	 * @param file
	 * @throws FileNotFoundException 
	 */
	private void tail(final File file, int numLines, int sleepSeconds) throws IOException {
		final String[] lines = new String[numLines];
		Arrays.fill(lines, null);
		
		int currentIndex = 0;
		boolean arrayFull = false;
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while(true) {
			while((line = reader.readLine()) != null) {
				lines[currentIndex++] = line;

				// rotate
				if(currentIndex == numLines) {
					currentIndex = 0;
					arrayFull = true;
				}
			}
			
			// at this point we have the buffer populated
			// output the array
			if(!arrayFull || currentIndex == 0) {
				for(int index = 0; index < numLines; index++) {
					println(lines[index]);
				}
			} else {
				// print the rotated array
				for(int index = currentIndex + 1; index < numLines; index++) {
					println(lines[index]);
				}
				for(int index = 0; index < currentIndex; index++) {
					println(lines[index]);
				}
			}

			break;
		}
		
		// wait for one second
		// and output all available lines
		do {
			try {
				Thread.sleep(sleepSeconds * 1000);
			} catch(InterruptedException e) {
				// we need to break out
				break;
			}
			
			// output new lines
			while((line = reader.readLine()) != null) {
				println(line);
			}
		} while(true);
		
		reader.close();
	}
	
}
