package com.tools;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FriendTool {


	
	public static boolean followExist(Connection connexion, String myUser, String userFollowing) throws SQLException {
		// TODO checks if both arguments are friends in the database;
		int idMyUser = DBTool.getUserID(connexion, myUser);
		int idUserFollowing = DBTool.getUserID(connexion, userFollowing);
		String query = "Select * From Followers Where idUser='"+idMyUser+"' AND idFollowing='"+idUserFollowing+"'";

		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(query);

		while(rs.next()) {
			return true;
		}
		return false;
	}

	public static boolean followUser(Connection connexion, String myUser, String userFollow) throws SQLException {


		int idMyUser = DBTool.getUserID(connexion, myUser);
		int idUserFollowing = DBTool.getUserID(connexion, userFollow);
		String insert = "INSERT INTO Followers(idUser, idFollowing) VALUES ('"+idMyUser+"', '"+idUserFollowing+"')";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(insert);

		if(rs!=0) {
			return true;
		}
		
		return false;
	}


	public static boolean unfollow(Connection connexion, String myUser, String userFollow) throws SQLException {
		int idMyUser = DBTool.getUserID(connexion, myUser);
		int idUserFollowing = DBTool.getUserID(connexion, userFollow);
		
		String delete = "DELETE FROM Followers WHERE idUser="+idMyUser+" AND idFollowing="+idUserFollowing;

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(delete);

		if(rs!=0) {
			return true;
		}
		
		return false;
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
