package com.test;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.services.messages.MessageServices;
import com.bd.*;

public class TestMongo {
	public static void main(String[] args) {
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document> coll = db.getCollection("message");


		
		MessageServices.addMessage("toto", "First message hello");
		
		MongoCursor<Document> cursor = coll.find().iterator();
		while(cursor.hasNext()) {
			Document o = cursor.next();
			System.out.println(o.toJson());
		}

	}
}
