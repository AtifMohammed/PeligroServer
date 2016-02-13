package com.peligro;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Servlet implementation class AlertClass
 */
public class AlertClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String API_KEY="AIzaSyBBSjqLqGBKeCr-xv3NH87gvfYqTtQX7MA";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlertClass() {
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
		String[] string, ph;
		
		try {
			
			//retrieving data of people around victim
			out=response.getWriter();
			connection=ConnectionUtil.getconnection();
			Statement st=connection.createStatement();
			String querySender="select LAT_CUR, LONG_CUR, NAME from USERDATA where phone='"+phone+"'";
			ResultSet rs=st.executeQuery(querySender);
			rs.next();
			double lat=rs.getDouble("lat_cur");
			double lon=rs.getDouble("long_cur");
			System.out.println("lat = "+lat+" long= "+lon);
			String name=rs.getString("NAME");
			String queryHelper1="select REGKEY, phone from USERDATA where LAT_CUR < "+lat+"+0.001";
			String queryHelper2="select REGKEY, phone from USERDATA where LAT_CUR > "+lat+"-0.001";
			String queryHelper3="select REGKEY, phone from USERDATA where LONG_CUR < "+lon+"+0.001";
			String queryHelper4="select REGKEY, phone from USERDATA where LAT_CUR > "+lat+"-0.001";
			String queryHelper=queryHelper1+" Intersect "+queryHelper2+" Intersect "+
					queryHelper3+" Intersect "+queryHelper4;
		
			ResultSet rs1=st.executeQuery(queryHelper);
			//JSONArray arr=new JSONArray();
			string=new String[100];
			ph=new String[100];
			int i=0;
			while(rs1.next()&&i<100){
			string[i]=rs1.getString("REGKEY");
			ph[i++]=rs1.getString("phone");
			System.out.println(string[i-1]+" "+ph[i-1]);
			}
                
			for(int j=0; j<i; j++){
				//updating the record
				try{
				if(!phone.equals(ph[j])){
				String query2="update userdata set victim='"+phone+"' where phone='"+ph[j]+"'";
				st.executeUpdate(query2);}}catch(Exception e){continue;}

			}

          
          
            	 // Prepare JSON containing the GCM message content. What to send and where to send.
                
             // Where to send GCM message.
                for(int j=0; j<i; j++){
                	
                	  // Create connection to send GCM Message request.
                    URL url = new URL("https://android.googleapis.com/gcm/send");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Authorization", "key="+API_KEY);
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    
                	JSONObject jGcmData = new JSONObject();
                    JSONObject jData = new JSONObject();
                jGcmData.put("to",string[j]);
                jData.put("message",name+" needs your help ");
                // What to send in GCM message.
                jGcmData.put("data", jData);

           
            // Send GCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());
           
            // Read GCM response.
            InputStream inputStream = conn.getInputStream();
            String resp = IOUtils.toString(inputStream);
            System.out.println(resp);
                }
            System.out.println("Check your device/emulator for notification or logcat for " +
                    "confirmation of the receipt of the GCM message.");
            out.print("success");
        } catch (Exception e) {
            System.out.println("Unable to send GCM message.");
            System.out.println("Please ensure that API_KEY has been replaced by the server " +
                    "API key, and that the device's registration token is correct (if specified).");
            System.out.println(phone);
            e.printStackTrace();
        }
	}

}
