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

package com.sangupta.andruil.commands.checksum;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.sangupta.andruil.commands.base.AbstractMultiFileCommand;

/**
 * @author sangupta
 *
 */
public class SHA1 extends AbstractMultiFileCommand {

	/**
	 * @see com.sangupta.andruil.commands.AbstractCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return "sha1";
	}

	/**
	 * @see com.sangupta.andruil.commands.AbstractCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Computes SHA1 hash of the given file";
	}

	/**
	 * @see com.sangupta.andruil.commands.AbstractCommand#execute(java.lang.String[])
	 */
	@Override
	protected boolean processFile(File file) throws IOException {
		if(file.isDirectory()) {
			return true;
		}
		
		byte[] bytes = FileUtils.readFileToByteArray(file);
		try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA1");
	        byte[] array = md.digest(bytes);
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        
	        this.out.println(sb.toString() + " *" + file.getName());
	    } catch (java.security.NoSuchAlgorithmException e) {
	    	// do nothing
	    	this.out.println("No SHA1 implementation available");
	    	return false;
	    }
		
		return true;
	}

}
