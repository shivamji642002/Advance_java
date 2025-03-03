package com.ducat.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ducat.dao.StudentDao;
import com.ducat.dao.impl.StudentDaoImpl;
import com.ducat.model.Student;

@WebServlet("/LoginHandler")
public class LoginHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		StudentDao dao=new StudentDaoImpl();
		Student studentByEmail = dao.getStudentByEmail(email);
		request.setAttribute("user", studentByEmail);
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
