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

package com.sangupta.andruil.commands.folder;

import java.io.File;

import com.sangupta.andruil.Andruil;
import com.sangupta.andruil.commands.AbstractCommand;

public class ChangeDirectoryCommand extends AbstractCommand {

	@Override
	public String getCommandName() {
		return "cd";
	}

	@Override
	public String getHelpLine() {
		return "Displays the name of or changes the current directory";
	}

	@Override
	protected void execute(String[] args) throws Exception {
		if(args.length == 0) {
			this.out.println(getCurrentWorkingDirectory());
			return;
		}
		
		String dirName = args[0].trim();
		
		if(".".equals(dirName)) {
			return;
		}
		
		File dir = null;
		
		if("..".equals(dirName)) {
			dir = new File(getCurrentWorkingDirectory()).getParentFile();
		}
		
		if("\\".equals(dirName) || "/".equals(dirName)) {
			dir = new File("/");
		}
		
		if(dir == null) {
			dir = resolveFile(dirName);
		}
		
		if(!dir.exists()) {
			this.out.println("The system cannot find the file specified.");
			return;
		}
		
		if(!dir.isDirectory()) {
			this.out.println("Access is denied.");
			return;
		}
		
		Andruil.changeCurrentDirectory(dir);
	}

}
