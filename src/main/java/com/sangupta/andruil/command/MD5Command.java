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

import java.io.File;

import org.apache.commons.io.FileUtils;

public class MD5Command extends AbstractCommand {

	@Override
	public String getCommandName() {
		return "md5";
	}

	@Override
	public String getHelpLine() {
		return "Computes MD5 of the given file.";
	}

	@Override
	protected void execute(String[] args) throws Exception {
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
			getOut().println("File is a directory.");
			return;
		}
		
		byte[] bytes = FileUtils.readFileToByteArray(file);
		try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(bytes);
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        
	        getOut().println(sb.toString());
	    } catch (java.security.NoSuchAlgorithmException e) {
	    	// do nothing
	    }
	}

}
