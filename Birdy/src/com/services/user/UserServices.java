package com.services.user;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import com.bd.*;
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

public class UserServices {

	public static JSONObject newUser(String username, String email, 
			String password, String name, String surname)   {

		if(username == null || email == null || password == null || name == null || surname == null) {
			return ErrorJSON.serviceRefused("Missing argument", -1);
		}

		Connection connexion = null;

		try {	
			connexion= Database.getMySQLConnection();

			boolean is_username = UserTool.usernameExist(connexion, username);
			if(is_username) {
				return ErrorJSON.serviceRefused("User already taken", 0);
			}

			boolean is_email = UserTool.emailExist(connexion, email);
			if(is_email) {
				return ErrorJSON.serviceRefused("Email already taken", 0);
			}

			boolean is_password_valid = UserTool.checkPasswordValid(password);
			if(!is_password_valid) {
				return ErrorJSON.serviceRefused("Unvalid password format", 0);
			}

			boolean is_added = UserTool.insertUser(connexion, username, email, password, name, surname);

			if(!is_added) {
				return ErrorJSON.serviceRefused("Error when adding user in the database", 0);
			}

			return ErrorJSON.serviceAccepted();
		}

		catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}
		finally {
			try {
				connexion.close();
				//TODO dali e dobro vaka, dava problem so init
			} catch (SQLException e) {
				System.out.println("Failed to close the connection");
				e.printStackTrace();
			}
		}


	}
	
	public static JSONObject removeUser(String username, String email, 
			String password)   {

		if(username == null || email == null || password == null ) {
			return ErrorJSON.serviceRefused("Missing argument", -1);
		}

		Connection connexion = null;

		try {	
			connexion= Database.getMySQLConnection();

			boolean is_username = UserTool.usernameExist(connexion, username);
			if(!is_username) {
				return ErrorJSON.serviceRefused("User doesn't exist", 0);
			}

			boolean is_email = UserTool.emailExist(connexion, email);
			if(!is_email) {
				return ErrorJSON.serviceRefused("Email doesn't exist", 0);
			}

			boolean is_password = UserTool.checkPasswordExist(connexion, username, password);
			if(!is_password) {
				return ErrorJSON.serviceRefused("Wrong password", 0);
			}

			boolean is_removed = UserTool.deleteUser(connexion, username);
			
			if(!is_removed) {
				return ErrorJSON.serviceRefused("Couldn't remove user", 0);
			}
			
			return ErrorJSON.serviceAccepted();
		}

		catch (SQLException e) {
			e.printStackTrace();
			return ErrorJSON.serviceRefused(null, 0);
		}
		finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				System.out.println("Failed to close the connection");
				e.printStackTrace();
			}
		}


	}


}





