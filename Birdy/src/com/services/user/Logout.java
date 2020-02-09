package com.services.user;

import org.json.JSONException;
import org.json.JSONObject;

import com.tools.ErrorJSON;
import com.tools.UserTool;

/*
 * Nom du web service: Log Out
 * URL du web service: /logout
 * Description du web service: Logout the user from the session 
 * Parametres en entree: username
 * Format de sortie: JSON
 * Exemple de sortie: OK KO
 * Erreurs possibles: Wrong arguments, no user in the db, logout error
 * Avancement du Service:
 * Classes JAVA en rapport avec le Web service:
 * Informations additionneles:
 */

public class Logout {


	public static JSONObject Logout(String login) {
		if(login == null) {
			return ErrorJSON.serviceRefused("Mauvais arguments", -1);
		}
		
		try {
			
			boolean is_user = UserTool.userExist(login);

			if(!is_user) {
				return ErrorJSON.serviceRefused("No user in the db", 0);
			}
			
			UserTool.logoutTime(login);
			
			boolean isLoggedOut = UserTool.logoutUser(login);
			
			if(!isLoggedOut) {
				return ErrorJSON.serviceRefused("User didn't log out ", 1000);	
			}
			
			return ErrorJSON.serviceAccepted();
		
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}
		
		
		
	}

	public static JSONObject Login(String login, String pass) {
		if(log == null || pass == null) {
			return ErrorJSON.serviceRefused("Mauvais arguments", -1);
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

			UserTool.loginTime(login);

			String key = UserTool.insertUser(login, pass);

			return ErrorJSON.serviceAccepted();
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}
	}



}
