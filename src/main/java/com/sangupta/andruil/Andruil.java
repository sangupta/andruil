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

import jline.console.completer.FileNameCompleter;
import jline.console.completer.StringsCompleter;

public class Andruil {
	
	public static void main(String[] args) throws Exception {
		// prepare the shell
		Shell.setPrompt(createPromptText(getCurrentDirectory()));
		
		// add the filename completer
		Shell.addCompleter(new FileNameCompleter());
		
		// build up the list of commands
		List<String> commandNames = new ArrayList<String>();
		for(Command command : CommandExecutor.getCommands()) {
			commandNames.add(command.getCommandName());
		}
		Shell.addCompleter(new StringsCompleter(commandNames));
		
		// start the shell
		Shell.run();
	}
	
	private static String createPromptText(File file) {
		if(file == null) {
			return null; 
		}
		
		if(file.isDirectory()) {
			return file.getAbsolutePath() + ">";
		}
		
		return file.getParentFile().getAbsolutePath() + ">";
	}

	public static File getCurrentDirectory() {
		File file = new File(".").getAbsoluteFile().getParentFile();
		return file;
	}

}
