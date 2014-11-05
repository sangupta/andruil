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

package com.sangupta.andruil.commands.encode;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;
import com.sangupta.jerry.util.ConsoleUtils;

/**
 * Encode/decode any string as Base64
 * 
 * @author sangupta
 *
 */
public class Base64 extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "base64";
	}

	@Override
	public String getHelpLine() {
		return "Encode/decode a base64 string";
	}

	@Override
	public void execute(String[] args) {
		String mode = ConsoleUtils.readLine("Mode (encode/decode, e/d): ", false);
		String text = ConsoleUtils.readLine("Text: ", false);
		
		if("e".equalsIgnoreCase(mode) || "encode".equalsIgnoreCase(mode)) {
			System.out.println(new String(org.apache.commons.codec.binary.Base64.encodeBase64(text.getBytes())));
			return;
		}
		
		if("d".equalsIgnoreCase(mode) || "decode".equalsIgnoreCase(mode)) {
			System.out.println(new String(org.apache.commons.codec.binary.Base64.decodeBase64(text)));
			return;
		}
		
		System.out.println("Invalid mode selected.");
	}


}
