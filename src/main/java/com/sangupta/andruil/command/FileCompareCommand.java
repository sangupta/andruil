package com.sangupta.andruil.command;

public class FileCompareCommand extends AbstractCommand {

	@Override
	public String getCommandName() {
		return "fc";
	}

	@Override
	public String getHelpLine() {
		return "Compares two files and displays the differences between them";
	}

	@Override
	protected void execute(String[] args) throws Exception {

	}

}
