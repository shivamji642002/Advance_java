package com.ducat.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ducat.dao.StudentDao;
import com.ducat.dao.impl.StudentDaoImpl;

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		int id = Integer.parseInt(request.getParameter("id"));
		StudentDao dao=new StudentDaoImpl();
		boolean status = dao.deleteStudent(id);
		RequestDispatcher rd = request.getRequestDispatcher("AdminDashboard");
		if (status) {
			response.getWriter().append("Student deleted!");
		} else {
			response.getWriter().append("unable to delete Student!");
		}
		rd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
