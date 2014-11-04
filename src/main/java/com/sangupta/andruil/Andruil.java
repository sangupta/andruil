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


import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import com.google.common.io.Resources;
import com.sangupta.andruil.support.Environment;
import com.sangupta.andruil.support.JavaLauncherCommand;
import com.sangupta.andruil.support.JavaPackage;
import com.sangupta.husk.HuskShell;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.GsonUtils;

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
		
		// load all Java package commands
		// find the file javapack.json and load it as well
		JavaPackage[] packs = loadExternalJavaPackages();
		if(AssertUtils.isNotEmpty(packs)) {
			for(JavaPackage pack : packs) {
				huskShell.addCommand(new JavaLauncherCommand(pack));
			}
		}
		
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
		System.out.println(Environment.timeKeeper);
	}

	private static JavaPackage[] loadExternalJavaPackages() {
		URL url = Resources.getResource("javapack.json");
		if(url == null) {
			return null;
		}
		
		String json;
		try {
			json = Resources.toString(url, Charset.defaultCharset());
		} catch (IOException e) {
			// TODO: log this
			return null;
		}

		if(AssertUtils.isEmpty(json)) {
			return null;
		}
		
		return GsonUtils.getGson().fromJson(json, JavaPackage[].class);
	}

}
