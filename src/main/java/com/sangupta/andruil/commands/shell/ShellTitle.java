/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012-15, Sandeep Gupta
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

package com.sangupta.andruil.commands.shell;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

/**
 * Change the title of the shell window.
 * 
 * @author sangupta
 *
 */
public class ShellTitle extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "title";
	}

	@Override
	public String getHelpLine() {
		return "Change the title of the shell window";
	}

	@Override
	public void execute(String[] arguments) {
		if(arguments.length == 0) {
			return;
		}
		
		if(arguments.length == 1) {
			this.shellContext.getConsole().setWindowTitle(arguments[0]);
		}
		
		StringBuilder builder = new StringBuilder();
		for(String arg : arguments) {
			builder.append(arg);
			builder.append(' ');
		}
		
		this.shellContext.getConsole().setWindowTitle(builder.toString().trim());
	}

}