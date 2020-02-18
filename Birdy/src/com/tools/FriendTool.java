package com.tools;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendTool {

	public static String getUserID(Connection connexion, String user) throws SQLException {
		// TODO     
		String query = "Select idUser From User Where username='"+user+"'";

		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(query);

		String id = null; //TODO dali e dobro vaka
		while(rs.next()) {
			 id = rs.getString("idUser");
		}
		return id;
	}
	
	public static boolean friendshipExist(Connection connexion, String myUser, String userFriend) throws SQLException {
		// TODO checks if both arguments are friends in the database;
		String idUser1 = getUserID(connexion, myUser);
		String idUser2 = getUserID(connexion, userFriend);
		String query = "Select * From Friends Where idUser1='"+idUser1+"' AND idUser2='"+idUser2+"'";

		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(query);

		while(rs.next()) {
			return true;
		}
		return false;
	}

	public static boolean addFriend(Connection connexion, String myUser, String userFriend) throws SQLException {


		String idUser1 = getUserID(connexion, myUser);
		String idUser2 = getUserID(connexion, userFriend);
		String insert = "INSERT INTO Friends(idUser1, idUser2) VALUES ('"+idUser1+"', '"+idUser2+"')";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(insert);

		if(rs!=0) {
			return false;
		}
		
		return true;
	}


	public static boolean removeFriend(Connection connexion, String myUser, String userFriend) throws SQLException {
		String idUser1 = getUserID(connexion, myUser);
		String idUser2 = getUserID(connexion, userFriend);
		
		String delete = "DELETE FROM Friends WHEER idUser1='"+idUser1+"' AND 'idUser2='"+idUser2+"'";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(delete);

		if(rs!=0) {
			return false;
		}
		
		return true;
	}

	public static boolean getListFriends(Connection connexion, String user) {
		boolean isOK;
//		isOK = databse function that gets list of all friends
		
		if(!isOK) {
			return false;
		}
		return true;
	}

}
