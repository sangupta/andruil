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


/**
 * Contract for all implementation that need to serve as a command in the
 * Andruil shell.
 * 
 * All in-built commands must implement this interface. This should provide
 * the shell to start fast.
 * 
 * In future, we may add the capability to use annotations for building
 * commands, but implementing an interface is easier to keep up with
 * compatibility and adding newer features.
 * 
 * @author sangupta
 * 
 */
public interface Command {
	
	/**
	 * Get the unique command name
	 * 
	 * @return
	 */
	public String getCommandName();
	
	/**
	 * Get the alias with which this command may be known as. This should be unique
	 * in the list of all command names and their aliases.
	 * 
	 * @return
	 */
	public String[] getCommandAlias();
	
	/**
	 * Get the single line help about this command.
	 * 
	 * @return
	 */
	public String getHelpLine();
	
	/**
	 * Execute the command with the given arguments
	 * 
	 * @param args
	 */
	public void run(String[] args);
	
	/**
	 * Return the help on this command.
	 * 
	 * @return
	 */
	public String getHelp();
	
}
