package com.developpez.tomcat;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.services.Operation;


public class Addition extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		int a = Integer.parseInt(req.getParameter("num1"));
		int b = Integer.parseInt(req.getParameter("num2"));
		String o = req.getParameter("op");
		
		Operation op = new Operation();
		
		PrintWriter out = resp.getWriter();
		out.println(o);
		out.println(o=="add");
		out.println(o.isEmpty());
		out.println(o.toString());
		
		
		out.println(a+" "+o+" "+b);
		out.println(op.operation(a,b,o));
	}
	
	
	
}
