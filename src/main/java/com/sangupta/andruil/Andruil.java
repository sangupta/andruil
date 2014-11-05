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

package com.sangupta.andruil;


import io.airlift.command.Cli;
import io.airlift.command.Command;
import io.airlift.command.Help;
import io.airlift.command.Option;
import io.airlift.command.OptionType;
import io.airlift.command.ParseArgumentsUnexpectedException;
import io.airlift.command.Cli.CliBuilder;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import com.google.common.io.Resources;
import com.sangupta.andruil.support.Environment;
import com.sangupta.andruil.support.JavaLauncherCommand;
import com.sangupta.andruil.support.JavaPackage;
import com.sangupta.consoles.ConsoleType;
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
	 * Main entry point from Operating system. We now parse the command line
	 * arguments as well.
	 * 
	 * @param args
	 *            the command line arguments supplied
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		CliBuilder<Runnable> builder = Cli.<Runnable>builder("andruil")
				  .withDescription("Cross-platform Power Shell")
				  .withDefaultCommand(AndruilCLI.class)
				  .withCommands(Help.class, AndruilCLI.class);
		
		Cli<Runnable> cliParser = builder.build();
		
		try {
			cliParser.parse(args).run();
		} catch(ParseArgumentsUnexpectedException e) {
			System.out.println("Invalid options, use $ andruil help for usage instructions.");
		}
	}
	
	/**
	 * Allow launching of {@link Andruil} using the given options.
	 * 
	 * @param options
	 *            the provided {@link AndruilOptions}
	 */
	public static void launch(AndruilOptions options) {
		if(options == null) {
			throw new IllegalArgumentException("Andruil options cannot be null");
		}
		
		launchAndruil(options);
	}
	
	/**
	 * Launch the shell now.
	 * 
	 * @param options
	 */
	private static void launchAndruil(final AndruilOptions options) {
		// first thing is to initialize the environment
		Environment.initialize();
		
		// create the context
		// start the shell
		// Create the shell instance
		HuskShell huskShell = new HuskShell(options.consoleType, options.numRows, options.numColumns);
		
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

	/**
	 * 
	 * @return
	 */
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
	
	@Command(name = "launch", description = "Launch the andruil power shell")
	public static class AndruilCLI implements Runnable {
		
		@Option(name = { "-r", "--rows" }, description = "Number of rows in the console", type = OptionType.GLOBAL)
		public int rows = 25;
		
		@Option(name = { "-c", "--cols" }, description = "Number of columns in the console", type = OptionType.GLOBAL)
		public int columns = 80;
		
		@Option(name = { "-t", "--type" }, description = "The desired console type", type = OptionType.GLOBAL)
		public String consoleType = ConsoleType.BestEffort.toString();

		@Override
		public void run() {
			AndruilOptions options = new AndruilOptions();
			
			// set the newer values for options
			options.numRows = rows;
			options.numColumns = columns;
			options.consoleType = ConsoleType.fromString(this.consoleType);
			
			Andruil.launchAndruil(options);
		}
		
	}

}
