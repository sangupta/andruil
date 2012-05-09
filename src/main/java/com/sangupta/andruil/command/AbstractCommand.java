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
	
	public PrintWriter getOut() {
		return Shell.getOutStream();
	}
	
	public void print(String string) {
		getOut().print(string);
	}
	
	public void println(String string) {
		getOut().println(string);
	}
	
	public String getCurrentWorkingDirectory() {
		return Andruil.getCurrentDirectory().getAbsolutePath();
	}
	
	public File resolveFile(String dirName) {
		return new File(getCurrentWorkingDirectory(), dirName);
	}
	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	protected abstract void execute(String[] args) throws Exception;

}
