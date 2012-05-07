package com.sangupta.andruil.command;

public class ChangeDirectoryCommand extends AbstractCommand {

	@Override
	public String getCommandName() {
		return "cd";
	}

	@Override
	public String getHelpLine() {
		return "Displays the name of or changes the current directory";
	}

	@Override
	protected void execute(String[] args) throws Exception {

	}

}
