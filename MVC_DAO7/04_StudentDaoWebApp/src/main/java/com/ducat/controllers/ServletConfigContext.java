package com.ducat.controllers;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = { "/ServletConfigContext", "/shivam" }, initParams = {
		@WebInitParam(name = "owner", value = "Aman"), @WebInitParam(name = "country", value = "india") })
public class ServletConfigContext extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// init
	// service -> Get -> doGet or Post -> doPost
	// destroy
	private ServletConfig config = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("servlet initialized!");
		System.out.println(config.getInitParameter("driver"));
		System.out.println(config.getInitParameter("owner"));
		this.config = config;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("servlet's service method is running!");
		ServletContext context = config.getServletContext();
		context.setAttribute("name", "Aman");
		
		//1. Using global context (ServletContext)
		//2. Using request  (HttpServletRequest)
		//3. Using Session  (HttpSesion)
		
	}

	@Override
	public void destroy() {
		System.out.println("servlet destroyed!");

	}

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}
}