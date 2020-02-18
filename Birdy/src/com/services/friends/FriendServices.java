package com.services.friends;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;	
import org.json.JSONObject;

import com.bd.Database;
import com.tools.ErrorJSON;
import com.tools.FriendTool;
import com.tools.UserTool;

public class FriendServices {
	//TODO kako ke raboti so unidirectional?

	public static JSONObject unFriend(String myUser, String userFriend) {
				
		Connection connexion = null;

		try {
			connexion= Database.getMySQLConnection();
			
			if(userFriend == null || myUser == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}

			boolean userCheck = UserTool.userExist(connexion, userFriend);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 0);
			}

			boolean friendCheck = FriendTool.friendshipExist(connexion, myUser, userFriend);

			if(!friendCheck) {
				return ErrorJSON.serviceRefused("You are not friend with the person", 0);
			}

			boolean removeOK = FriendTool.removeFriend(connexion, myUser, userFriend);

			if(!removeOK) {
				return ErrorJSON.serviceRefused("Error while removing friend", 0);
			}


			return ErrorJSON.serviceAccepted();


		} catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}
		finally {
			try {
				connexion.close();
				//TODO dali e dobro vaka, dava problem so init
			} catch (SQLException e) {
				System.out.println("Failed to close the connection");
				e.printStackTrace();
			}
		}
	}

	public static JSONObject listFriends(String user) {
		Connection connexion = null;
		try {

			if(user == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}
			
			connexion= Database.getMySQLConnection();
			
			boolean userCheck = UserTool.userExist(connexion, user);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 0);	
			}
			
			//TODO KAKO?
			boolean getOK = FriendTool.getListFriends(connexion, user);

			if(!getOK) {
				return ErrorJSON.serviceRefused("Error while getting list of friends", 0);
			}

			return ErrorJSON.serviceAccepted();

		}
		catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}
		finally {
			try {
				connexion.close();
				//TODO dali e dobro vaka, dava problem so init
			} catch (SQLException e) {
				System.out.println("Failed to close the connection");
				e.printStackTrace();
			}
		}

	}
	
	public static JSONObject addFriend(String myUser, String userFriend ) {
		
		Connection connexion = null;
		try {

			if(userFriend == null || myUser == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}
			
			connexion= Database.getMySQLConnection();
			
			boolean userCheck = UserTool.userExist(connexion, userFriend);		
			
			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 0);
			}
			
			boolean friendCheck = FriendTool.friendshipExist(connexion, myUser, userFriend);
			
			if(!friendCheck) {
				return ErrorJSON.serviceRefused("You are not friends with the person", 0);
			}
			
			boolean addOK = FriendTool.addFriend(connexion, myUser, userFriend);
	
			if(!addOK) {
				return ErrorJSON.serviceRefused("Error while adding friend", 0);
			}
			
			
			return ErrorJSON.serviceAccepted();
			
			
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}
		finally {
			try {
				connexion.close();
				//TODO dali e dobro vaka, dava problem so init
			} catch (SQLException e) {
				System.out.println("Failed to close the connection");
				e.printStackTrace();
			}
		}
		
	
	}
	
}
