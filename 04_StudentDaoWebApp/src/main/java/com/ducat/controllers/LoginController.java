package com.ducat.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.ducat.dao.StudentDao;
import com.ducat.dao.impl.StudentDaoImpl;
import com.ducat.model.Student;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print(
				"""
						<!doctype html>
						<html lang="en">

						<head>
						    <meta charset="utf-8">
						    <meta name="viewport" content="width=device-width, initial-scale=1">
						    <title>Student ERP</title>
						    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
						        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
						</head>

						<body>
						    <nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme='dark'>
						        <div class="container-fluid">
						            <a class="navbar-brand" href="#">StudentERP</a>
						            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
						                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false"
						                aria-label="Toggle navigation">
						                <span class="navbar-toggler-icon"></span>
						            </button>
						            <div class="collapse navbar-collapse" id="navbarSupportedContent">
						                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
						                    <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Home</a></li>
						                    <li class="nav-item"><a class="nav-link" href="#">About US</a>
						                    </li>
						                    <li class="nav-item"><a class="nav-link" href="#">Contact
						                            US</a></li>
						                    <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" role="button"
						                            data-bs-toggle="dropdown" aria-expanded="false"> Services </a>
						                        <ul class="dropdown-menu">
						                            <li><a class="dropdown-item" href="#">Action</a></li>
						                            <li><a class="dropdown-item" href="#">Another action</a></li>
						                            <li>
						                                <hr class="dropdown-divider">
						                            </li>
						                            <li><a class="dropdown-item" href="#">Something else
						                                    here</a></li>
						                        </ul>
						                    </li>

						                </ul>
						                <div class='flex justify-content-between'>
						                    <a href="LoginController">
						                        <button class="btn btn-success">login</button>
						                    </a>
						                    <a href="RegisterController">
						                        <button class="btn btn-primary">register</button>
						                    </a>
						                </div>
						            </div>
						        </div>
						    </nav>

						    <h1 class="text-center mb-3 mt-3">Login Page</h1>
						    <div class="container mt-4">
						        <div class="row">
						            <div class="col-md-6"><img src="img/userAtDesktop.svg" alt="" width="100%"></div>
						            <div class="col-md-6">
						                <div class="container-fluid">
						                    <form action="LoginHandler" method="post">
						                        <div class="row">
						                            <div class="col-md-12">
						                                <label for="email" class="form-label">email</label>
						                                <input type="email" class="form-control" name="email">
						                            </div>
						                        </div>
						                        <div class="row">
						                            <div class="col-md-12">
						                                <label for="email" class="form-label">password</label>
						                                <input type="password" class="form-control" name="password">
						                            </div>
						                        </div>
						                        <div class="d-flex justify-content-center mt-2 mb-3">
						                            <button type="reset" class="btn btn-secondary">reset</button>
						                            <button type="submit" class="btn btn-primary ms-3">Login</button>
						                        </div>
						                    </form>
						                </div>
						            </div>
						        </div>
						    </div>
						    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
						        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
						        crossorigin="anonymous"></script>
						</body>

						</html>
									""");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
