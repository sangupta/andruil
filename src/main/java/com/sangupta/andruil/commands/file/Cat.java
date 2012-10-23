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
import java.io.FileReader;
import java.io.IOException;

import com.sangupta.andruil.commands.base.AbstractMultiFileCommand;

/**
 * @author sangupta
 *
 */
public class Cat extends AbstractMultiFileCommand {
	
	private String line;

	/**
	 * @see com.sangupta.andruil.commands.base.AbstractMultiFileCommand#processFile(java.io.File)
	 */
	@Override
	protected boolean processFile(File file) throws IOException {
		if(file.isDirectory()) {
			return true;
		}

		BufferedReader reader = new BufferedReader(new FileReader(file));
		while((line = reader.readLine()) != null) {
			this.out.println(line);
		}
		
		reader.close();
		return true;
	}
	
	/**
	 * @see com.sangupta.andruil.commands.base.AbstractMultiFileCommand#postProcess()
	 */
	@Override
	protected void postProcess() {
		line = null;
	}

	/**
	 * @see com.sangupta.andruil.commands.base.AbstractCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return "cat";
	}

	/**
	 * @see com.sangupta.andruil.commands.base.AbstractCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Concatenate FILE(s) to standard output";
	}

}
