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
import java.util.zip.CRC32;

import org.apache.commons.io.FileUtils;

import com.sangupta.andruil.commands.base.AbstractMultiFileCommand;

/**
 * @author sangupta
 *
 */
public class Crc32 extends AbstractMultiFileCommand {

	/**
	 * @see com.sangupta.andruil.commands.AbstractCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return "crc32";
	}

	/**
	 * @see com.sangupta.andruil.commands.AbstractCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Computes CRC32 hash of the given file";
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
		CRC32 crc32 = new CRC32();
		crc32.update(bytes);
		this.out.println(Long.toHexString(crc32.getValue()) + " *" + file.getName());
		
		return true;
	}

}
