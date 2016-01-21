/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012-2015, Sandeep Gupta
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

package com.sangupta.andruil.store;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

/**
 * Provides functionality to work with a data-store and helps
 * various commands to store command specific data.
 * 
 * @author sangupta
 *
 */
public class AndruilStore {
	
	/**
	 * Store the given key-value pair for the command in the data-store.
	 * 
	 * @param command
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean store(AbstractAndruilCommand command, String key, String value) {
		return false;
	}
	
	public static String value(AbstractAndruilCommand command, String key) {
		return null;
	}
}