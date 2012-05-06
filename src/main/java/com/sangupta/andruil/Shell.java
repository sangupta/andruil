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

package com.sangupta.andruil;

import java.io.IOException;
import java.io.PrintWriter;

import jline.console.ConsoleReader;
import jline.console.completer.Completer;

public class Shell {
	
    private static final ConsoleReader reader;
    
    private static Character mask = null;
    
    private static boolean passwordMode = false;
    
    private static final PrintWriter out;
    
    private static boolean exitShellNow = false;

    static {
		ConsoleReader local = null;
    	try {
			local = new ConsoleReader();
    	} catch(IOException e) {
    		// do nothing
    	}
    	
		reader = local;
		out = new PrintWriter(reader.getOutput());
    }
    
    public static void setPrompt(String prompt) {
		reader.setPrompt(prompt);
    }
    
    public static void addCompleter(Completer completer) {
		reader.addCompleter(completer);
    }
    
    public static void setMast(Character mask) {
    	Shell.mask = mask;
    }
    
    public static void setPasswordMode(boolean passwordMode) {
    	Shell.passwordMode = passwordMode;
    }
    
    public static void clearScreen() {
    	try {
			reader.clearScreen();
		} catch (IOException e) {
			// eat up
			out.println("Command not supported.");
			out.flush();
		}
    }
    
	public static void run() throws Exception {
        String line;
        while ((line = reader.readLine()) != null) {
        	// trim any extra white space
        	line = line.trim();
        	
        	int index = line.indexOf(' ');
        	String command = line;
        	if(index > 0) {
        		command = line.substring(0, index);
        		line = line.substring(index + 1);
        	} else {
        		line = null;
        	}
        	
        	boolean executable = CommandExecutor.isExecutableCommand(command);
        	if(executable) {
        		try {
        			CommandExecutor.executeCommand(command, line);
        		} catch(Throwable t) {
        			t.printStackTrace();
        		}
        	} else {
        		out.print("'");
        		out.print(command);
        		out.print("' is not recognized as an internal command.");
        		out.flush();
        	}
        	
        	out.println("");
        	out.println("");
        	out.flush();
        	
//            // If we input the special word then we will mask
//            // the next line.
//            if (passwordMode) {
//                line = reader.readLine("password> ", mask);
//            }
//            
//            if (line.equalsIgnoreCase("quit") || line.equalsIgnoreCase("exit")) {
//                break;
//            }
        	
        	if(exitShellNow) {
        		break;
        	}
        }
	}

	public static void exitShell() {
		exitShellNow = true;
	}

}
