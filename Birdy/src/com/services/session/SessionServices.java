package com.services.session;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONObject;

import com.bd.Database;
import com.tools.ErrorJSON;
import com.tools.SessionTool;
import com.tools.UserTool;

public class SessionServices {



	public static JSONObject login(String login, String pass)  {
		if(login == null || pass == null) {
			return ErrorJSON.serviceRefused("Mauvais arguments", -1);
		}

		Connection connexion = null;

		try {

			connexion = Database.getMySQLConnection();

			boolean is_user = UserTool.userExist(connexion, login);

			if(!is_user) {
				return ErrorJSON.serviceRefused("No user in the db", 10000);
			}

			boolean pass_ok = UserTool.checkPass(connexion, login, pass);

			if(!pass_ok) {
				return ErrorJSON.serviceRefused("Error password", 10000);
			}
			
			try {
				SessionTool.insertSession(connexion, login);
			} catch (SQLException e) {
				return ErrorJSON.serviceRefused("Deja connecte", 100);
			}
			
			return ErrorJSON.serviceAccepted();

		}
		catch (SQLException e) {
			e.printStackTrace();
			return ErrorJSON.serviceRefused("user exist problem", 100);
		}
		finally {
			try {
				connexion.close();
				//TODO dali e dobro vaka, dava problem so init
			} catch (SQLException e) {
				e.printStackTrace();
				return ErrorJSON.serviceRefused("Failed to close the connection", 100);
			}
		}
	}

	public static JSONObject logout(String login)  {
		if(login == null) {
			return ErrorJSON.serviceRefused("Mauvais arguments", -1);
		}

		Connection connexion = null;


		try {
			connexion = Database.getMySQLConnection();

			boolean is_user = UserTool.userExist(connexion, login);

			if(!is_user) {
				return ErrorJSON.serviceRefused("No user in the db", 1000);
			}


			boolean isLoggedOut = SessionTool.logoutUser(connexion, login);

			if(!isLoggedOut) {
				return ErrorJSON.serviceRefused("User didn't log out ", 1000);	
			}


			return ErrorJSON.serviceAccepted();


		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} 
		finally {
			try {
				connexion.close();
				//TODO dali e dobro vaka, dava problem so init
			} catch (SQLException e) {
				e.printStackTrace();
				return ErrorJSON.serviceRefused("Failed to close the connection", 100);
			}
		}



	}
}

