package com.tools;

public class UserTool {

	public static boolean userExist(String login) {
		String query = "Select * From User u Where login=u.login";
		if( db(query) ) {
			return true;
		}
		return false;
	}

	public static boolean checkPass(String login, String pass) {
		String query = "Select * From User u Where login = u.login AND pass = u.pass";
		
		if(db(query)) {
			return true;
		}
		
		return false;
	}
	
	public static boolean loginTime(String login) {
		return false;
		//add the time when the log in happened;
	}

	public static boolean checkPasswordValid(String password) {
		
		if (password.length()>=8 && password.length()<=24) {
			return true;
		} 
		
		return false;
	}

	public static String insertUser(String login, String pass) {
		// TODO Add create the user in the database and return the key
		return null;
	}
	
	public static boolean logoutUser(String login) {
		return false;
		// TODO Log out the user from the site
		
	}

	public static void logoutTime(String login) {
		// TODO add the time when the user logged out		
	}

	

}
