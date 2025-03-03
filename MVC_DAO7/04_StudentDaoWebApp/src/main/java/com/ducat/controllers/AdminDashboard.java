package com.ducat.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import com.ducat.dao.StudentDao;
import com.ducat.dao.impl.StudentDaoImpl;
import com.ducat.model.Student;

@WebServlet("/AdminDashboard")
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if (session == null) {
			request.setAttribute("msg","pls login First!");
			request.getRequestDispatcher("/LoginController").forward(request, response);
		}
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
								""");
//		Student curStu = (Student) request.getAttribute("user");
		Student curStu=new Student();
		if (session != null) {
			
			curStu = (Student) session.getAttribute("user");
		}
		if (curStu.getEmail() != null) {
			out.print("<span class='text-white'>Welcome " + curStu.getEmail()+"</span>");
			out.print("""
					<a href="LogoutController"><button class="btn btn-warning">logout</button></a>
					""");
		} else {
			out.print("""
					<a href="LoginController"><button class="btn btn-success">login</button></a>
					<a href="RegisterController"><button class="btn btn-primary">register</button></a>
					""");
		}
		out.print("""
									</div>
							</div>
						</div>
					</nav>
				""");
		StudentDao dao = new StudentDaoImpl();
		List<Student> allStudents = dao.getAllStudents();
		out.print("""
				<h1 class="text-center my-3">All Students List!</h1>
				<div class="mx-auto" style='width:90%;overflow-x:auto;'>
				<table class="table table-striped" border='1'>
				<thead class="table-dark">
				<tr>
				""");
		String[] columns = dao.getColumnsName();

		for (String col : columns) {
			out.print("<td>" + col + "</td>");
		}
		out.print("<td>Actions</td>");
		out.print("</tr>  </thead><tbody>");
		for (Student student : allStudents) {
			out.print("<tr>");
			out.print("<td>" + student.getId() + "</td>");
			out.print("<td>" + student.getName() + "</td>");
			out.print("<td>" + student.getMobileNo() + "</td>");
			out.print("<td>" + student.getGender() + "</td>");
			out.print("<td>" + student.isMember() + "</td>");
			out.print("<td>" + student.getFee() + "</td>");
			out.print("<td>" + student.getJoinedAt() + "</td>");
			out.print("<td>" + student.getEmail() + "</td>");
			out.print("<td>" + student.getPassword() + "</td>");
			out.print("<td width='450px'>"
					+ "<button class='btn btn-primary' data-bs-toggle='modal' data-bs-target='#editModal' onclick='fillModal(this)'>Edit</button>"
					+ "<a href='DeleteController?id=" + student.getId()
					+ "'><button class='btn btn-danger'>Delete</button></a>" + "</td>");
			out.print("</tr>");
		}
		out.print("""
				</tbody>
				<tfoot class="table-dark">
				<tr >
				""");
		for (String col : columns) {
			out.print("<td>" + col + "</td>");
		}
		out.print("<td>Actions</td>");
		out.print("</tr>  </tfoot>");
		out.print(
				"""

										</table>
										</div>
										<script
										src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
										integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
										crossorigin="anonymous"></script>
						<div class="modal fade" id="editModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="modal-title fs-5" id="staticBackdropLabel">Modal title</h1>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        <form action="EditController" method="post">
													<div class="row">
														<div class="col-md-12">
															<label for='id' class='form-label'>Id</label>
															<input type='text' class='form-control' name='id' id='id' disabled>
															<input type='hidden' value='' id='id2' name='id'>
														</div>
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
															<input type='text' class='form-control' name='password' id='password'>
														</div>
														<div class="col-md-12">
															<label for='mobileNo' class='form-label'>MobileNo</label>
															<input type='tel' class='form-control' name='mobileNo' id='mobileNo'>
														</div>
														<div class="col-md-12 flex">
															<label for='gender' class='form-check-label'>Gender</label>
															<input type='radio' class='form-check radio' name='gender' value="Male" > Male
															<input type='radio' class='form-check radio' name='gender' value="Female"> Female
															<input type='radio' class='form-check radio' name='gender' value="Others" > Others
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
													</div>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
						        <button type="submit" class="btn btn-primary">edit</button>
						      </div>
							</form>
						    </div>
						  </div>
						</div>
						<script>

							function fillModal(e)
							{
							   const tr=e.parentNode.parentNode;
							   console.log(tr);
							   console.dir(tr);
							   const tdArr=tr.childNodes;
							   document.querySelector("#id").value=tdArr[0].textContent;
							   document.querySelector("#id2").value=tdArr[0].textContent;
							   document.querySelector("#fname").value=tdArr[1].textContent;
							   document.querySelector("#mobileNo").value=tdArr[2].textContent;
							   let genArr=document.querySelectorAll("input[name='gender']");
							   let gen=tdArr[3].textContent;
							   console.dir(genArr);
							   console.dir(gen);
							   for(let i=0;i<genArr.length;i++)
							   {
								   if(genArr[i].value.startsWith(gen))
								   {
										genArr[i].checked = true;
								   }
							   }
							   if(tdArr[4].textContent=='true')
									document.querySelector("#isMember").checked=true;

							   document.querySelector("#fee").value=tdArr[5].textContent;
							   document.querySelector("#joinedAt").value=tdArr[6].textContent;
							   document.querySelector("#email").value=tdArr[7].textContent;
							   document.querySelector("#password").value=tdArr[8].textContent;
							}

						</script>
								</body>
								</html>""");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
