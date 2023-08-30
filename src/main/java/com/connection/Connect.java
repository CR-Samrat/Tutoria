package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

/**
 * Servlet implementation class Connection
 */
public class Connect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connect() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void Connects() throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
		java.sql.Statement st = con.createStatement();
    }

}
