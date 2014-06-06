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

package com.sangupta.andruil;

import com.sangupta.andruil.support.Environment;
import com.sangupta.husk.HuskShell;

/**
 * Main entry point for the shell.
 * 
 * @author sangupta
 *
 */
public class Andruil {
	
	/**
	 * Main entry point from Operating system.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// first thing is to initialize the environment
		Environment.initialize();
		
		// TODO: we need to read the command line arguments to this shell
		// this may provide customization options etc.
		
		// create the context
		// start the shell
		// Create the shell instance
		HuskShell huskShell = new HuskShell(25, 120);
		
		AndruilPromptProvider promptProvider = new AndruilPromptProvider(huskShell.getCurrentShellContext());
		
		// prepare for launch
		huskShell.initialize();

		huskShell.setPromptProvider(promptProvider);
		huskShell.setTitle("Andruil Shell");
		huskShell.setExitCommandNames("exit");
		huskShell.setHelpCommandNames("help");
		huskShell.loadExternalCommands("com.sangupta.andruil.commands");
		
		// start the shell instance
		try {
			huskShell.start();
		} catch(Throwable t) {
			t.printStackTrace();
		}
		
		// stop and destroy all resources associated with the shell
		huskShell.stop();
		
		// when we are done
		// make an exit
		Environment.timeKeeper.close();
		System.out.print("\n\n");
		System.out.println(Environment.timeKeeper);
	}

}
