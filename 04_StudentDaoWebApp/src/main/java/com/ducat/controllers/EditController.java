package com.ducat.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import com.ducat.dao.StudentDao;
import com.ducat.dao.impl.StudentDaoImpl;
import com.ducat.model.Student;

@WebServlet("/EditController")
public class EditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id =Integer.parseInt(request.getParameter("id").trim());
		String fname = request.getParameter("fname").trim();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		long mobileNo = Long.parseLong(request.getParameter("mobileNo").trim());
		char gender = request.getParameter("gender").trim().charAt(0);
		boolean isMember = false;
		if (request.getParameter("isMember") != null) {
			isMember = request.getParameter("isMember").trim().equals("yes");
		}
		double fee = Double.parseDouble(request.getParameter("fee").trim());
		LocalDate joinedAt = LocalDate.parse(request.getParameter("joinedAt").trim());

		Student stu = new Student(id, fname, mobileNo, gender, isMember, fee, joinedAt, email, password);
		StudentDao dao = new StudentDaoImpl();
		boolean status = dao.updateStudent(stu);
		if (status) {
			response.getWriter().append("student edited succesfully!");
			response.sendRedirect("AdminDashboard");
		} else {
			response.getWriter().append("unable to edit student!");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
