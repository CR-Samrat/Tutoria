package com.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Servlet implementation class AcceptStudent
 */
public class AcceptStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptStudent() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void addStd(String cId, String sId) {
    	
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	ServletOutputStream out = response.getOutputStream();
		HttpSession session = request.getSession();
		String uname=(String) session.getAttribute("uname");
		String update_id = request.getParameter("update_id");
		String update_subject = request.getParameter("update_subject");
		String cId = request.getParameter("batch");
		System.out.print(cId);
		String Query = "UPDATE tempapplication SET status = 1 WHERE suid = '"+update_id+"' AND tuid = '"+uname+"' AND subject = '"+update_subject+"'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			System.out.println("Check 1");
			boolean rs = ((java.sql.Statement) st).execute(Query);
			if(!rs) {
				try {
					String Query2 ="INSERT INTO stdclass VALUES ('"+cId+"','"+update_id+"')";
					System.out.println(Query2);
					Boolean rs1 = ((java.sql.Statement) st).execute(Query2);
					
					if(!rs1) {
						response.sendRedirect("applicationStatus.html");
					}else {
						out.print("student already applied");
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}else {
				out.print("Student have not applied for the subject");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
