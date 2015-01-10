/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012-2015, Sandeep Gupta
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

package com.sangupta.andruil.commands.andruil;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;
import com.sangupta.andruil.support.Environment;

/**
 * Command to output the version of Andruil shell.
 * 
 * @author sangupta
 *
 */
public class AndruilVersion extends AbstractAndruilCommand {

	public String getName() {
		return "me";
	}

	public void execute(String[] arguments) {
		System.out.println("");
		System.out.println("Andruil - Java Command Shell");
		System.out.println("by Sandeep Gupta - http://www.sangupta.com");
		System.out.println("Version 0.0.1");
		System.out.println("");
		System.out.println("Andruil has been up for " + Environment.timeKeeper.uptime());
	}

	@Override
	public String getHelpLine() {
		return "Display Andruil shell version";
	}

}