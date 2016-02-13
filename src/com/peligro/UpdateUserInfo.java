package com.peligro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
//import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import gvjava.org.json.JSONArray;
//import gvjava.org.json.JSONObject;

/**
 * Servlet implementation class SampleServlet
 */
public class UpdateUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UpdateUserInfo() {
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
		
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
	
		
		Connection connection=null;
		PrintWriter out=null;
		try {
			out=response.getWriter();
			connection=ConnectionUtil.getconnection();
			Statement st=connection.createStatement();
			String query="update USERDATA set NAME='"+name+"'," +
					"EMAIL='"+email+"', PASSWORD='"+password+"'where PHONE='"+phone+"'";
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
