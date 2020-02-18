package com.test;

import com.tools.*;

import java.util.Random;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bd.DBStatic;
import com.bd.Database;

public class ServicesTestBD {

	public static void main(String[] args) {


		//		insertUser("test1");
		//		insertUser("test2");
		//		insertFriend(5, 7);
		//		insertMessages(5, "my message");
		//		insertAuthentification(5);



		try {
			
			boolean a;
			Connection connexion = Database.getMySQLConnection();
//			UserTool
//			a = UserTool.insertUser(connexion, "vilipche", "filipsotiroski@gmail.com", "123ABCabc", "Filip", "Sotiroski");
//			System.out.println(a);
//
//			a = UserTool.usernameExist(connexion, "vilipche");
//			System.out.println(a);

//			a = UserTool.emailExist(connexion, "filipsotiroski@gmail.com");
//			System.out.println(a);
//			
//			a = UserTool.emailExist(connexion, "filipsotiroski@hotmail.com");
//			System.out.println(a);

//			a = UserTool.userExist(connexion, "vilipche");
//			System.out.println(a);
//			a = UserTool.userExist(connexion, "filipsotiroski@hotmail.com");
//			System.out.println(a);
			
//			System.out.println(UserTool.checkPasswordValid("asd"));
//			System.out.println(UserTool.checkPasswordValid("@FFFFFFFFF"));
//			System.out.println(UserTool.checkPasswordValid("@123FSAasdasd"));
			
			
//			a = UserTool.checkPass(connexion, "filipsotiroski@gmail.com", "123ABCabc");
//			System.out.println(a);
			
//			FriendTool
//			a = UserTool.insertUser(connexion, "toto", "toto@gmail.com", "123ABCabc", "Toto", "Toti");
//			System.out.println(a);
			
//			System.out.println(FriendTool.getUserID(connexion, "totao"));
			
//			System.out.println(FriendTool.friendshipExist(connexion, "toto", "vilipche"));
//			System.out.println(FriendTool.friendshipExist(connexion, "vilipche", "toto"));
//			
//			System.out.println(FriendTool.addFriend(connexion, "toto", "vilipche"));
//			
//			System.out.println(FriendTool.friendshipExist(connexion, "toto", "vilipche"));
//			System.out.println(FriendTool.friendshipExist(connexion, "vilipche", "toto"));
//
//			
			//TODO problem imam so remove
//			System.out.println(FriendTool.removeFriend(connexion, "vilipche", "toto"));
//			System.out.println(FriendTool.removeFriend(connexion, "toto", "vilipche"));
//			
//			System.out.println(FriendTool.friendshipExist(connexion, "toto", "vilipche"));
//			System.out.println(FriendTool.friendshipExist(connexion, "vilipche", "toto"));

//			System.out.println(FriendTool.getUserID(connexion, "toto"));
//			System.out.println(FriendTool.followUser(connexion, "toto", "vilipche"));
//			System.out.println(FriendTool.followExist(connexion, "toto", "vilipche"));
			
//			System.out.println(FriendTool.unfollow(connexion, "toto", "vilipche"));
			
			
//			System.out.println(FriendTool.followExist(connexion, "toto", "vilipche"));
			
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}



	}

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
