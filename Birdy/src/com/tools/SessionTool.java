package com.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionTool {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private static final int SESSION_LIMIT = 300;
	
	/**
	 * Function for creating na alphanumeric key 
	 */
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	/**
	 * Tool for creating a session in the database
	 * A random key is generated 
	 * Function called in the login service
	 */
	public static boolean insertSession(Connection connexion, String login) throws SQLException {

		int userID = UserTool.getUserID(connexion, login);

		String key = randomAlphaNumeric(32);

		String insert = "INSERT INTO Session(idUser, sessionKey) "
				+ "VALUES("+userID+",'"+key+"');";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(insert);

		//if user is added rs will be 1
		if(rs==1) {
			return true;
		}

		return false;

	}


	/**
	 * Tool for removing a user from the session by using the email or username
	 */
	public static boolean logoutUser(Connection connexion, String login) throws SQLException {
		//function for removing the user's session
		int userID = UserTool.getUserID(connexion, login);

		String delete = "DELETE FROM Session WHERE  idUser="+userID+";";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(delete);

		if(rs!=0) {
			return true;
		}

		return false;
	}
	
	/**
	 * This function will be used in each service so that we know if the session has expired
	 * check the session
	 * if user is inactive more than 1 minute, log out
	 * if the user is active, change the session time.
	 * if session doesn't exist return false
	 */
	public static boolean checkSession(Connection connexion, String login) throws SQLException {

		String sessionKey= null;
		try {
			sessionKey = getKey(connexion, login); //We get the key of the session
		} catch(SQLException e) {
			System.out.println("GETKEY ERROR");
		}
		
		if(sessionKey == null) {
			System.out.println("Check getKey function");
			return false;
		} 

		// we get the login time of the session
		String sessionQuery = "Select dateLogin FROM Session WHERE sessionKey = '"+sessionKey+"';";
		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(sessionQuery);
		
		String sessionTime = null;
		while (rs.next()) {
			sessionTime = rs.getString("dateLogin");
		}
		
		if(sessionTime == null) {
			return false;
		}
		
		//We get the time difference between the session and current time
		String queryDifference = "SELECT TIMESTAMPDIFF(SECOND, '"+sessionTime+"' , CURRENT_TIMESTAMP) AS difference";	
		lecture = connexion.createStatement();
		rs = lecture.executeQuery(queryDifference);
		
		int difference = 0;
		while (rs.next()) {
			difference = rs.getInt("difference");
		}
		
		//login or logut depending on the difference
		if(difference > SESSION_LIMIT) {
			System.out.println("BAD");
			if(logoutUser(connexion, login)) {
				System.out.println("User log out");
			} else {
				System.out.println("Problem log out");
			}
			
			return false;

		} else {
			System.out.println("OK");
			if(updateSession(connexion, sessionKey)) {
				System.out.println("Time updated");
				return true;
			} else {
				System.out.println("Time problem");
				return false;
			}
		}
	}

	/**
	 * Update the current session using the sessionKey to current time
	 */
	private static boolean updateSession(Connection connexion, String sessionKey) throws SQLException {
		
		String delete = "UPDATE Session SET dateLogin = CURRENT_TIMESTAMP WHERE sessionKey = '"+sessionKey+"';";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(delete);
		if(rs!=0) {
			return true;
		}
		
		return false;
	}

	
	/**
	 * Tool that returns the key of the session depending on the login
	 * returns null if there are more than one session per user or no keys
	 */
	private static String getKey(Connection connexion, String login) throws SQLException {
		//Returns null if doesn't find key or there is a duplicate
		//Else it returns the key
		
		int userID = UserTool.getUserID(connexion, login);
		
		String keyQuery = "Select sessionKey FROM Session WHERE idUser="+userID+";";
		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(keyQuery);
		
		int rows=0; //used to check the number of rows
		String sessionKey = null;
		
		while (rs.next()) {
            sessionKey = rs.getString("sessionKey");
            rows++;
		}
		
		if(rows>1) {
			System.out.println("more than one key");
			return null;
		} else if(rows==0) {
			System.out.println("no key");
			return null;
		} else {
			System.out.println("key exists");
		}
		
		return sessionKey;
		
	}



}
