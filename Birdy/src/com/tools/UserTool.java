package com.tools;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTool {

	public static boolean usernameExist(Connection connexion, String username) throws SQLException {
		String query = "Select * From User u Where u.username='"+username+"'";

		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(query);

		while(rs.next()) {
			return true;
		}
		return false;
	}

	public static boolean emailExist(Connection connexion, String email) throws SQLException {
		String query = "Select * From User u Where u.email='"+email+"'";

		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(query);

		while(rs.next()) {
			return true;
		}
		return false;
	}



	public static boolean userExist(Connection connexion, String login) throws SQLException {
		boolean email = emailExist(connexion, login);
		boolean user = usernameExist(connexion, login);
		
		return email || user;
	}

	public static boolean checkPass(Connection connexion, String login, String pass) throws SQLException {
		String query = "Select * From User u Where u.email='"+login+"' AND u.password='"+pass+"' ";
		//TODO ne znam preku koj login
		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(query);

		while(rs.next()) {
			return true;
		}
		return false;
	}



	public static boolean checkPasswordValid(String password) {

		if (password.length()<8 && password.length()>24) {
			return false;
		} 
		int lowercase = 0;
		int uppercase = 0;
		int digit = 0;

		for(int i=0; i < password.length() ; i++) {
			char c = password.charAt(i);

			if(Character.isUpperCase(c)) uppercase++;
			if(Character.isLowerCase(c)) lowercase++;
			if(Character.isDigit(c)) digit++;
			if(Character.isWhitespace(c)) return false;
		}

		return lowercase>0 && uppercase>0 && digit>0;
	}


	/**
	 * Insert user in the database
	 * @param connexion
	 * @param username
	 * @param email
	 * @param password
	 * @param name
	 * @param surname
	 * @return true if the user was successfully added in the databse
	 * @throws SQLException
	 */
	public static boolean insertUser(Connection connexion, String username, String email, 
			String password, String name, String surname) throws SQLException {
		String insert = "INSERT INTO User(email, username, name, surname, password) "
				+ "VALUES ('"+email+"', '"+username+"', '"+name+"', '"+surname+"','"+password+"')";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(insert);

		//if user is added rs will be 1
		if(rs==1) {
			return true;
		}
		
		return false;

	}
	
	public static boolean insertSession(String login) {
				
		return false;
		
	}

	public static boolean logoutUser(Connection connexion, String login) {
		return false;
		// TODO Log out the user from the site

	}

	public static void logoutTime(String login) {
		// TODO add the time when the user logged out		
	}




}
