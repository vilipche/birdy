package com.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTool {

	public static int getUserID(Connection connexion, String user) throws SQLException {
		// TODO     
		String query = "Select idUser From User Where username='"+user+"'";

		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(query);

		int id = -1; 
		while(rs.next()) {
			 id = rs.getInt("idUser");
		}
		return id;
	}
}
