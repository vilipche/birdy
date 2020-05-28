package com.services.messages;

import java.sql.Connection;
import java.sql.SQLException;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.bd.Database;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tools.ErrorJSON;
import com.tools.MessageTool;
import com.tools.SessionTool;
import com.tools.UserTool;

public class MessageServices {


	public static JSONObject addMessage(String myUser, String message) {

		Connection connexion = null;
		MongoDatabase db = null;
		try {

			if(myUser == null || message== null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}

			connexion = Database.getMySQLConnection();

			//Check if the user session is valid
			try {
				if(SessionTool.checkSession(connexion, myUser) == false) {
					return ErrorJSON.serviceRefused("Session Problem!!!", 10000);
				}
			} catch(SQLException e) {
				return ErrorJSON.serviceRefused(e.toString(), -1);
			}
			
			db = Database.getMongoDBConnection();
			MongoCollection<Document> messageColl = db.getCollection("message");



			boolean userCheck = UserTool.userExist(connexion, myUser);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 10000);
			}

			boolean messageCheck = MessageTool.validMessage(message);

			if(!messageCheck) {
				return ErrorJSON.serviceRefused("Message format not valid (too long)", 10000);
			}

			boolean addOK = MessageTool.addMessage(connexion, messageColl, myUser, message);

			if(!addOK) {
				return ErrorJSON.serviceRefused("Error while adding message", 10000);
			}


			return ErrorJSON.serviceAccepted();



		} catch (SQLException e) {
			return ErrorJSON.serviceRefused("Erreur en userExist", 1000);
		}
		finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				System.out.println("Failed to close the connection");
				e.printStackTrace();
				return ErrorJSON.serviceRefused("Erreur en close ", 1000);

			}
		}

	}

	public static JSONObject listMessages(String user) {

		Connection connexion = null;
		MongoDatabase db = null;

		try {

			connexion = Database.getMySQLConnection();
			
			//Check if the user session is valid
			try {
				if(SessionTool.checkSession(connexion, user) == false) {
					return ErrorJSON.serviceRefused("Session Problem!!!", 10000);
				}
			} catch(SQLException e) {
				return ErrorJSON.serviceRefused(e.toString(), -1);
			}
			
			db = Database.getMongoDBConnection();
			MongoCollection<Document> messageColl = db.getCollection("message");

			if(user == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}

			boolean userCheck = UserTool.userExist(connexion, user);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 10000);
			}

			JSONObject jsonListMessages = MessageTool.getListMessages(connexion, messageColl, user);

			if(jsonListMessages == null) {
				return ErrorJSON.serviceRefused("Error while getting list of messages", 10000);
			}

			return ErrorJSON.serviceAccepted("listMessages", jsonListMessages);

		} catch (JSONException e) {
			e.printStackTrace();
			return ErrorJSON.serviceRefused("Probleme dans getListMessage", 100); 
		}
		catch (SQLException e) {
			e.printStackTrace();
			return ErrorJSON.serviceRefused("Erreur dans userExist", 1000);
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



	public static JSONObject removeMessage(ObjectId id, String user) {


		Connection connexion = null;
		MongoDatabase db = null;

		if(id == null) {
			return ErrorJSON.serviceRefused("Mauvais arguments", -1);
		}
		
		try {
			connexion = Database.getMySQLConnection();
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL connection error", 100);
		}
		
		db = Database.getMongoDBConnection();
		
		//Check if the user session is valid
		try {
			if(SessionTool.checkSession(connexion, user) == false) {
				return ErrorJSON.serviceRefused("Session Problem!!!", 10000);
			}
		} catch(SQLException e) {
			return ErrorJSON.serviceRefused(e.toString(), -1);
		}
		
		MongoCollection<Document> messageColl = db.getCollection("message");

		//			boolean userCheck = UserTool.userExist(connexion, user);		
		//
		//			if(!userCheck) {
		//				return ErrorJSON.serviceRefused("User doesn't exist", 0);
		//			}

		boolean isInDB = MessageTool.findMessage(messageColl, id);

		if(!isInDB) {
			return ErrorJSON.serviceRefused("Message not in database", 10000);
		}

		boolean removeOK = MessageTool.removeMessage(messageColl, id);

		if(!removeOK) {
			return ErrorJSON.serviceRefused("Error while removing message", 10000);
		}


		return ErrorJSON.serviceAccepted();



	}


}
