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

package com.sangupta.andruil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jline.console.completer.StringsCompleter;

import com.sangupta.andruil.completer.ExtendedFileNameCompleter;
import com.sangupta.andruil.utils.TimeKeeper;

/**
 * Main entry point for the shell.
 * 
 * @author sangupta
 *
 */
public class Andruil {
	
	private TimeKeeper timeKeeper = new TimeKeeper("Andruil");
	
	private static File currentWorkingDirectory = new File(".").getAbsoluteFile().getParentFile();
	
	/**
	 * Main entry point from Operating system.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO: we need to read the command line arguments to this shell
		// this may provide customization options etc.
		
		// start the shell
		Andruil andruil = new Andruil();
		andruil.startShell();
		
		// when we are done
		// make an exit
		System.out.print("\n\n");
		System.out.println(andruil.timeKeeper);
	}
	
	/**
	 * Build and start the shell
	 */
	private void startShell() throws Exception {
		// prepare the shell
		updatePromptText();
		
		// add the filename completer
		Shell.addCompleter(new ExtendedFileNameCompleter());
		
		// build up the list of commands
		List<String> commandNames = new ArrayList<String>();
		for(Command command : CommandExecutor.getCommands()) {
			String name = command.getCommandName();
			if(name != null && !name.trim().equals("")) {
				commandNames.add(name);
			}
		}
		Shell.addCompleter(new StringsCompleter(commandNames));
		
		// start the shell
		Shell.run();
	}
	
	/**
	 * Updates the prompt text to the current directory that the user is in.
	 * 
	 */
	private static void updatePromptText() {
		File file = getCurrentDirectory();
		if(file == null) {
			return; 
		}
		
		String prompt;
		if(file.isDirectory()) {
			prompt = file.getAbsolutePath() + " $ ";
		} else {
			prompt = file.getParentFile().getAbsolutePath() + " $ ";
		}
		
		Shell.setPrompt(prompt);
	}

	/**
	 * Get current working directory
	 *  
	 * @return
	 */
	public static File getCurrentDirectory() {
		return currentWorkingDirectory;
	}

	/**
	 * Change current working directory for the user.
	 * 
	 * @param file
	 */
	public static void changeCurrentDirectory(File file) {
		if(file != null && file.exists() && file.isDirectory()) {
			currentWorkingDirectory = file;
			updatePromptText();
		}
	}

}
