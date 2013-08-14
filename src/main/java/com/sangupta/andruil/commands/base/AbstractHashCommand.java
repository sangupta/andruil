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

import org.apache.commons.io.FileUtils;

/**
 * @author sangupta
 *
 */
public abstract class AbstractHashCommand extends AbstractMultiFileCommand {
	
	/**
	 * Return the name of the algorithm implementation to be used
	 * for hashing.
	 * 
	 * @return
	 */
	protected abstract String getAlgorithmName();

	/**
	 * @see com.sangupta.andruil.commands.base.AbstractMultiFileCommand#processFile(java.io.File)
	 */
	@Override
	protected boolean processFile(File file) throws IOException {
		if(file.isDirectory()) {
			return true;
		}
		
		byte[] bytes = FileUtils.readFileToByteArray(file);
		try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance(getAlgorithmName());
	        byte[] array = md.digest(bytes);
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        
	        System.out.println(sb.toString() + " *" + file.getName());
	    } catch (java.security.NoSuchAlgorithmException e) {
	    	// do nothing
	    	System.out.println("No " + getAlgorithmName() + " implementation available");
	    	return false;
	    }
		
		return true;
	}
	
}
