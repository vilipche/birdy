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
			db = Database.getMongoDBConnection();
			MongoCollection<Document> messageColl = db.getCollection("message");

			
			boolean userCheck = UserTool.userExist(connexion, myUser);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 0);
			}

			boolean messageCheck = MessageTool.validMessage(message);

			if(!messageCheck) {
				return ErrorJSON.serviceRefused("Message format not valid (too long)", 0);
			}

			boolean addOK = MessageTool.addMessage(connexion, messageColl, myUser, message);

			if(!addOK) {
				return ErrorJSON.serviceRefused("Error while adding message", 0);
			}


			return ErrorJSON.serviceAccepted();



		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}

	}
	
	public static JSONObject listMessages(String user) {

		try {

			if(user == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}

			boolean userCheck = UserTool.userExist(user);		

			return ErrorJSON.serviceRefused("User doesn't exist", 0);

			boolean getOK = MessageTool.getListMessages(user);

			if(!getOK) {
				return ErrorJSON.serviceRefused("Error while getting list of messages", 0);
			}

			return ErrorJSON.serviceAccepted();

		}
		catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(null, 0);
		}

	}
	


	public static JSONObject removeMessage(ObjectId id) {
		
		Connection connexion = null;
		MongoDatabase db = null;
		
		try {

			if(user == null || message == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}
			
			db = Database.getMongoDBConnection();
			MongoCollection<Document> messageColl = db.getCollection("message");

//			boolean userCheck = UserTool.userExist(connexion, user);		
//
//			if(!userCheck) {
//				return ErrorJSON.serviceRefused("User doesn't exist", 0);
//			}

			boolean isInDB = MessageTool.findMessage(messageColl, id);

			if(!isInDB) {
				return ErrorJSON.serviceRefused("Message not in database", 0);
			}

			boolean removeOK = MessageTool.removeMessage(messageColl, id);

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
