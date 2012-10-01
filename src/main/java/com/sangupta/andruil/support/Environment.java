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

package com.sangupta.andruil.support;

import com.sangupta.andruil.utils.OSUtils;
import com.sangupta.andruil.utils.TimeKeeper;

/**
 * Holds details of the environment that we are running in.
 * 
 * @author sangupta
 *
 */
public class Environment {
	
	/**
	 * The operating system
	 */
	public static final OperatingSystem operatingSystem;

	static {
		if(OSUtils.isWindows()) {
			operatingSystem = OperatingSystem.Windows;
		} else {
			operatingSystem = OperatingSystem.Unknown;
		}
	}
	
	/** 
	 * The timekeeper instance for this instance of the shell
	 */
	public static final TimeKeeper timeKeeper = new TimeKeeper("Andruil");

	/**
	 * Initialize the environment
	 */
	public static void initialize() {
		
	}
	
}
