/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012-2015, Sandeep Gupta
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

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

/**
 * Unix-style tail command.
 * 
 * @author sangupta
 *
 */
public class Tail extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "tail";
	}

	@Override
	public String getHelpLine() {
		return "Displays the last part of the file";
	}
	
	@Override
	public void execute(String[] args) {
		if(args.length == 0) {
			System.out.println("No file specified.");
			return;
		}
		
		File file = resolveFile(args[0]);
		if(!file.exists()) {
			System.out.println("File does not exists.");
			return;
		}
		
		if(file.isDirectory()) {
			System.out.println("File is a directory.");
			return;
		}
		
		try {
			tail(file, 10, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					System.out.println(lines[index]);
				}
			} else {
				// print the rotated array
				for(int index = currentIndex + 1; index < numLines; index++) {
					System.out.println(lines[index]);
				}
				for(int index = 0; index < currentIndex; index++) {
					System.out.println(lines[index]);
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
				System.out.println(line);
			}
		} while(true);
		
		reader.close();
	}
	
}