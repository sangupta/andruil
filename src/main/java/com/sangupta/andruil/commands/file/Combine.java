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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

/**
 * Combine multiple files into one.
 * 
 * @author sangupta
 *
 */
public class Combine extends AbstractAndruilCommand {

	@Override
	public void execute(String[] args) {
		if(args.length < 3) {
			System.out.println("Usage: combine <out-file> <file1> <file2> ...");
			return;
		}
		
		File outFile = resolveFile(args[0]);
		if(outFile.exists() && outFile.isFile()) {
			System.out.println("Output file exists... exiting!");
			return;
		}
		
		try {
			for(int index = 1; index < args.length; index++) {
				File inFile = resolveFile(args[index]);
				if(!(inFile.exists() && inFile.isFile())) {
					continue;
				}
				
				byte[] bytes = FileUtils.readFileToByteArray(inFile);
				FileUtils.writeByteArrayToFile(outFile, bytes, true);
			}
		} catch(IOException e) {
			System.out.println("Unable to copy files: " + e.getMessage());
		}
	}

	@Override
	public String getHelpLine() {
		return "Combine multiple files into one";
	}

	@Override
	public String getName() {
		return "combine";
	}

}