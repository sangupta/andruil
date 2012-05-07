package com.sangupta.andruil.command;

public class TailCommand extends AbstractCommand {

	@Override
	public String getCommandName() {
		return "tail";
	}

	@Override
	public String getHelpLine() {
		return null;
	}

	@Override
	protected void execute(String[] args) throws Exception {

	}

}
