package com.tingyun.database.mongodb;

import com.mongodb.*;

import java.net.UnknownHostException;

public class MongodbTest {

	Mongo mongo;
	DB db;
	DBCollection collection;
	BasicDBObject document;
	BasicDBObject searchQuery;

	public void test(){
		try {
			mongo = new Mongo("192.168.2.130", 27017);
			db = mongo.getDB("yourdb");
			collection = db.getCollection("yourCollection");
			document = new BasicDBObject();
			searchQuery = new BasicDBObject();
			document.put("id", 1001);
			collection.insert(document);
			collection.remove(document);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}

	public static void  main(String[] args){
		MongodbTest mongodbTest = new MongodbTest();
		mongodbTest.test();
	}


}
