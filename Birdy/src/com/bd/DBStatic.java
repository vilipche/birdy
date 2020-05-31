package com.bd;

public class DBStatic {
	
	public static boolean pooling = false; 
	
	// MySQL
	public static final String mysql_host = "localhost"; //changer si en local
	public static final String mysql_db = "sotiroski-li";
	public static final String mysql_user = "root";
	public static final String mysql_password = "root";
	public static final String mysql_host_tomcat = "db:3306"; //changer si sur tomcat/postman

	
		//MongoDB
	public static final String mongo_host = "mongodb://localhost:27017";
	public static final String mongo_db = "sotiroski-li";
	public static final String mongo_host_tomcat = "mongo"; // preku war
	public static final String mongo_host_eclipse = "localhost"; // ako lansiram od eclipse, ama ne lansiram
	public static final int mongo_port = 27017;
	
}
