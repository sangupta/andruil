package com.sangupta.andruil.commands.shell;

import java.io.File;
import java.io.IOException;

import com.sangupta.andruil.Andruil;
import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

/**
 * Create and fork a new shell so that we can multiple instances
 * of the shell with us.
 * 
 * @author sangupta
 *
 */
public class NewShell extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "new";
	}

	@Override
	public String getHelpLine() {
		return "Fork and create a new shell";
	}

	@Override
	public void execute(String[] arguments) {
		 try {
			exec(Andruil.class);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private int exec(@SuppressWarnings("rawtypes") Class klass) throws IOException, InterruptedException {
		String javaHome = System.getProperty("java.home");
		String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
		String classpath = System.getProperty("java.class.path");
		String className = klass.getCanonicalName();

		ProcessBuilder builder = new ProcessBuilder(javaBin, "-cp", classpath, className);

		Process process = builder.start();
		return 0;
	}

}
