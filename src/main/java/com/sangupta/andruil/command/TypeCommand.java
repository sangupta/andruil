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

package com.sangupta.andruil.command;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TypeCommand extends AbstractCommand {

	@Override
	public String getCommandName() {
		return "type";
	}

	@Override
	public String[] getCommandAlias() {
		return null;
	}

	@Override
	public String getHelpLine() {
		return "Displays the contents of a text file or files.";
	}

	@Override
	protected void execute(String[] args) {
		if(args.length == 0) {
			getOut().println("The syntax of the command is incorrect.");
			return;
		}
		
		File file = new File(args[0]);
		if(!file.exists()) {
			getOut().println("The system cannot find the file specified.");
			return;
		}
		
		if(file.isDirectory()) {
			getOut().println("Access is denied.");
			return;
		}
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
