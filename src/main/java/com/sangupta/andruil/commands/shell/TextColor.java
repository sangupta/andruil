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

package com.sangupta.andruil.commands.shell;

import java.awt.Color;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;
import com.sangupta.consoles.swing.SwingColors;

/**
 * 
 * @author sangupta
 *
 */
public class TextColor extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "color";
	}

	@Override
	public String getHelpLine() {
		return "Change the current text color";
	}

	@Override
	public void execute(String[] arguments) {
		Color fore = this.shellContext.getConsole().getForegroundColor();
		Color back = this.shellContext.getConsole().getBackgroundColor();
		
		if(arguments.length == 0) {
			System.out.println("Foreground color: " + fore);
			System.out.println("Background color: " + back);
			return;
		}
		
		String str = arguments[0];
		
		if(str.length() >= 1) {
			fore = getColor(str.charAt(0));
		}
		if(str.length() > 1) {
			back = getColor(str.charAt(1));
		}
		
		if(fore.equals(back)) {
			System.out.println("Foreground and background color cannot be the same!");
			return;
		}
		
		this.shellContext.getConsole().setColor(fore, back);
	}

	private Color getColor(char c) {
		switch(c) {
			case '0': return SwingColors.BLACK;
			case '1': return SwingColors.BLUE;
			case '2': return SwingColors.GREEN;
			case '3': return SwingColors.AQUA;
			case '4': return SwingColors.RED;
			case '5': return SwingColors.PURPLE;
			case '6': return SwingColors.YELLOW;
			case '7': return SwingColors.WHITE;
			case '8': return SwingColors.GRAY;
			case '9': return SwingColors.LIGHT_BLUE;
			case 'A': case 'a': return SwingColors.LIGHT_GREEN;
			case 'B': case 'b': return SwingColors.LIGHT_AQUA;
			case 'C': case 'c': return SwingColors.LIGHT_RED;
			case 'D': case 'd': return SwingColors.LIGHT_PURPLE;
			case 'E': case 'e': return SwingColors.LIGHT_YELLOW;
			case 'F': case 'f': return SwingColors.BRIGHT_WHITE;
		}
		
		return null;
	}

}
