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

package com.sangupta.andruil.commands.base;

import java.io.File;
import java.io.IOException;

import com.sangupta.andruil.constants.WindowsErrorMessages;
import com.sangupta.andruil.utils.ArgumentUtils;

/**
 * Base class that takes multiple file arguments over the command line and operate
 * over the same.
 * 
 * @author sangupta
 *
 */
public abstract class AbstractMultiFileCommand extends AbstractCommand {

	/**
	 * @see com.sangupta.andruil.commands.base.AbstractCommand#execute(java.lang.String[])
	 */
	@Override
	public void execute(String[] args) {
		preProcess();
		
		if(args.length == 0) {
			System.out.println(WindowsErrorMessages.INCORRECT_SYNTAX);
			return;
		}
		
		// read a list of all files that need to be worked upon
		for(String arg : args) {
			File[] files = ArgumentUtils.resolveFiles(this.shellContext.getCurrentDirectory(), arg);
		
			if(files == null) {
				System.out.println(WindowsErrorMessages.FILE_NOT_FOUND);
				return;
			}
			
			if(files.length == 1) {
				File file = files[0];
				if(!file.exists()) {
					System.out.println(WindowsErrorMessages.FILE_NOT_FOUND);
					return;
				}
				
				if(file.isDirectory()) {
					System.out.println(WindowsErrorMessages.FILE_IS_A_FOLDER);
					return;
				}
			}
			
			boolean cont = true;
			for(File file : files) {
				
				try {
					cont = processFile(file);
				} catch(IOException e) {
					// unable to process file
					e.printStackTrace();
				}
				
				if(!cont) {
					break;
				}
			}
		}
		
		postProcess();
	}
	
	/**
	 * The worker method that processes the file from the list of arguments. Must return <code>true</code>
	 * if the next file needs to be processed, or <code>false</code> if execution needs to break right away.
	 * 
	 * @param file
	 * @return
	 */
	protected abstract boolean processFile(File file) throws IOException;
	
	/**
	 * Method that is invoked before any file starts getting processed.
	 * 
	 */
	protected void preProcess() {
		
	}
	
	/**
	 * Method that is invoked once all the files have been processed.
	 * 
	 */
	protected void postProcess() {
		
	}

}
