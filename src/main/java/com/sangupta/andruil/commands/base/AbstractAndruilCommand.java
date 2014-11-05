/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012-2014, Sandeep Gupta
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

import com.sangupta.husk.HuskShellCommand;
import com.sangupta.husk.core.HuskShellContext;
import com.sangupta.husk.core.HuskShellContextAware;

/**
 * A base abstract implementation of {@link HuskShellCommand} that also provides
 * support for {@link HuskShellContextAware} by providing each command the necessary
 * shell context.
 * 
 * @author sangupta
 *
 */
public abstract class AbstractAndruilCommand implements HuskShellCommand, HuskShellContextAware {
	
	/**
	 * Reference to the current shell context
	 */
	protected HuskShellContext shellContext;
	
	@Override
	public String[] getNameAlias() {
		return null;
	}
	
	/**
	 * Return the current working directory of the shell.
	 * 
	 * @return
	 */
	public String getCurrentWorkingDirectory() {
		return this.shellContext.getCurrentDirectory().getAbsolutePath();
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
	
	@Override
	public void setShellContext(HuskShellContext shellContext) {
		this.shellContext = shellContext;
	}

}
