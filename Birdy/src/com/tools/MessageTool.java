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
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;

public class MessageTool {

	public static boolean validMessage(String message) {
		if(message.length() <=140) {
			return true;
		}
		return false;
	}

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

	public static boolean findMessage(MongoCollection<Document> messageColl, ObjectId id) {
		
		Document query = new Document();
		query.append("_id", id);
		MongoCursor<Document> cursor = messageColl.find(query).iterator();
		
		while(cursor.hasNext()) {
			return true;
		}
		
		return false;
	}

	public static boolean removeMessage(MongoCollection<Document> messageColl, ObjectId id) {
		
		Document query = new Document();
		query.append("_id", id);
		DeleteResult dr =  messageColl.deleteOne(query);
		return dr.wasAcknowledged();
		
	}

	public static boolean getListMessages(Connection connexion, MongoCollection<Document> messageColl, String user) {
		try {
			int userID = UserTool.getUserID(connexion, user);
			Document query = new Document();
			query.append("id_autheur",userID);
//			query.append("_id", true); //TODO comment implementer > db.messages.find( {"id":1} , {"_id":true} )


			
			MongoCursor<Document> cursor = messageColl.find(query).iterator();

			while(cursor.hasNext()) {
				System.out.println(cursor.next().toJson());

			}


		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}
	
}






















