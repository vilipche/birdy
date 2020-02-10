package com.services.user;

import org.json.JSONException;
import org.json.JSONObject;

import com.tools.ErrorJSON;
import com.tools.UserTool;

/*
 * Nom du web service: CreateUser
 * URL du web service: /user
 * Description du web service: Create a new user in the database 
 * Parametres en entree: username, email, password, name, surname, age
 * Format de sortie: JSON
 * Exemple de sortie: OK KO
 * Erreurs possibles:
 * Avancement du Service:
 * Classes JAVA en rapport avec le Web service:
 * Informations additionneles:
 */

public class CreateUser {

	public static JSONObject newUser(String username, String email, 
			String password, String name, String surname) {
		
		if(username == null || email == null || password == null || name == null || surname == null) {
			return ErrorJSON.serviceRefused("Missing argument", -1);
		}
		
				
			try {
				boolean is_username = UserTool.userExist(username);
				if(is_username) {
					return ErrorJSON.serviceRefused("User already taken", 0);
				}
				
				boolean is_email = UserTool.userExist(email);
				if(is_email) {
					return ErrorJSON.serviceRefused("Email already taken", 0);
				}
				
				boolean is_password_valid = UserTool.checkPasswordValid(password);
				if(!is_password_valid) {
					return ErrorJSON.serviceRefused("Unvalid password format", 0);
				}
				
				return ErrorJSON.serviceAccepted();
			}
			
			catch (JSONException e) {
				return ErrorJSON.serviceRefused(null, 0);
			} catch (SQLException e) {
				return ErrorJSON.serviceRefused(null, 0);
			}
		
			
	}


	private JSONObject checkPassword(String password) {
		JSONObject json = new JSONObject();
		if (password.length()>=8 && password.length()<=24) {
			return ErrorJSON.serviceRefused("Invalid format", -2);
		} 
		return ErrorJSON.serviceAccepted();
	}

}
		
		
	
	
	
