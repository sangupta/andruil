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

import java.util.List;

import com.sangupta.andruil.Command;
import com.sangupta.andruil.CommandExecutor;

public class HelpListCommand extends AbstractCommand {

	@Override
	public String getCommandName() {
		return "list";
	}

	@Override
	protected void execute(String[] args) {
		List<Command> commands = CommandExecutor.getCommands();
		for(Command command : commands) {
			String commandName = command.getCommandName();
			String helpLine = command.getHelpLine();
			
			System.out.print(commandName);
			System.out.print(": ");
			System.out.println(helpLine);
		}
	}
	
	@Override
	public String[] getCommandAlias() {
		return null;
	}

	@Override
	public String getHelpLine() {
		return "Show list of available commands";
	}

}
