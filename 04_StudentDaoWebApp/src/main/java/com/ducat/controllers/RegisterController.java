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

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		out.print("""
				<!doctype html>
				<html lang="en">
				<head>
				<meta charset="utf-8">
				<meta name="viewport" content="width=device-width, initial-scale=1">
				<title>Student ERP</title>
				<link
					href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
					rel="stylesheet"
					integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
					crossorigin="anonymous">
					<style>
						.radio{
						display:inline;
						min-height:0.9rem !important;
						}
					</style>
				</head>
				<body>
					<nav class="navbar navbar-expand-lg bg-body-tertiary"
						data-bs-theme='dark'>
						<div class="container-fluid">
							<a class="navbar-brand" href="#">StudentERP</a>
							<button class="navbar-toggler" type="button"
								data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
								aria-controls="navbarSupportedContent" aria-expanded="false"
								aria-label="Toggle navigation">
								<span class="navbar-toggler-icon"></span>
							</button>
							<div class="collapse navbar-collapse" id="navbarSupportedContent">
								<ul class="navbar-nav me-auto mb-2 mb-lg-0">
									<li class="nav-item"><a class="nav-link active"
										aria-current="page" href="#">Home</a></li>
									<li class="nav-item"><a class="nav-link" href="#">About US</a>
									</li>
									<li class="nav-item"><a class="nav-link" href="#">Contact
											US</a></li>
									<li class="nav-item dropdown"><a
										class="nav-link dropdown-toggle" href="#" role="button"
										data-bs-toggle="dropdown" aria-expanded="false"> Services </a>
										<ul class="dropdown-menu">
											<li><a class="dropdown-item" href="#">Action</a></li>
											<li><a class="dropdown-item" href="#">Another action</a></li>
											<li><hr class="dropdown-divider"></li>
											<li><a class="dropdown-item" href="#">Something else
													here</a></li>
										</ul></li>

								</ul>
								<div class='flex justify-content-between'>
									<a href="LoginController"><button class="btn btn-success">login</button></a>
									<a href="RegisterController"><button class="btn btn-primary">register</button></a>
								</div>
							</div>
						</div>
					</nav>""");

		out.print(
				"""

						<div class="container my-3">
							<form action="RegisterController" method="post">
							<div class="row">
								<div class="col-md-6">
									<label for='fname' class='form-label'>Full Name</label>
									<input type='text' class='form-control' name='fname' id='fname'>
								</div>
								<div class="col-md-6">
									<label for='email' class='form-label'>Email</label>
									<input type='email' class='form-control' name='email' id='email'>
								</div>
								<div class="col-md-12">
									<label for='pass' class='form-label'>Password</label>
									<input type='password' class='form-control' name='password' id='pass'>
								</div>
								<div class="col-md-12">
									<label for='mobileNo' class='form-label'>MobileNo</label>
									<input type='tel' class='form-control' name='mobileNo' id='mobileNo'>
								</div>
								<div class="col-md-12 flex">
									<label for='gender' class='form-check-label'>Gender</label>
									<input type='radio' class='form-check radio' name='gender' value="Male" id='gender'> Male
									<input type='radio' class='form-check radio' name='gender' value="Female" id='gender'> Female
									<input type='radio' class='form-check radio' name='gender' value="Others" id='gender'> Others
								</div>
								<div class="col-md-12">
									<label for='isMember' class='form-check-label'>Is Member ? </label>
									<input type='checkbox' class='form-check radio' name='isMember' value="yes" id='isMember'>
								</div>
								<div class="col-md-12">
									<label for='fee' class='form-label'>Fee</label>
									<input type='number' class='form-control' name='fee' id='fee'>
								</div>
								<div class="col-md-12">
									<label for='joinedAt' class='form-label'>Joined At</label>
									<input type='date' class='form-control' name='joinedAt' id='joinedAt'>
								</div>
								<div class="col-md-12 mt-3">
									<input class='btn btn-secondary' type='reset' value='reset'>
									<input class='btn btn-primary' type='submit' value='submit'>
								</div>
							</div>
							</form>

						</div>


						""");

		out.print("""
						<script
						src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
						integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
						crossorigin="anonymous"></script>
				</body>
				</html>""");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		Student stu = new Student(0, fname, mobileNo, gender, isMember, fee, joinedAt, email, password);
		StudentDao dao = new StudentDaoImpl();
		int status = dao.registerStudent(stu);
		if (status > 0) {
			response.getWriter().append("student registered succesfully!");
			response.sendRedirect("./index.html");
		} else {
			response.getWriter().append("unable to register student!");
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

		}
	}

}
