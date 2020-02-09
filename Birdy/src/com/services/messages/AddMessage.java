package com.services.messages;

import org.json.JSONException;
import org.json.JSONObject;

import com.tools.ErrorJSON;
import com.tools.FriendTool;
import com.tools.MessageTool;
import com.tools.UserTool;

public class AddMessage {

	public static JSONObject addMessage(String myUser, String message) {

		try {

			if(myUser == null || message== null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}

			boolean userCheck = UserTool.userExist(myUser);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 0);
			}

			boolean messageCheck = MessageTool.validMessage(message);

			if(!messageCheck) {
				return ErrorJSON.serviceRefused("Message format not valid (too long)", 0);
			}

			boolean addOK = MessageTool.addMessage(myUser, message);

			if(!addOK) {
				return ErrorJSON.serviceRefused("Error while adding message", 0);
			}


			return ErrorJSON.serviceAccepted();


		} catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}


	}

}
