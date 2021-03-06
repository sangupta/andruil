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

package com.sangupta.andruil.commands.basic;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

/**
 * @author sangupta
 *
 */
public class DateCommand extends AbstractAndruilCommand {

	/**
	 * The date format to be used
	 */
	private final String DATE_FORMAT = "dd-MM-yyyy";

	/**
	 * @see com.sangupta.andruil.AbstractAndruilCommand.AbstractCommand#getCommandName()
	 */
	@Override
	public String getName() {
		return "date";
	}

	/**
	 * @see com.sangupta.andruil.AbstractAndruilCommand.AbstractCommand#getHelpLine()
	 */
	@Override
	public String getHelpLine() {
		return "Display or sets the current system date";
	}

	@Override
	public void execute(String[] args) {
		if(args.length == 0) {
			display(true);
			ask();
		} else {
			if(args.length > 1) {
				System.out.println("The system cannot accept the date entered.");
				ask();
			} else {
				if("/t".equalsIgnoreCase(args[0])) {
					display(false);
					return;
				}
			}
		}
	}

	/**
	 * Display the current date with the prompt if needed
	 */
	private void display(boolean showPrompt) {
		if(showPrompt) {
			System.out.println("The current date is: ");
		}
		
		Date current = new Date();
		String formattedDate = new SimpleDateFormat(DATE_FORMAT).format(current);
		System.out.println(formattedDate);
	}
	
	/**
	 * Ask the user for the current date
	 */
	private void ask() {
		System.out.println("Andruil: Not yet implemented");
		
		
//		final String date = this.readLine("Enter the new date: (" + DATE_FORMAT + ") ");
//		if(AssertUtils.isEmpty(date)) {
//			return;
//		}
//		
//		try {
//			Date provided = new SimpleDateFormat(DATE_FORMAT).parse(date);
//			switch(Environment.operatingSystem) {
//				case Windows:
//					OSUtils.executeNativeCommand("date " + date);
//					return;
//					
//				default:
//					this.out.println("Andruil: Not yet implemented");
//			}
//		} catch(ParseException e) {
//			this.out.println("The system cannot accept the date entered.");
//			ask();
//		}
	}

}