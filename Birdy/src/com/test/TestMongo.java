package com.test;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.services.messages.MessageServices;
import com.bd.*;

public class TestMongo {
	public static void main(String[] args) {

		
//		System.out.println(MessageServices.addMessage("toto", "First message hello"));
//		ObjectId id = new ObjectId("5e53c07754a113411d3edeb0");
//		System.out.println(MessageServices.removeMessage(id));
		
		MongoDatabase db = Database.getMongoDBConnection();
		MongoCollection<Document> coll = db.getCollection("message");
		MongoCursor<Document> cursor = coll.find().iterator();
		
		System.out.println(MessageServices.listMessages("toto"));
		
		while(cursor.hasNext()) {
			Document o = cursor.next();
			System.out.println(o.toJson());
		}

	}
}
