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

package com.sangupta.andruil.utils;

/**
 * A simple timekeeper that tracks the times of shell run, and
 * individual tasks that are run inside the shell.
 * 
 * @author sangupta
 *
 */
public class TimeKeeper {
	
	/**
	 * Unique name of this instance
	 */
	private String name = null;
	
	/**
	 * Whether the instance has been closed or not
	 */
	private volatile boolean closed = false;
	
	/**
	 * The instantiation time of this instance
	 */
	private long instantiationTime = System.currentTimeMillis();
	
	/**
	 * The closing time of this instance
	 */
	private long closingTime = -1;

	/**
	 * Default constructor
	 * 
	 * @param name
	 */
	public TimeKeeper(String name) {
		this.name = name;
	}
	
	/**
	 * Close this instance of timekeeper. If the timekeeper has been closed
	 * no more operations can be performed on the instance, except read requests.
	 */
	public void close() {
		this.closed = true;
		this.closingTime = System.currentTimeMillis();
	}
	
	/**
	 * Return the running time of this instance. If the instance has been closed, this
	 * returns the total time, the instance ran for.
	 * 
	 * @return
	 */
	public long getRunningTime() {
		if(!closed) {
			return System.currentTimeMillis() - this.instantiationTime;
		}
		
		return this.closingTime - this.instantiationTime;
	}
	
	/**
	 * Return the uptime of this time-keeper.
	 * 
	 * @return
	 */
	public String uptime() {
		return (this.getRunningTime() / 1000) + " seconds.";
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.name + " ran for " + uptime();
	}
}
