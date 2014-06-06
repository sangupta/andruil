package com.sangupta.andruil.commands.andruil;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

public class MemoryUsage extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "mem";
	}

	@Override
	public String getHelpLine() {
		return "Display the memory usage information of this shell";
	}

	@Override
	public void execute(String[] arguments) {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Free Memory: " + runtime.freeMemory() + " bytes");
		System.out.println("Currently Used: " + runtime.totalMemory() + " bytes");
		System.out.println("Max Available: " + runtime.maxMemory() + " bytes");
	}

}
