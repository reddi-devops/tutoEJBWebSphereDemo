package com.dppware.tutoejbweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dppware.tutoejbmodule.interfaces.ITutoEJBRemote;

public class TestEjbServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		PrintWriter out = resp.getWriter();
		try {
			String result =  this.invokeEJB("soy el servlet");
			out.println(result); //Do output!!
		} catch (NamingException e) {
			e.printStackTrace();
			out.println(e);
		}
		
	}
	
	
	/**
	 * Invoca al EJB
	 * @param args
	 * @return
	 * @throws NamingException 
	 */
	private String invokeEJB(String args) throws NamingException{
		InitialContext ctx = new InitialContext();
		ITutoEJBRemote ejb  = (ITutoEJBRemote)ctx.lookup("ejb/TutoEJBImpl");
		return ejb.testMethod(args);
	}
	
}
