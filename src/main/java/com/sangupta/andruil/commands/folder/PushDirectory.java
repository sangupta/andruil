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

package com.sangupta.andruil.commands.folder;

import java.io.File;
import java.util.Stack;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

/**
 * 
 * @author sangupta
 *
 */
public class PushDirectory extends AbstractAndruilCommand {
	
	private static final Stack<File> directories = new Stack<File>();

	@Override
	public String getName() {
		return "pushd";
	}

	@Override
	public String getHelpLine() {
		return "Save the current directory onto the stack.";
	}

	@Override
	public void execute(String[] arguments) {
		directories.push(this.shellContext.getCurrentDirectory());
	}

	/**
	 * 
	 * @author sangupta
	 *
	 */
	public static class PopDirectory extends AbstractAndruilCommand {

		@Override
		public String getName() {
			return "popd";
		}

		@Override
		public String getHelpLine() {
			return "Restores the previous value of the current directory saved by PUSHD";
		}

		@Override
		public void execute(String[] arguments) {
			if(directories.size() == 0) {
				return;
			}
			
			File dir = directories.pop();
			this.shellContext.changeCurrentDirectory(dir);
		}
		
	}
}
