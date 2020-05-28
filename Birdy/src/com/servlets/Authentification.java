package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.services.session.SessionServices;
import com.services.user.*;

public class Authentification extends HttpServlet {


	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login"); //loign -> soit username soit email


		JSONObject json = SessionServices.logout(login);
		PrintWriter out = response.getWriter();
		out.println(json.toString());

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		JSONObject json = SessionServices.login(email, password);
		PrintWriter out = response.getWriter();

		out.println(json.toString());
	}


}
