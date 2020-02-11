package com.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bd.Database;

public class bdTest {

	public static void main(String[] args) {
		System.out.println("asdadsdasdadsaa");
		
		try {
			System.out.println("asdasd");
			Connection c = Database.getMySQLConnection();
			Statement statement = c.createStatement();
			System.out.println("Statement creation");
			String query = "Select * From Test;";
			
			statement.executeUpdate("INSERT INTO Test VALUES (5);");
			statement.executeUpdate("INSERT INTO Test VALUES (2);");
			
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
