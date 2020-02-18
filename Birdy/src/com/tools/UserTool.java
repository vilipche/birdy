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
				+ "VALUES ('"+email+"', '"+username+"', '"+name+"', '"+surname+"','"+password+"');";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(insert);

		//if user is added rs will be 1
		if(rs==1) {
			return true;
		}
		
		return false;

	}

	public static boolean deleteUser(Connection connexion, String user) throws SQLException {
		
		int idUser = DBTool.getUserID(connexion, user);
		String delete = "DELETE FROM User WHERE idUser="+idUser+";";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(delete);

		if(rs!=0) {
			return true;
		}
		
		return false;
	}
	
	public static boolean checkPasswordExist(Connection connexion, String username, String password) throws SQLException {
		String query = "Select * From User u "
				+ "Where u.username='"+username+"' AND u.password='"+password+"';";

		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(query);

		while(rs.next()) {
			return true;
		}
		return false;
	}
	




}
