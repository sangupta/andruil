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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javassist.Modifier;

import org.reflections.Reflections;

/**
 * The basic command executor.
 * 
 * @author sangupta
 *
 */
public class CommandExecutor {
	
	/**
	 * A map of all command objects, where the command name is the key
	 */
	private static Map<String, Command> commandMap = new HashMap<String, Command>();
	
	/**
	 * A list of all command objects
	 */
	private static final List<Command> commands = new ArrayList<Command>();
	
	/**
	 * Read all commands via reflection
	 */
	static {
		Reflections reflections = new Reflections("com.sangupta.andruil.commands");
		Set<Class<? extends Command>> foundCommands = reflections.getSubTypesOf(Command.class);
		
		for(Class<? extends Command> clazz : foundCommands) {
			Command command;
			try {
				if(Modifier.isAbstract(clazz.getModifiers())) {
					// no need to instantiate abstract classes
					continue;
				}
				
				command = clazz.newInstance();
				
				CommandExecutor.commands.add(command);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
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

	public static List<Command> getCommands() {
		return commands;
	}

	/**
	 * Checks if a command name is executable, by finding a matching command object
	 * in our registry.
	 * 
	 * @param command
	 * @return
	 */
	public static boolean isExecutableCommand(String command) {
		if(commandMap.containsKey(command)) {
			return true;
		}
		
		return false;
	}

	/**
	 * Execute a given command
	 * 
	 * @param commandName
	 * @param arguments
	 */
	public static void executeCommand(String commandName, String arguments) {
		Command command = commandMap.get(commandName);

		String[] args;
		if(arguments == null) {
			args = new String[0];
		} else {
			args = arguments.split(" ");
		}

		command.run(args);
	}

}
