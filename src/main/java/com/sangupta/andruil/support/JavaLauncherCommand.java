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

package com.sangupta.andruil.support;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;
import com.sangupta.andruil.launcher.JavaLauncher;

/**
 * A command wrapper that encapsulates an existing Java command line
 * application to be used inside Andruil.
 * 
 * @author sangupta
 *
 */
public class JavaLauncherCommand extends AbstractAndruilCommand {
	
	private JavaPackage pack;
	
	public JavaLauncherCommand(JavaPackage pack) {
		this.pack = pack;
	}

	@Override
	public String getName() {
		return this.pack.name;
	}

	@Override
	public String getHelpLine() {
		return this.pack.description;
	}

	@Override
	public void execute(String[] arguments) {
		@SuppressWarnings("rawtypes")
		Class clazz = null;
		
		try {
			clazz = Class.forName(this.pack.main);
		} catch (ClassNotFoundException e) {
			// TODO: log this
			e.printStackTrace();
			return;
		}
		
		JavaLauncher.runClass(clazz, arguments);
	}
	
}