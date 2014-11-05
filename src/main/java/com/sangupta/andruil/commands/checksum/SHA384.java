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

package com.sangupta.andruil.commands.checksum;

import com.sangupta.andruil.commands.base.AbstractHashCommand;

/**
 * @author sangupta
 *
 */
public class SHA384 extends AbstractHashCommand {

	/**
	 * @see com.sangupta.andruil.commands.AbstractAndruilCommand#getCommandName()
	 */
	@Override
	public String getName() {
		return "sha384";
	}

	/**
	 * @see com.sangupta.andruil.commands.AbstractAndruilCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Computes SHA384 hash of the given file";
	}


	/**
	 * @see com.sangupta.andruil.commands.base.AbstractHashCommand#getAlgorithmName()
	 */
	@Override
	protected String getAlgorithmName() {
		return "sha-384";
	}

}
