package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bd.DBStatic;
import com.bd.Database;

public class bdTest {

	public static void main(String[] args) {

		
		try {

			Connection c = Database.getMySQLConnection();
			Statement statement = c.createStatement();
			System.out.println("Statement creation");
			String query = "Select * From User;";
			
			statement.executeUpdate("INSERT INTO User(email, username, name, surname, password) VALUES ('asdasd', 'asdasd', 'asdasda', 'asdasd','dasdw2')");
			
			ResultSet curseur = statement.executeQuery(query);	
			
			while(curseur.next()) {
				System.out.println(curseur);
			}
			System.out.println("cbon");
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public static void insertLine() {
		
		try {
			DBStatic.pooling = true;
			Connection c = Database.getMySQLConnection();
			Statement statement = c.createStatement();
			System.out.println("Statement creation");
			String query = "Select * From User;";
			
			statement.executeUpdate("INSERT INTO User(email, username, name, surname, password) "
					+ "VALUES ('test', 'true', '131231', 'asdasd','dasdw2')");
			
			ResultSet curseur = statement.executeQuery(query);	
			
			while(curseur.next()) {
				System.out.println(curseur);
			}
			System.out.println("cbon");
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
