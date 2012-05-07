package com.sangupta.andruil.command;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class MD5Command extends AbstractCommand {

	@Override
	public String getCommandName() {
		return "md5";
	}

	@Override
	public String getHelpLine() {
		return "Computes MD5 of the given file.";
	}

	@Override
	protected void execute(String[] args) throws Exception {
		if(args.length == 0) {
			System.out.println("The syntax of the command is incorrect.");
			return;
		}
		
		File file = new File(args[0]);
		if(!file.exists()) {
			System.out.println("The system cannot find the file specified.");
			return;
		}
		
		if(file.isDirectory()) {
			System.out.println("File is a directory.");
			return;
		}
		
		byte[] bytes = FileUtils.readFileToByteArray(file);
		try {
	        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
	        byte[] array = md.digest(bytes);
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < array.length; ++i) {
	          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	       }
	        
	       System.out.println(sb.toString());
	    } catch (java.security.NoSuchAlgorithmException e) {
	    	// do nothing
	    }
	}

}
