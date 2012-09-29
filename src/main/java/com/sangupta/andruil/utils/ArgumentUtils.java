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

package com.sangupta.andruil.utils;

/**
 * Utility function around validation, conversion and decomposition
 * of command arguments.
 * 
 * @author sangupta
 *
 */
public class ArgumentUtils {
	
	/**
	 * Check if the given argument represents a number or not.
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean isNumber(String arg) {
		if(arg == null) {
			return false;
		}
		
		char[] num = arg.toCharArray();
		int index = 0;
		char c;
		for( ; index < num.length; index++) {
			c = num[index];
			if(c < '0' || c > '9' || c != '-' || c != '+' || c != '.') {
				return false;
			}
		}
		
		return true;
	}

	/**
	 * Check if a given argument has wild-cards present in it or not.
	 * 
	 * @param arg
	 * @return
	 */
	public static boolean hasWildcards(String arg) {
		if(arg == null) {
			return false;
		}
		
		char[] name = arg.toCharArray();
		int index = 0;
		char c;
		for( ; index < name.length; index++) {
			c = name[index];
			if(c == '*' || c == '?') {
				return true;
			}
		}
		
		return false;
	}
}
