package com.tools;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {
	
	
	public static JSONObject serviceRefused(String message, int codeErreur)  {
		
		JSONObject json = new JSONObject();
		
		//for the types of errors.
		switch (codeErreur) {
		  case -1:
		    System.out.println("Web Error: Argument manquant ou mauvais format");
		    System.out.println(message);
		    break;
		  case 100:
		    System.out.println("JSON Error");
		    System.out.println(message);
		    break;
		  case 1000:
		    System.out.println("SQL Error");
		    System.out.println(message);
		    break;
		  case 10000:
		    System.out.println("JAVA Error");
		    System.out.println(message);
		    break;
		}

			try {
				json.put("codeErreur",codeErreur);
				json.put("message", message);
				json.put("status", "KO");	
			} catch (JSONException e) {
				e.printStackTrace();
			}

		return json;

	}
	
	public static JSONObject serviceAccepted()  {
		JSONObject json = new JSONObject();
		try {
			json.put("status", "OK");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
				
	}
	
	public static JSONObject serviceAccepted(String key, JSONObject paramJson)  {
		JSONObject json = new JSONObject();
		try {
			json.put("status", "OK");
			json.put(key, paramJson);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
				
	}
}
