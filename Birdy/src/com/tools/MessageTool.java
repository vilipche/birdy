package com.tools;

public class MessageTool {

	public static boolean validMessage(String message) {
		if(message.length() <=140) {
			return true;
		}
		return false;
	}

	public static boolean addMessage(String myUser, String message) {
		boolean isOK;
//		isOK = databse function that adds the message
		
		if(!isOK) {
			return false;
		}
		return true;
	}

	public static boolean findMessage(String user, String message) {
		boolean isOK;
//		isOK = databse function that finds the message
		
		if(!isOK) {
			return false;
		}
		return true;
	}

	public static boolean removeMessage(String user, String message) {
		boolean isOK;
//		isOK = databse function that remvoes the message
		
		if(!isOK) {
			return false;
		}
		return true;
	}

	public static boolean getListMessages(String user) {
		boolean isOK;
//		isOK = databse function that gets the list of messages of a user
		
		if(!isOK) {
			return false;
		}
		return true;
	}
	}

}
