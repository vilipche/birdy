package com.tools;

public class FriendTool {

	public static boolean friendExist(String myUser, String userFriend) {
		// TODO checks if both arguments are friends in the database;
		String query = "Select * From Friends Where f1=myUser AND f2=userFriend";
		
		if(!db(query)) {
			return true;
		}
		
		return false;
	}

	public static boolean addFriend(String myUser, String userFriend) {
		// TODO adds friends in the database
		
		boolean isOK;
//		isOK = databse function that adds them as friends
		
		if(!isOK) {
			return false;
		}
		return true;
	}

	public static boolean removeFriend(String myUser, String userFriend) {
		boolean isOK;
//		isOK = databse function that removes them as friends
		
		if(!isOK) {
			return false;
		}
		return true;
	}

	public static boolean getListFriends(String user) {
		boolean isOK;
//		isOK = databse function that gets list of all friends
		
		if(!isOK) {
			return false;
		}
		return true;
	}

}
