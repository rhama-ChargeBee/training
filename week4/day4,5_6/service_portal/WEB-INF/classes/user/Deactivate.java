package user;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Deactivate extends HttpServlet{
	private Connection con;
	private Map <String, String> userDetails= new HashMap <String, String>();
	private int id;
	private String printString="Account successfully Deactivated!!!";

	private int getId(){
		return id;
	}

	private void setId(){
		try{
			id=Integer.parseInt(userDetails.get("user_id"));
		}catch(Exception e){
			id=-1;
			printString="<a href=\"/login.html\">Login to</a> Deactivate Account!!!";
		}

	}

	private void openDb()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String connectionUrl = "jdbc:mysql://localhost/self_service_portal";
		con = DriverManager.getConnection(connectionUrl,"root", "");
	}

	private void removeFromDb()throws Exception{
		openDb();
		String sqlDelLogin="delete from user_login where id=?;";
		PreparedStatement delLogin=con.prepareStatement(sqlDelLogin);
		delLogin.setInt(1,getId());
		delLogin.executeUpdate();
		String sqlDelDetails="delete from user_details where id=?;";
		PreparedStatement delDetails=con.prepareStatement(sqlDelLogin);
		delDetails.setInt(1,getId());
		delDetails.executeUpdate();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		Cookie[] cookies=request.getCookies();
		for(Cookie cookie: cookies){
			userDetails.put(cookie.getName(), cookie.getValue());
			cookie.setMaxAge(0);
		}
		setId();
		if(id!=-1){
			try{
				removeFromDb();
			}catch(Exception e){
				System.err.println(e);
			}
		}
		request.setAttribute("printString", printString);
		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/deactivate.jsp");
        dispatcher.forward(request,response);

	}

	@Override  
    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {  
        doPost(req, resp);  
    }
}