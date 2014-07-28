package org.apcs.zeriksen.binary;

import java.util.ArrayList;
import java.util.List;

public class Message {
	private String message;

	public Message(String message){
		this.message = message;
	}

	public Message(List<BinaryNum> binaryNums){
		String str = "";
		for(BinaryNum num : binaryNums){
			str += (char)num.toDecimal();
		}
		message = str;
	}

	public List<BinaryNum> getBinaryNums(){
		List<BinaryNum> bin = new ArrayList<BinaryNum>();
		for (int i = 0; i < message.length(); i++) {
			BinaryNum temp = new BinaryNum(message.charAt(i));
			bin.add(temp);
		}
		return bin;
	}

	public String getMessage(){
		return message;
	}
}
