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

package com.sangupta.andruil;

import com.sangupta.husk.core.HuskShellContext;
import com.sangupta.husk.core.PromptProvider;
import com.sangupta.jerry.util.AssertUtils;

/**
 * Andruil specific {@link PromptProvider} implementation.
 * 
 * @author sangupta
 * 
 */
public class AndruilPromptProvider implements PromptProvider {
	
	private HuskShellContext context;
	
	public AndruilPromptProvider(HuskShellContext context) {
		this.context = context;
	}

	@Override
	public String getPrompt() {
		String style = this.context.getConsole().getConsoleProperty("prompt.style");
		if(AssertUtils.isEmpty(style)) {
			return this.context.getCurrentDirectory().getAbsolutePath() + " $ ";
		}
		
		String prompt = style.replace("$A", "&");
		prompt = prompt.replace("$X", this.context.getCurrentDirectory().getName());
		prompt = prompt.replace("$P", this.context.getCurrentDirectory().getAbsolutePath());
		prompt = prompt.replace("$S", " ");
		prompt = prompt.replace("$U", System.getProperty("user.name"));
		prompt = prompt.replace("$G", ">");
		prompt = prompt.replace("$L", "<");
		prompt = prompt.replace("$C", "(");
		prompt = prompt.replace("$F", ")");
		
		// this should always be the last one
		prompt = prompt.replace("$$", "$");
		
		return prompt;
	}

}
