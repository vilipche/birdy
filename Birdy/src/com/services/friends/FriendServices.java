package com.services.friends;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;	
import org.json.JSONObject;

import com.bd.Database;
import com.tools.ErrorJSON;
import com.tools.FriendTool;
import com.tools.SessionTool;
import com.tools.UserTool;

public class FriendServices {
	//TODO kako ke raboti so unidirectional?

	public static JSONObject unFollow(String myUser, String userFriend)  {
				
		Connection connexion = null;

		try {
			connexion= Database.getMySQLConnection();
			
			if(userFriend == null || myUser == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}
			
			//Check if the user session is valid
			try {
				if(SessionTool.checkSession(connexion, myUser) == false) {
					return ErrorJSON.serviceRefused("Session Problem!!!", 10000);
				}
			} catch(SQLException e) {
				return ErrorJSON.serviceRefused(e.toString(), -1);
			}
			
			boolean userCheck = UserTool.userExist(connexion, userFriend);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 10000);
			}

			boolean followCheck = FriendTool.followExist(connexion, myUser, userFriend);

			if(!followCheck) {
				return ErrorJSON.serviceRefused("You are not following this person", 10000);
			}

			boolean removeOK = FriendTool.unfollow(connexion, myUser, userFriend);

			if(!removeOK) {
				return ErrorJSON.serviceRefused("Error while unfollowing friend", 10000);
			}


			return ErrorJSON.serviceAccepted();
		}


		catch (SQLException e) {
			return ErrorJSON.serviceRefused("Erreur dans userExist ou followExist", 1000);
		}
		finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				System.out.println("Failed to close the connection");
				e.printStackTrace();
			}
		}
	}

	
	public static JSONObject follow(String myUser, String userFriend )  {
		
		Connection connexion = null;
		try {
			

			if(userFriend == null || myUser == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}
			
			connexion = Database.getMySQLConnection();
			
			//Check if the user session is valid
			try {
				if(SessionTool.checkSession(connexion, myUser) == false) {
					return ErrorJSON.serviceRefused("Session Problem!!!", 10000);
				}
			} catch(SQLException e) {
				return ErrorJSON.serviceRefused(e.toString(), -1);
			}

			
			boolean userCheck = UserTool.userExist(connexion, userFriend);		
			
			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 10000);
			}
			
			boolean friendCheck = FriendTool.followExist(connexion, myUser, userFriend);
			
			if(friendCheck) {
				return ErrorJSON.serviceRefused("You are already following the person", 10000);
			}
			
			boolean addOK = FriendTool.followUser(connexion, myUser, userFriend);
	
			if(!addOK) {
				return ErrorJSON.serviceRefused("Error while adding friend", 10000);
			}
			
			
			return ErrorJSON.serviceAccepted();
			
		}
		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL Error", 1000);
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
	
	public static JSONObject listFriends(String user)  {
		//TODO 
		Connection connexion = null;
		try {

			if(user == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}
			
			connexion= Database.getMySQLConnection();
			
			//Check if the user session is valid
			try {
				if(SessionTool.checkSession(connexion, user) == false) {
					return ErrorJSON.serviceRefused("Session Problem!!!", 10000);
				}
			} catch(SQLException e) {
				return ErrorJSON.serviceRefused(e.toString(), -1);
			}

			
			boolean userCheck = UserTool.userExist(connexion, user);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 10000);	
			}
			
			//TODO 
			JSONObject jsonListFriends = FriendTool.getListFriends(connexion, user);

			if(jsonListFriends == null) {
				return ErrorJSON.serviceRefused("Error while getting list of friends", 100);
			}

			return ErrorJSON.serviceAccepted("friends", jsonListFriends);

		}

		catch (SQLException e) {
			return ErrorJSON.serviceRefused("Erreur sur le test UserExist", 1000);
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused("Json Error", 100);

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
