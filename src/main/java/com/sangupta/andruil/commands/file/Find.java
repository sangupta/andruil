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

package com.sangupta.andruil.commands.file;

import com.sangupta.andruil.commands.base.AbstractCommand;

/**
 * @author sangupta
 *
 */
public class Find extends AbstractCommand {

	/**
	 * @see com.sangupta.andruil.command.AbstractCommand#getCommandName()
	 */
	@Override
	public String getName() {
		return "find";
	}

	/**
	 * @see com.sangupta.andruil.command.AbstractCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Searches for a text string in a file or files";
	}

	@Override
	public void execute(String[] args) {
		// TODO Auto-generated method stub
	}

}