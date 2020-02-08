package com.services;
import com.tools.*;

import org.json.JSONException;
import org.json.JSONObject;

public class Login {

	public static JSONObject Login(String login, String pass) {
		if(log == null || pass == null) {
			ErrorJSON.serviceRefused("Mauvais arguments", 0);
		}
		try {
			boolean is_user = UserTool.userExist(login);
			
			if(is_user) {
				return ErrorJSON.serviceRefused("No user in the db", 0);
			}
			
			boolean pass_ok = UserTool.checkPass(login, pass);
			
			if(!pass_ok) {
				return ErrorJSON.serviceRefused("Error password", 0);
			}
			
			String key = UserTool.insertUser(login, pass);
			
			return ErrorJSON.serviceAccepted();
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}
	}
	
}
