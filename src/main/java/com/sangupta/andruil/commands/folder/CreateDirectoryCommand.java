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

import com.sangupta.andruil.commands.base.AbstractCommand;

/**
 * @author sangupta
 *
 */
public class CreateDirectoryCommand extends AbstractCommand {

	/**
	 * @see com.sangupta.andruil.command.AbstractCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return "mkdir";
	}
	
	/**
	 * @see com.sangupta.andruil.command.AbstractCommand#getCommandAlias()
	 */
	@Override
	public String[] getCommandAlias() {
		String[] values = { "md" };
		return values;
	}

	/**
	 * @see com.sangupta.andruil.command.AbstractCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Creates a directory.";
	}

	/**
	 * @see com.sangupta.andruil.command.AbstractCommand#getHelp()
	 */
	@Override
	public String getHelp() {
		return null;
	}

	/**
	 * @see com.sangupta.andruil.command.AbstractCommand#execute(java.lang.String[])
	 */
	@Override
	protected void execute(String[] args) throws Exception {
		if(args.length == 0) {
			System.out.println("The syntax of the command is incorrect.");
			return;
		}
		
		String dirName = args[0];
		File file = resolveFile(dirName);
		
		if(file.exists()) {
			System.out.print("A subdirectory or file ");
			System.out.print(dirName);
			System.out.println(" already exists.");
			return;
		}
		
		boolean success = file.mkdirs();
		if(!success) {
			System.out.print("Unable to create directory by the name of ");
			System.out.println(dirName);
		}
	}

}
