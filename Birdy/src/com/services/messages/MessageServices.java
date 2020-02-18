package com.services.messages;

import java.sql.SQLException;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.bd.Database;
import com.mongodb.client.MongoDatabase;
import com.tools.ErrorJSON;
import com.tools.MessageTool;
import com.tools.UserTool;

public class MessageServices {

	
	public static JSONObject addMessage(String myUser, String message) {

		try {

			if(myUser == null || message== null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}
			
			MongoDatabase db = Database.getMongoDBConnection();
			Document query = new Document();

			
			
			boolean userCheck = UserTool.userExist(myUser);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 0);
			}

			boolean messageCheck = MessageTool.validMessage(message);

			if(!messageCheck) {
				return ErrorJSON.serviceRefused("Message format not valid (too long)", 0);
			}

			boolean addOK = MessageTool.addMessage(myUser, message);

			if(!addOK) {
				return ErrorJSON.serviceRefused("Error while adding message", 0);
			}


			return ErrorJSON.serviceAccepted();


		} catch (JSONException e) {
			return ErrorJSON.serviceRefused(null, 0);
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
	
//	public static JSONObject addMessage(String myUser, String message) {
//
//		try {
//
//			if(myUser == null || message== null) {
//				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
//			}
//
//			boolean userCheck = UserTool.userExist(myUser);		
//
//			if(!userCheck) {
//				return ErrorJSON.serviceRefused("User doesn't exist", 0);
//			}
//
//			boolean messageCheck = MessageTool.validMessage(message);
//
//			if(!messageCheck) {
//				return ErrorJSON.serviceRefused("Message format not valid (too long)", 0);
//			}
//
//			boolean addOK = MessageTool.addMessage(myUser, message);
//
//			if(!addOK) {
//				return ErrorJSON.serviceRefused("Error while adding message", 0);
//			}
//
//
//			return ErrorJSON.serviceAccepted();
//
//
//		} catch (JSONException e) {
//			return ErrorJSON.serviceRefused(null, 0);
//		} catch (SQLException e) {
//			return ErrorJSON.serviceRefused(null, 0);
//		}
//
//	}

	public static JSONObject removeMessage(String user, String message) {
		try {

			if(user == null || message == null) {
				return ErrorJSON.serviceRefused("Mauvais arguments", -1);
			}

			boolean userCheck = UserTool.userExist(user);		

			if(!userCheck) {
				return ErrorJSON.serviceRefused("User doesn't exist", 0);
			}

			boolean isInDB = MessageTool.findMessage(user, message);

			if(!isInDB) {
				return ErrorJSON.serviceRefused("Message not in database", 0);
			}

			boolean removeOK = MessageTool.removeMessage(user, message);

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
