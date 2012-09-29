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

package com.sangupta.andruil.commands.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.sangupta.andruil.commands.AbstractCommand;

/**
 * @author sangupta
 * 
 */
public class HostnameCommand extends AbstractCommand {

	/**
	 * (non-Javadoc)
	 * 
	 * @see com.sangupta.andruil.command.AbstractCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return "hostname";
	}

	/**
	 * @see com.sangupta.andruil.command.AbstractCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Prints the name of the current host.";
	}

	/**
	 * @see com.sangupta.andruil.command.AbstractCommand#execute(java.lang.String[])
	 */
	@Override
	protected void execute(String[] args) throws Exception {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String hostname = addr.getHostName();
			println(hostname);
		} catch (UnknownHostException e) {
			println("Unable to detect hostname.");
		}
	}

}
