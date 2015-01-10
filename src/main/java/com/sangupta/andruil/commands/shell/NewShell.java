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

import java.io.File;
import java.io.IOException;

import com.sangupta.andruil.Andruil;
import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

/**
 * Create and fork a new shell so that we can multiple instances
 * of the shell with us.
 * 
 * @author sangupta
 *
 */
public class NewShell extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "new";
	}

	@Override
	public String getHelpLine() {
		return "Fork and create a new shell";
	}

	@Override
	public void execute(String[] arguments) {
		 try {
			exec(Andruil.class);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private int exec(@SuppressWarnings("rawtypes") Class klass) throws IOException, InterruptedException {
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = klass.getCanonicalName();

		ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);

		Process process = builder.start();
		return 0;
	}

}