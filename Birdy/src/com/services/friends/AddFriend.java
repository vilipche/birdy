package com.services.friends;

import org.json.JSONException;
import org.json.JSONObject;

import com.tools.ErrorJSON;
import com.tools.FriendTool;
import com.tools.UserTool;

public class AddFriend {

	public static JSONObject addFriend(String myUser, String userFriend ) {
		
		try {
			
			if(userFriend == null || myUser == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}
			
			boolean userCheck = UserTool.userExist(userFriend);		
			
			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 0);
			}
			
			boolean friendCheck = FriendTool.friendExist(myUser, userFriend);
			
			if(!friendCheck) {
				return ErrorJSON.serviceRefused("You are friends with the person", 0);
			}
			
			boolean addOK = FriendTool.addFriend(myUser, userFriend);
	
			if(!addOK) {
				return ErrorJSON.serviceRefused("Error while adding friend", 0);
			}
			
			
			return ErrorJSON.serviceAccepted();
			
			
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}
		
	
	}
	
}
