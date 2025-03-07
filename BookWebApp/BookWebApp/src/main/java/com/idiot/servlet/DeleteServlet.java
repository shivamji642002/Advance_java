package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/deleteurl")
public class DeleteServlet extends HttpServlet {
	private static final String query = "delete from bookdata where id=?";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// get Printwriter
		PrintWriter pw = res.getWriter();

		// set content type
		res.setContentType("text/html");
		// get the of record
		int id = Integer.parseInt(req.getParameter("id"));
		// load jdbc driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// generate the connection
		try (Connection con = DriverManager.getConnection("jdbc:mysql:///book", "root", "8081575712");
				PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, id);
			int count = ps.executeUpdate();
			if (count == 1) {
				pw.print("<h2>Delete Record Seccessfull</h2>");
			} else {
				pw.print("Record is not Delete");
			}
		} catch (SQLException se) {
			se.printStackTrace();
			pw.println("<h1>" + se.getMessage() + "</h1>");
		} catch (Exception e) {
			e.printStackTrace();
			pw.println("<h1>" + e.getMessage() + "</h1>");
		}
		pw.println("<a href='home.html'>Home</a>");
		pw.println("<br>");
		pw.println("<a href='bookList'>Book List</a>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
}
