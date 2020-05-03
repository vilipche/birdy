package com.test;

import com.tools.*;

import java.util.Random;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bd.DBStatic;
import com.bd.Database;
import com.services.friends.FriendServices;
import com.services.session.SessionServices;
import com.services.user.UserServices;

public class ServicesTestBD {

	public static void main(String[] args) {

		try {
			
			Connection connexion = Database.getMySQLConnection();
			//UserServices -> Works
//			System.out.println(UserServices.newUser("a", "a", "Aaaaaaaa123", "Aa", "Aaa"));
//			System.out.println(UserServices.removeUser("a", "a", "Aaaaaaaa123"));
			
			//SessionServices -> Works
//			System.out.println(SessionServices.login("a", "Aaaaaaaa123"));
//			System.out.println(SessionServices.logout("a"));
			
			//FriendServices -> Works
//			System.out.println(FriendServices.follow("a", "toto"));
//			System.out.println(FriendServices.unFollow("a", "toto"));
//			System.out.println(FriendServices.listFriends("a"));
			
			//On delete cascade -> Works
//			System.out.println(UserServices.removeUser("a", "a", "Aaaaaaaa123"));
//			toto@gmail.com
			
//			System.out.println(SessionServices.login("toto", "123ABCabc"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}



	}

	//some first local tests (not very useful now)
	
	public static void insertAuthentification(int user) {
		try {
			Random rand = new Random();
			Connection connexion = Database.getMySQLConnection();
			Statement statement = connexion.createStatement();
			System.out.println("Creating login");
			String query = "Select * From Connexion;";
			int randomNum = rand.nextInt((500 - 1) + 1) + 1;

			statement.executeUpdate("INSERT INTO Connexion(idUser, authentificationKey) VALUES ('"+user+"', '"+randomNum+"')");

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

	public static void insertMessages(int user, String string2) {
		try {
			Connection connexion = Database.getMySQLConnection();
			Statement statement = connexion.createStatement();
			System.out.println("Creating message");
			String query = "Select * From Messages;";

			statement.executeUpdate("INSERT INTO Messages(idUser, content) VALUES ('"+user+"', '"+string2+"')");

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

	public static void insertFriend(int friend1, int friend2) {
		try {

			Connection connexion = Database.getMySQLConnection();
			Statement statement = connexion.createStatement();
			System.out.println("Creating friends");
			String query = "Select * From Friends;";

			statement.executeUpdate("INSERT INTO Friends(idUser1, idUser2) VALUES ('"+friend1+"', '"+friend2+"')");

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

	public static void insertUser(String value) {
		try {
			Connection connexion = Database.getMySQLConnection();
			Statement statement = connexion.createStatement();

			System.out.println("Creating a user");
			String query = "Select * From User;";

			statement.executeUpdate("INSERT INTO User(email, username, name, surname, password) VALUES ('"+value+"', '"+value+"', '"+value+"', '"+value+"','"+value+"')");

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
//			DBStatic.pooling = true;
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
