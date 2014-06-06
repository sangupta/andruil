package com.sangupta.andruil.commands.file;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;

public class Combine extends AbstractAndruilCommand {

	@Override
	public void execute(String[] args) {
		if(args.length < 3) {
			System.out.println("Usage: combine <out-file> <file1> <file2> ...");
			return;
		}
		
		File outFile = resolveFile(args[0]);
		if(outFile.exists() && outFile.isFile()) {
			System.out.println("Output file exists... exiting!");
			return;
		}
		
		try {
			for(int index = 1; index < args.length; index++) {
				File inFile = resolveFile(args[index]);
				if(!(inFile.exists() && inFile.isFile())) {
					continue;
				}
				
				byte[] bytes = FileUtils.readFileToByteArray(inFile);
				FileUtils.writeByteArrayToFile(outFile, bytes, true);
			}
		} catch(IOException e) {
			System.out.println("Unable to copy files: " + e.getMessage());
		}
	}

	@Override
	public String getHelpLine() {
		return "Combine multiple files into one";
	}

	@Override
	public String getName() {
		return "combine";
	}

}
