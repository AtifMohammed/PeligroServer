package com.peligro;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AlertMap
 */
public class AlertMap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlertMap() {
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
		
		String phone=request.getParameter("phone");
		Connection connection=null;
		PrintWriter out=null;
		double lat, lon;
		out=response.getWriter();
		out.println("<H1>Please wait while we transfer you the location of victim</H1>");
		
try {
			
			//retrieving data of people around victim
			connection=ConnectionUtil.getconnection();
			Statement st=connection.createStatement();
			//some trouble here
			//rectify the query
			String querySender="select LAT_CUR, LONG_CUR, phone from USERDATA where phone=(select victim from" +
					" userdata where phone='"+phone+"')";
			ResultSet rs=st.executeQuery(querySender);
			rs.next();
			lat=rs.getDouble("lat_cur");
			lon=rs.getDouble("long_cur");
			String s=rs.getString("phone");
			if(s.length()>0){
			String url="http://192.168.14.245:9090/PeligroServer/alertmap.jsp?lats="+lat+"&lons="+lon+"&latr="+request.getParameter("lat")+
					"&lonr="+request.getParameter("lon");
			String query2="update userdata set victim=null where victim='"+s+"'";
			st.executeUpdate(query2);
			response.sendRedirect(url);
			}else{
			out.println("<h3>Victim might be rescued</h3><br><p>we appreciate your support</p>");
			}
}catch(Exception e){e.printStackTrace();
out.println("<h3>Victim might be rescued</h3><br><p>we appreciate your support</p>");}
		
	}

}
