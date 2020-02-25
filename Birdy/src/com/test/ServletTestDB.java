package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.messages.*;
import com.services.user.UserServices;

import org.json.JSONObject;



public class ServletTestDB extends HttpServlet {
	
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserServices.newUser("qwerty", "werwer", "asda231SAD", "qwerty", "qwerty");
//		com.test.ServicesTestBD.insertLine();			
		
	}

}
