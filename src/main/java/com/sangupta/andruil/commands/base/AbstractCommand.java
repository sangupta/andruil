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

package com.sangupta.andruil.commands.base;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.sangupta.andruil.Andruil;
import com.sangupta.andruil.Command;
import com.sangupta.andruil.Shell;

/**
 * @author sangupta
 *
 */
public abstract class AbstractCommand implements Command {
	
	/**
	 * Command's current output stream
	 */
	protected PrintWriter out = Shell.getOutStream();
	
	/**
	 * @see com.sangupta.andruil.Command#getCommandName()
	 */
	@Override
	public abstract String getCommandName();

	/**
	 * @see com.sangupta.andruil.Command#getHelpLine()
	 */
	@Override
	public abstract String getHelpLine();

	/**
	 * @see com.sangupta.andruil.Command#getHelp()
	 */
	@Override
	public String getHelp() {
		return null;
	}

	/**
	 * @see com.sangupta.andruil.Command#getCommandAlias()
	 */
	@Override
	public String[] getCommandAlias() {
		return null;
	}

	/**
	 * @see com.sangupta.andruil.Command#run(java.lang.String[])
	 */
	@Override
	public final void run(String[] args) {
		try {
			execute(args);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Print the given string to the output stream and flush it as well.
	 * 
	 * @param string
	 */
	public void print(String string) {
		this.out.print(string);
		this.out.flush();
	}
	
	/**
	 * Print the given string with a new line at the end to the output stream
	 * and flush it as well.
	 * 
	 * @param string
	 */
	public void println(String string) {
		this.out.println(string);
		this.out.flush();
	}
	
	protected String readLine(String prompt) {
		try {
			return Shell.getReader().readLine(prompt);
		} catch(IOException e) {
			
		}
		
		return null;
	}
	
	/**
	 * Return the current working directory of the shell.
	 * 
	 * @return
	 */
	public String getCurrentWorkingDirectory() {
		return Andruil.getCurrentDirectory().getAbsolutePath();
	}
	
	/**
	 * Resolve the file with the given current working directory of the 
	 * shell.
	 * 
	 * @param dirName
	 * @return
	 */
	public File resolveFile(String dirName) {
		return new File(getCurrentWorkingDirectory(), dirName);
	}
	
	/**
	 * Execute the command with the given arguments.
	 * 
	 * @param args
	 * @throws Exception
	 */
	protected abstract void execute(String[] args) throws Exception;

}
