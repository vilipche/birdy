package com.services.messages;

import org.json.JSONException;
import org.json.JSONObject;

import com.tools.ErrorJSON;
import com.tools.FriendTool;
import com.tools.MessageTool;
import com.tools.UserTool;

public class RemoveMessage {

	public static JSONObject removeMessage(String user, String message) {
		try {

			if(user == null || message == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}

			boolean userCheck = UserTool.userExist(user);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 0);
			}

			boolean isInDB = MessageTool.findMessage(user, message);

			if(!isInDB) {
				return ErrorJSON.serviceRefused("Message not in database", 0);
			}

			boolean removeOK = MessageTool.removeMessage(user, message);

			if(!removeOK) {
				return ErrorJSON.serviceRefused("Error while removing message", 0);
			}


			return ErrorJSON.serviceAccepted();


		} catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}
	}


}
