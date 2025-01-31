package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.services.friends.FriendServices;


public class Friends extends HttpServlet {


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String myUser = request.getParameter("myUser");
		String userFriend = request.getParameter("userFriend");

		JSONObject json = FriendServices.unFollow(myUser, userFriend);
				PrintWriter out = response.getWriter();

		out.println(json.toString());
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userFriend = request.getParameter("userFriend");

		JSONObject json = FriendServices.listFriends(userFriend);
		PrintWriter out = response.getWriter();

		out.println(json.toString());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String myUser = request.getParameter("myUser");
		String userFriend = request.getParameter("userFriend");

		JSONObject json = FriendServices.follow(myUser, userFriend);

		PrintWriter out = response.getWriter();

		out.println(json.toString());

	}

}
