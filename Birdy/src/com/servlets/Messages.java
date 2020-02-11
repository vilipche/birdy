package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.messages.*;


import org.json.JSONObject;



public class Messages extends HttpServlet {
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String message = request.getParameter("message");


		JSONObject json = MessageServices.removeMessage(user, message);
				
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");



		JSONObject json = MessageServices.listMessages(user);
				
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String myUser = request.getParameter("myUser");
		String message = request.getParameter("message");

		JSONObject json = MessageServices.addMessage(myUser, message);			
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}

}
