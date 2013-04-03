package com.sangupta.andruil;

import com.sangupta.husk.core.HuskShellContext;
import com.sangupta.husk.core.PromptProvider;

public class AndruilPromptProvider implements PromptProvider {
	
	private HuskShellContext context;
	
	public AndruilPromptProvider(HuskShellContext context) {
		this.context = context;
	}

	@Override
	public String getPrompt() {
		return this.context.getCurrentDirectory().getAbsolutePath() + "> ";
	}

}
