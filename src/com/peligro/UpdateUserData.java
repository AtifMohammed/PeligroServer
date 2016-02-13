package com.peligro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateUserData
 */
public class UpdateUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		double lat=Double.parseDouble(request.getParameter("lat"));
		double lon=Double.parseDouble(request.getParameter("lon"));
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		String gender=request.getParameter("gender");
		String phone=request.getParameter("phone");
		String disease=request.getParameter("disease");
		String dob=request.getParameter("dob");
	
		
		Connection connection=null;
		PrintWriter out=null;
		try {
			out=response.getWriter();
			connection=ConnectionUtil.getconnection();
			Statement st=connection.createStatement();
			String query="update USERDATA set lat_home="+lat+", long_home="+lon+",name='"+name+"',password='"+password+"',email='"+email+"',gender='"+gender+"',disease='"
			+disease+"',dob='"+dob+"' where phone='"+phone+"'";
			int n=st.executeUpdate(query);
			
			if(n>0)
			{
				out.print("success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

}
