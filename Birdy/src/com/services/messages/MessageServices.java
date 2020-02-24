package com.services.messages;

import java.sql.Connection;
import java.sql.SQLException;

import org.bson.Document;
import org.bson.types.ObjectId;

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
		finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				System.out.println("Failed to close the connection");
				e.printStackTrace();
			}
		}

	}

	public static JSONObject listMessages(String user) {

		Connection connexion = null;
		MongoDatabase db = null;

		try {

			connexion = Database.getMySQLConnection();
			db = Database.getMongoDBConnection();
			MongoCollection<Document> messageColl = db.getCollection("message");

			if(user == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}

			boolean userCheck = UserTool.userExist(connexion, user);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 0);
			}

			boolean getOK = MessageTool.getListMessages(connexion, messageColl, user);

			if(!getOK) {
				return ErrorJSON.serviceRefused("Error while getting list of messages", 0);
			}

			return ErrorJSON.serviceAccepted();

		}
		catch (SQLException e) {
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



	public static JSONObject removeMessage(ObjectId id) {


		MongoDatabase db = null;

		if(id == null) {
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



	}


}
