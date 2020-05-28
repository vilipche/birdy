package com.tools;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bson.BSON;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;

public class MessageTool {

	/**
	 * returns true if the message is less than 140 characters
	 */
	public static boolean validMessage(String message) {
		if(message.length() <=140) {
			return true;
		}
		return false;
	}

	/**
	 * Function used to add the message in a collection
	 */
	public static boolean addMessage(Connection connexion, MongoCollection<Document> messageColl, String myUser, String message) {

		try {
			Document doc = new Document();
			GregorianCalendar calendar = new GregorianCalendar();
			Date ddj = calendar.getTime();
			List<Document> likes =new ArrayList<>();
			
			int userID = UserTool.getUserID(connexion, myUser);

			doc.append("id_autheur", userID);
			doc.append("author_name", myUser);
			doc.append("message", message);
			doc.append("date", ddj);
			doc.append("likes", likes); //TODO ? arrayList?

			messageColl.insertOne(doc);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;


	}

	/**
	 * Find a message with an objectid in a collection
	 */
	public static boolean findMessage(MongoCollection<Document> messageColl, ObjectId id) {
		
		Document query = new Document();
		query.append("_id", id);
		MongoCursor<Document> cursor = messageColl.find(query).iterator();
		
		while(cursor.hasNext()) {
			return true;
		}
		
		return false;
	}

	/**
	 * Remove the emssage from the collection using an objectid
	 */
	public static boolean removeMessage(MongoCollection<Document> messageColl, ObjectId id) {
		
		Document query = new Document();
		query.append("_id", id);
		DeleteResult dr =  messageColl.deleteOne(query);
		return dr.wasAcknowledged();
		
	}

	/**
	 * Return the list of messages of a suer from the collection
	 */
	@SuppressWarnings("finally")
	public static JSONObject getListMessages(Connection connexion, MongoCollection<Document> messageColl, String user) throws JSONException {
		JSONObject json = null;
		try {
			int userID = UserTool.getUserID(connexion, user);
			Document query = new Document();
			query.append("id_autheur",userID);

			MongoCursor<Document> cursor = messageColl.find(query).iterator();
			json = new JSONObject();
			
			while(cursor.hasNext()) {
				Document d = cursor.next();
//				System.out.println(d.get("_id"));
//				System.out.println(d.get("message"));
				json.put(d.get("_id").toString(), d.get("message"));
			}
			
		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
			return json;
		}
		

		
	}
	
}






















