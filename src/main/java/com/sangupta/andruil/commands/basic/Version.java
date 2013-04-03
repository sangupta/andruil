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

package com.sangupta.andruil.commands.basic;

import com.sangupta.andruil.commands.base.AbstractCommand;

/**
 * Output the OS version. Any arguments passed to the command
 * are ignored.
 * 
 * @author sangupta
 *
 */
public class Version extends AbstractCommand {

	/**
	 * @see com.sangupta.andruil.commands.AbstractCommand#getCommandName()
	 */
	@Override
	public String getName() {
		return "ver";
	}

	/**
	 * @see com.sangupta.andruil.commands.AbstractCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Display the OS version";
	}

	@Override
	public void execute(String[] args) {
		String osName = System.getProperty("os.name");
		String osVersion = System.getProperty("os.version");
		
		System.out.println(osName + " [Version " + osVersion + "]");
	}

}
