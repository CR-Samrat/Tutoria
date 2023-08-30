package com.student;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Servlet implementation class Apply
 */
public class Apply extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Apply() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter pw = response.getWriter();
		System.out.println(request.getParameter("subject"));
		System.out.println(request.getParameter("teacherID"));
		System.out.println(session.getAttribute("uname"));
		String insertQuery = "insert into tempapplication values('"+session.getAttribute("uname")+"','"+request.getParameter("teacherID")+"','"+request.getParameter("subject")+"','0')";
		try {
			boolean rs;
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			rs = ((java.sql.Statement) st).execute(insertQuery);
			if(rs != true) {
				pw.println("Application Succesfull");
				response.sendRedirect("teacherSearch.html");
			}
		}catch(Exception e) {
			pw.println(e);
		}
		
	}

}
