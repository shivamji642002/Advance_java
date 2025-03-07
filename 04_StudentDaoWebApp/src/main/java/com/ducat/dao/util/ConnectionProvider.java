package com.ducat.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider 
{
	private static Connection con=null;
	
	public static Connection getMySqlConnection()
	{
		final String URL="jdbc:mysql://localhost:3306/";
		final String DB="jdbc?createDatabaseIfNotExist=true";
		final String USERNAME="root";
		final String PASSWORD="1234";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(URL+DB,USERNAME,PASSWORD);
			System.out.println("connected to mysql succesfully");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("unable to load Driver "+e.getLocalizedMessage());
		}
		catch (SQLException e) 
		{
			System.out.println("Sql Error : "+e.getLocalizedMessage());
		}
		return con;
	}
	
	public static Connection getPostgreConnection()
	{
		final String URL="jdbc:postgresql://localhost:5432/";
		final String DB="mydb";
		final String USERNAME="postgres";
		final String PASSWORD="1234";
		try {
			Class.forName("org.postgresql.Driver");
			con=DriverManager.getConnection(URL+DB,USERNAME,PASSWORD);
			System.out.println("connected to postgres succesfully");
		} 
		catch (ClassNotFoundException e) 
		{
			System.out.println("unable to load Driver "+e.getLocalizedMessage());
		}
		catch (SQLException e) 
		{
			System.out.println("Sql Error : "+e.getLocalizedMessage());
		}
		return con;
	}


}
