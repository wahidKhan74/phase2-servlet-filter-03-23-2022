package com.simplilearn.webapp.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/auth")
public class AuthFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("-- Filter Initialized --");
	}

	public void destroy() {
		System.out.println("-- Filter Destoryed ! --");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		// request params
		String userEmail = request.getParameter("useremail");
		String userPassword = request.getParameter("password");

		request.getRequestDispatcher("index.html").include(request, response);
		
		if(userEmail.equals("") && userPassword.equals("")) {
			out.println("<h2 style='color:red'>Login Failed  * Required filled are missing! </h2>");
		} else {
			if(userEmail.equals("admin@gmail.com") && userPassword.equals("admin@123")) {
				out.println("<h2 style='color:green'>Login sucessfull ! for user '"+userEmail+"' </h2>");
				// validation completed.
				chain.doFilter(request, response);
			} else {
				out.println("<h2 style='color:red'>Login Failed * Invalid credntials </h2>");
			}
		}
	
	}

}
