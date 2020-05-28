package com.tools;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

public class FriendTool {


	/**
	 * Checks if myUser follows userFollowing
	 */
	public static boolean followExist(Connection connexion, String myUser, String userFollowing) throws SQLException {
		// TODO checks if both arguments are friends in the database;
		int idMyUser = UserTool.getUserID(connexion, myUser);
		int idUserFollowing = UserTool.getUserID(connexion, userFollowing);
		String query = "Select * From Followers Where idUser='"+idMyUser+"' AND idFollowing='"+idUserFollowing+"';";

		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(query);

		while(rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * create a relationship between myuser and userFollow
	 */
	public static boolean followUser(Connection connexion, String myUser, String userFollow) throws SQLException {


		int idMyUser = UserTool.getUserID(connexion, myUser);
		int idUserFollowing = UserTool.getUserID(connexion, userFollow);
		String insert = "INSERT INTO Followers(idUser, idFollowing) VALUES('"+idMyUser+"', '"+idUserFollowing+"');";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(insert);

		if(rs!=0) {
			return true;
		}
		
		return false;
	}


	/**
	 * myUser unfollows userFollow
	 */
	public static boolean unfollow(Connection connexion, String myUser, String userFollow) throws SQLException {
		int idMyUser = UserTool.getUserID(connexion, myUser);
		int idUserFollowing = UserTool.getUserID(connexion, userFollow);
		
		String delete = "DELETE FROM Followers WHERE idUser="+idMyUser+" AND idFollowing="+idUserFollowing+";";

		Statement lecture = connexion.createStatement();
		int rs = lecture.executeUpdate(delete);

		if(rs!=0) {
			return true;
		}
		
		return false;
	}

	/**
	 * return the list of friends of a user
	 */
	public static JSONObject getListFriends(Connection connexion, String user) throws SQLException, JSONException {
		int idMyUser = UserTool.getUserID(connexion, user);

		String query = "Select idFollowing From Followers Where idUser='"+idMyUser+"';";

		Statement lecture = connexion.createStatement();
		ResultSet rs = lecture.executeQuery(query);
		JSONObject json = new JSONObject();
		System.out.println(json == null);
		while(rs.next()) {
			int id = rs.getInt("idFollowing");
			String username = UserTool.getUserFromID(connexion, id);
			json.put(username, id);
		}
		return json;
	}

}
