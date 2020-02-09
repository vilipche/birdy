package com.services.friends;

import org.json.JSONException;
import org.json.JSONObject;

import com.tools.ErrorJSON;
import com.tools.FriendTool;
import com.tools.UserTool;

public class UnFriend {

	public static JSONObject unFriend(String myUser, String userFriend) {
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
				return ErrorJSON.serviceRefused("You are not friend with the person", 0);
			}

			boolean removeOK = FriendTool.removeFriend(myUser, userFriend);

			if(!removeOK) {
				return ErrorJSON.serviceRefused("Error while removing friend", 0);
			}


			return ErrorJSON.serviceAccepted();


		} catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}
	}
}
