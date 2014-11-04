package com.sangupta.andruil.commands.basic;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

public class Epoch extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "epoch";
	}

	@Override
	public String getHelpLine() {
		return "Show current epoch time";
	}

	@Override
	public void execute(String[] arguments) {
		System.out.println(System.currentTimeMillis());
	}

}
