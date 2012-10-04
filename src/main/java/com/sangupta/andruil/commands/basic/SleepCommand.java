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

package com.sangupta.andruil.commands.basic;

import com.sangupta.andruil.commands.AbstractCommand;
import com.sangupta.andruil.constants.UnixErrorMessages;

/**
 * Pause the shell for a given time
 * 
 * @author sangupta
 *
 */
public class SleepCommand extends AbstractCommand {

	/**
	 * @see com.sangupta.andruil.commands.AbstractCommand#getCommandName()
	 */
	@Override
	public String getCommandName() {
		return "sleep";
	}

	/**
	 * @see com.sangupta.andruil.commands.AbstractCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Pause for NUMBER seconds.";
	}

	/**
	 * @see com.sangupta.andruil.commands.AbstractCommand#execute(java.lang.String[])
	 */
	@Override
	protected void execute(String[] args) throws Exception {
		if(args.length != 1) {
			this.out.println(this.getCommandName() + ": " + UnixErrorMessages.TOO_FEW_ARGUMENTS);
			return;
		}
		
		Long time = parseTimeInterval(args[0]);
		if(time == null) {
			this.out.println(this.getCommandName() + ": " + "invalid time interval '" + args[0] + "'");
			return;
		}
		
		Thread.sleep(time * 1000);
	}

	/**
	 * Parses time interval from the given string argument
	 * and converts it into number of seconds.
	 * 
	 * @param string
	 * @return
	 */
	private Long parseTimeInterval(String arg) {
		char c = arg.charAt(arg.length() - 1);
		c = Character.toLowerCase(c);
		if(c == 's' || c == 'm' || c == 'h' || c == 'd') {
			// parse the number
			arg = arg.substring(0, arg.length() - 1);
		}

		// try and parse it as a number
		long time;
		try {
			time = Long.parseLong(arg);
		} catch(NumberFormatException e) {
			return null;
		}
		
		switch(c) {
			case 's':
				return time;
				
			case 'm':
				return time * 60;
				
			case 'h':
				return time * 3600;
				
			case 'd':
				return time * 86400;
		}
		
		return time;
	}

}