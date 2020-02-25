package com.tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SessionTool {

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
	
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

	public static boolean logoutUser(Connection connexion, String login) throws SQLException {
		int userID = UserTool.getUserID(connexion, login);
		
		String delete = "DELETE FROM Session WHERE  idUser="+userID+";";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(delete);

		if(rs!=0) {
			return true;
		}
		
		return false;
	}
	//TODO
	public static boolean logoutUserKey(Connection connexion, String key) {
		return false;
	}


	
}
