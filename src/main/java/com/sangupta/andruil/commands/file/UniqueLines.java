package com.sangupta.andruil.commands.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.sangupta.andruil.commands.base.AbstractCommand;
import com.sangupta.jerry.util.CryptoUtil;

public class UniqueLines extends AbstractCommand {

	@Override
	public String getName() {
		return "uniq";
	}

	@Override
	public String getHelpLine() {
		return "Find number of unique lines in a text file";
	}

	@Override
	public void execute(String[] arguments) {
		if(arguments.length == 0) {
			System.out.println("No file specified.");
			return;
		}
		
		File file = new File(arguments[0]);
		if(!file.exists()) {
			System.out.println("File does not exists.");
			return;
		}
		
		if(file.isDirectory()) {
			System.out.println("File is a directory.");
			return;
		}
		
		try {
			process(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		Set<String> hashes = new HashSet<String>();
		String line;
		
		int lineNumber = 0;
		int duplicates = 0;
		while((line = reader.readLine()) != null) {
			lineNumber++;
			String md5 = CryptoUtil.getMD5Hex(line);
			if(hashes.contains(md5)) {
				// System.out.println("Duplicate line found at line: " + lineNumber);
				duplicates++;
			} else {
				hashes.add(md5);
			}
		}
	
		System.out.println("Total lines in file: " + lineNumber);
		System.out.println("Total unique lines: " + (lineNumber - duplicates));
		System.out.println("Total duplicate lines: " + duplicates);
		
		reader.close();
	}

}
