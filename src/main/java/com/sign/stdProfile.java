package com.sign;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@WebServlet("/stdProfile")
public class stdProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public stdProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void upd(String username,HttpServletResponse response) throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
		java.sql.Statement st = con.createStatement();
		try {
			int rp = st.executeUpdate("UPDATE students SET isProfile = '1' WHERE uname = '"+username+"'");
			if(rp==1) {
				response.sendRedirect("studentDashboard.html");
			}
		}catch(Exception e) {
			System.out.println(e);
		}
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		isProfile isprof = new isProfile();
		isprof.isprofile(request, response);
		stdProfile tch = new stdProfile();
		HttpSession session = request.getSession();
		boolean isProfile = true;//(Boolean) session.getAttribute("isProfile");
		if(isProfile==true) {
		}else {
			String username = (String) session.getAttribute("uname");
			String fname = request.getParameter("fname");
			String lname = request.getParameter("lname");
			String dob = request.getParameter("dob");
			String gender = request.getParameter("gender");
			String classes = request.getParameter("Class");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String country = request.getParameter("country");
			String pin = request.getParameter("pin");
			String insertQuery = "insert into studentsprofile values('" + username + "','" + fname + "','" + lname +"','"+ dob+ "','"+gender+"','"+classes+"','"+address+"','"+city+"','"+state+"','"+country+"','"+pin+"')";
			boolean rs;
			try {
				//Sql query to be executed
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
				java.sql.Statement st = con.createStatement();
					try {
						rs = ((java.sql.Statement) st).execute(insertQuery);
						if(rs) {
							
						}else {
							System.out.print("Inserted Data Succesfully");
							con.close();
							tch.upd(username,response);
						}
					}catch(Exception e) {
						System.out.println(e);
					}
			}catch(Exception e) {
				
			}
		}
	}
}
