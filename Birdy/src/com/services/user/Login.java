package com.services.user;
import com.tools.*;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * Nom du web service: Login
 * URL du web service: /login
 * Description du web service: Logs in the new user
 * Parametres en entree: email, password
 * Format de sortie: JSON
 * Exemple de sortie: OK KO
 * Erreurs possibles: Wrong arguments, no user in the db, wrong password
 * Avancement du Service:
 * Classes JAVA en rapport avec le Web service:
 * Informations additionneles:
 */

public class Login {

	public static JSONObject Login(String email, String pass) {
		if(log == null || pass == null) {
			return ErrorJSON.serviceRefused("Mauvais arguments", -1);
		}
		try {
			boolean is_user = UserTool.userExist(email);
			
			if(!is_user) {
				return ErrorJSON.serviceRefused("No user in the db", 0);
			}
			
			boolean pass_ok = UserTool.checkPass(email, pass);
			
			if(!pass_ok) {
				return ErrorJSON.serviceRefused("Error password", 0);
			}
			
			UserTool.loginTime(email);
			
			String key = UserTool.insertUser(email, pass);
			
			return ErrorJSON.serviceAccepted();
		} catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}
	}
	
}
