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

package com.sangupta.andruil.commands.andruil;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

/**
 * Report the current memory usage.
 * 
 * @author sangupta
 *
 */
public class MemoryUsage extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "mem";
	}

	@Override
	public String getHelpLine() {
		return "Display the memory usage information of this shell";
	}

	@Override
	public void execute(String[] arguments) {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Free Memory: " + runtime.freeMemory() + " bytes");
		System.out.println("Currently Used: " + runtime.totalMemory() + " bytes");
		System.out.println("Max Available: " + runtime.maxMemory() + " bytes");
	}

}
