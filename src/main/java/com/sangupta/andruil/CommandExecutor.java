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

import java.util.HashMap;
import java.util.Map;

import com.sangupta.andruil.command.ChangeDirectoryCommand;
import com.sangupta.andruil.command.DateCommand;
import com.sangupta.andruil.command.FileCompareCommand;
import com.sangupta.andruil.command.GarbageCollectionCommand;
import com.sangupta.andruil.command.HelpListCommand;
import com.sangupta.andruil.command.ClearScreenCommand;
import com.sangupta.andruil.command.CreateDirectoryCommand;
import com.sangupta.andruil.command.ListDirFilesCommand;
import com.sangupta.andruil.command.MD5Command;
import com.sangupta.andruil.command.QuitShellCommand;
import com.sangupta.andruil.command.RemoveDirectoryCommand;
import com.sangupta.andruil.command.TailCommand;
import com.sangupta.andruil.command.TypeCommand;
import com.sangupta.andruil.command.VersionCommand;
import com.sangupta.andruil.command.WhoAmICommand;

public class CommandExecutor {
	
	private static Map<String, Command> commandMap = new HashMap<String, Command>();
	
	// TODO: this should be changed to read all commands from the com.sangupta.andruil.command
	// package via reflection.
	private static Command[] commands = {
		new ClearScreenCommand(),
		new QuitShellCommand(),
		new WhoAmICommand(),
		new VersionCommand(),
		new HelpListCommand(),
		new TypeCommand(),
		new CreateDirectoryCommand(),
		new RemoveDirectoryCommand(),
		new MD5Command(),
		new FileCompareCommand(),
		new TailCommand(),
		new ChangeDirectoryCommand(),
		new DateCommand(),
		new ListDirFilesCommand(),
		new GarbageCollectionCommand()
	};
	
	static {
		// start building the command map
		for(Command command : commands) {
			commandMap.put(command.getCommandName(), command);

			// for alias
			String[] alias = command.getCommandAlias();
			if(alias != null) {
				for(String name : alias) {
					commandMap.put(name, command);
				}
			}
		}
	}

	public static Command[] getCommands() {
		return commands;
	}

	public static boolean isExecutableCommand(String command) {
		if(commandMap.containsKey(command)) {
			return true;
		}
		
		return false;
	}

	public static void executeCommand(String commandName, String arguments) {
		Command command = commandMap.get(commandName);

		String[] args;
		if(arguments == null) {
			args = new String[0];
		} else {
			args = arguments.split(" ");
		}

		command.setOutputWriter(Shell.getOutStream());
		command.run(args);
	}

}
