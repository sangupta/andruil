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

package com.sangupta.andruil.commands;

import com.sangupta.andruil.commands.base.AbstractCommand;
import com.sangupta.andruil.support.Environment;

/**
 * Command to output the version of Andruil shell.
 * 
 * @author sangupta
 *
 */
public class AndruilVersion extends AbstractCommand {

	public String getCommandName() {
		return "me";
	}

	protected void execute(String[] args) {
		println("");
		println("Andruil - Java Command Shell");
		println("by Sandeep Gupta - http://www.sangupta.com");
		println("Version 0.0.1");
		println("");
		println("Andruil has been up for " + Environment.timeKeeper.uptime());
	}

	@Override
	public String[] getCommandAlias() {
		return null;
	}

	@Override
	public String getHelpLine() {
		return "Display Andruil shell version";
	}

}
