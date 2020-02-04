package com.services;

import org.json.JSONObject;

import com.tools.ErrorJSON;

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
			String password, String name, String surname, int age) {
		
		
		
		//connect to DB
		//check if username and email are in the database
		
		//check if query for creation returns true or false
		//if returns false
		//return ErrorJSON.serviceRefused("User creation unsucsessful",-1);
		//else
		//return ErrorJSON.serviceAccepted();
		
		
		
	}
	
	private JSONObject checkPassword(String password) {
		JSONObject json = new JSONObject();
		if (password.length()>=8 && password.length()<=24) {
			return ErrorJSON.serviceRefused("Invalid format", -2);
		} 
		return ErrorJSON.serviceAccepted();
	}
}
