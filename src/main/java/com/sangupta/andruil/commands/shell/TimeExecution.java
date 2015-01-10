/**
 *
 * andruil - Java command line shell
 * Copyright (c) 2012-15, Sandeep Gupta
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

package com.sangupta.andruil.commands.shell;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;
import com.sangupta.husk.HuskShellCommand;

/**
 * Time the execution of another command.
 * 
 * @author sangupta
 *
 */
public class TimeExecution extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "time";
	}

	@Override
	public String getHelpLine() {
		return "Time the execution of another command";
	}

	@Override
	public void execute(String[] arguments) {
		if(arguments.length == 0) {
			System.out.println("Nothing to time!");
			return;
		}
		
		String commandName = arguments[0];
		final String[] newArgs;
		if(arguments.length > 1) {
			newArgs = Arrays.copyOfRange(arguments, 1, arguments.length);
		} else {
			newArgs = new String[] { };
		}
		
		final HuskShellCommand command = this.shellContext.obtainCommand(commandName);
		if(command == null) {
			// TODO: we might need to delegate this to OS
			System.out.println("No such command found");
			return;
		}
		
		final ThreadMXBean bean = ManagementFactory.getThreadMXBean();
		final long[] times = new long[3];
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				final long start = System.currentTimeMillis();
				command.execute(newArgs);
				final long end = System.currentTimeMillis();
				
				long sys = bean.getCurrentThreadCpuTime();
				sys = sys / (1000l * 1000l);

				long user = bean.getCurrentThreadUserTime();
				user = user / (1000l * 1000l);
				
				times[0] = end - start;
				times[1] = user;
				times[2] = sys;
			}
			
		});
		
		thread.start();
		
		try {
			thread.join();
		} catch(InterruptedException e) {
			// eat up
		}
			
		System.out.println();
		System.out.println("real: " + times[0] + " millis");
		System.out.println("user: " + times[1] + " millis");
		System.out.println("sys : " + times[2]  + " millis");
	}
	
}