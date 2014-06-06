package com.sangupta.andruil.commands.encode;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;
import com.sangupta.jerry.util.ConsoleUtils;

public class Base64 extends AbstractAndruilCommand {

	@Override
	public String getName() {
		return "base64";
	}

	@Override
	public String getHelpLine() {
		return "Encode/decode a base64 string";
	}

	@Override
	public void execute(String[] args) {
		String mode = ConsoleUtils.readLine("Mode (encode/decode, e/d): ", false);
		String text = ConsoleUtils.readLine("Text: ", false);
		
		if("e".equalsIgnoreCase(mode) || "encode".equalsIgnoreCase(mode)) {
			System.out.println(new String(org.apache.commons.codec.binary.Base64.encodeBase64(text.getBytes())));
			return;
		}
		
		if("d".equalsIgnoreCase(mode) || "decode".equalsIgnoreCase(mode)) {
			System.out.println(new String(org.apache.commons.codec.binary.Base64.decodeBase64(text)));
			return;
		}
		
		System.out.println("Invalid mode selected.");
	}


}
