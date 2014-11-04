package com.sangupta.andruil.support;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;
import com.sangupta.andruil.launcher.JavaLauncher;

/**
 * A command wrapper that encapsulates an existing Java command line
 * application to be used inside Andruil.
 * 
 * @author sangupta
 *
 */
public class JavaLauncherCommand extends AbstractAndruilCommand {
	
	private JavaPackage pack;
	
	public JavaLauncherCommand(JavaPackage pack) {
		this.pack = pack;
	}

	@Override
	public String getName() {
		return this.pack.name;
	}

	@Override
	public String getHelpLine() {
		return this.pack.description;
	}

	@Override
	public void execute(String[] arguments) {
		@SuppressWarnings("rawtypes")
		Class clazz = null;
		
		try {
			clazz = Class.forName(this.pack.main);
		} catch (ClassNotFoundException e) {
			// TODO: log this
			e.printStackTrace();
			return;
		}
		
		JavaLauncher.runClass(clazz, arguments);
	}
	
}
