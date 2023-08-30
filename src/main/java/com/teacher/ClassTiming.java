package com.teacher;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Servlet implementation class ClassTiming
 */
public class ClassTiming extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassTiming() {
        super();
        /* TODO Auto-generated constructor stub*/
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] weekdays=request.getParameterValues("weekday");
		String[] start = new String[7];
		String[] end = new String[7];
		System.out.println(request.getParameter("cId"));
		int i = 0;
		for(String s:weekdays) {
			String st="s"+s+"Time";
			start[i]=request.getParameter(st);
			String et = "e"+s+"Time";
			end[i]=request.getParameter(et);
			i++;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tutoria","root","1San@321#1704");
			java.sql.Statement st = con.createStatement();
			boolean rs=true;
			for(i=0;i<weekdays.length;i++) {
				String Query = "Insert into classtiming values("+request.getParameter("cId")+",'"+weekdays[i]+"','"+start[i].toString()+"','"+end[i].toString()+"')";
				System.out.println(Query);
				rs = ((java.sql.Statement) st).execute(Query);
			}
			if(!rs) {
				String Query = "UPDATE classdetails SET timeSet = '1' WHERE cId = "+request.getParameter("cId")+"";
				rs = ((java.sql.Statement) st).execute(Query);
				if(!rs) {
					response.sendRedirect("classDetails.html");
				}
			}	
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
