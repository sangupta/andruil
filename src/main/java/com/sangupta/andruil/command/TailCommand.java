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

package com.sangupta.andruil.command;

import java.io.File;

public class TailCommand extends AbstractCommand {

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
		
		tail(file);
	}

	/**
	 * @param file
	 */
	private void tail(File file) {
		println("File tailed: " + file.getAbsolutePath());
	}

}
