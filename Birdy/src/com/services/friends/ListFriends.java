package com.services.friends;

import org.json.JSONException;
import org.json.JSONObject;

import com.tools.ErrorJSON;
import com.tools.FriendTool;
import com.tools.UserTool;

public class ListFriends {

	public static JSONObject listFriends(String user) {

		try {

			if(user == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}

			boolean userCheck = UserTool.userExist(user);		

			return ErrorJSON.serviceRefused("User doesn't exist", 0);

			boolean getOK = FriendTool.getListFriends(user);

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

	}
}