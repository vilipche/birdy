package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bd.Database;

public class bdTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection c;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = Database.getMySQLConnection();
			Statement statement = c.createStatement();
			
			String query = "Select * From Test;";
			
			statement.executeUpdate("INSERT INTO Test VALUES (5);");
			statement.executeUpdate("INSERT INTO Test VALUES (2);");
			
			ResultSet curseur = statement.executeQuery(query);	
			
			while(curseur.next()) {
				System.out.println(curseur);
			}
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
