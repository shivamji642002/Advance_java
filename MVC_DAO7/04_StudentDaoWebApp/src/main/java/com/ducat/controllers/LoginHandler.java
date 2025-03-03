package com.ducat.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.ducat.dao.StudentDao;
import com.ducat.dao.impl.StudentDaoImpl;
import com.ducat.model.Student;

@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		System.out.println("LoginHandler.doPost()");
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		StudentDao dao=new StudentDaoImpl();
//		System.out.println(email+" : "+password);
		Student studentByEmail = dao.getStudentByEmail(email);
//		System.out.println(studentByEmail);
//		request.setAttribute("user", studentByEmail);
		
		HttpSession session=request.getSession(true);// if no session exist create them
		session.setAttribute("user", studentByEmail);
		session.setMaxInactiveInterval(5*60);// 5 Minute of inactivity
		if (studentByEmail == null) {
			response.getWriter().append("email not found in DB");
			request.getRequestDispatcher("/LoginController")
			.include(request, response);
		}
		else
		{
			if (studentByEmail.getPassword().equals(password)) 
			{
				response.getWriter().append("Login Succesfully!");
				request.getRequestDispatcher("/AdminDashboard")
				.forward(request, response);
			} 
			else {
				response.getWriter().append("incorrect password!");
				request.getRequestDispatcher("/LoginController")
				.include(request, response);
			}
		}
	}

}
