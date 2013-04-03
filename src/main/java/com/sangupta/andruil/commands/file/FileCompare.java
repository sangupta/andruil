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

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.sangupta.andruil.commands.base.AbstractCommand;
import com.sangupta.andruil.constants.WindowsErrorMessages;

/**
 * @author sangupta
 *
 */
public class FileCompare extends AbstractCommand {

	/**
	 * @see com.sangupta.andruil.command.AbstractCommand#getCommandName()
	 */
	@Override
	public String getName() {
		return "fc";
	}

	/**
	 * @see com.sangupta.andruil.command.AbstractCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Compares two given files";
	}

	@Override
	public void execute(String[] args) {
		if(args.length != 2) {
			System.out.println(WindowsErrorMessages.INCORRECT_SYNTAX);
			return;
		}

		File file1 = new File(args[0]);
		File file2 = new File(args[1]);
		
		if(!file1.exists()) {
			System.out.println("FC: Cannot open " + file1.getName() + " - No such file");
			return;
		}
		
		if(!file2.exists()) {
			System.out.println("FC: Cannot open " + file2.getName() + " - No such file");
			return;
		}
		
		if(!file1.isFile()) {
			System.out.println("FC: " + file1.getName() + " is a folder");
			return;
		}

		if(!file2.isFile()) {
			System.out.println("FC: " + file2.getName() + " is a folder");
			return;
		}
		
		boolean equals = false;
		try {
			equals = FileUtils.contentEquals(file1, file2);
		} catch (IOException e) {
			// TODO: fix this
			// eat up and assume we could not compare
		}
		
		if(equals) {
			System.out.println("Files are identical");
			return;
		}
		
		System.out.println("Files are not identical");
	}

}
