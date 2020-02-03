package com.developpez.tomcat;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleServlet extends HttpServlet {
	
	private int counter = 0;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println("<HTML><BODY>");
		resp.getWriter().println(this+": <br>");
		
		for(int c=0;c<10;c++) {
			resp.getWriter().println("Counter = "+counter+"<BR>");
			counter++;
		}
		
		resp.getWriter().println("</BODY></HTML>");
	}
	
}
