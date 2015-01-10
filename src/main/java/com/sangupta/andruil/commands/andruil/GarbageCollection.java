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

package com.sangupta.andruil.commands.andruil;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

/**
 * Runs the java garbage collector as well as finalization. This
 * may help clean up some memory if pending.
 * 
 * @author sangupta
 *
 */
public class GarbageCollection extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "gc";
	}

	@Override
	public String getHelpLine() {
		return "Run the garbage collection.";
	}

	@Override
	public void execute(String[] args) {
		System.out.println("Running finalization...");
		Runtime.getRuntime().runFinalization();
		
		System.out.println("Requesting garbage collection...");
		System.gc();
	}

}