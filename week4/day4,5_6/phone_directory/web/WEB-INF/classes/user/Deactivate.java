package user;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Deactivate extends HttpServlet{
	private Connection con;
	private Map <String, String> userDetails= new HashMap <String, String>();
	private int id=-1;

	private int getId(){
		return id;
	}

	private void setId(String id){
		this.id=Integer.parseInt(id);
	}

	private void removeFromDb()throws Exception{
		DbConnection.connect();
		DbConnection.deleteUser(getId());
		DbConnection.closeConnection();
	}

	@Override  
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {  
    	System.err.println("Deatctivate***************DoGet**************");
        Cookie[] cookies=request.getCookies();
		for(Cookie cookie: cookies){
			if(cookie.getName().equals("user_id")){
				setId(cookie.getValue());
			}
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		if(getId()!=-1){
			try{
				removeFromDb();
			}catch(Exception e){
				System.err.println(e);
				e.printStackTrace();
			}
		}
		try{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user_id") != null){
            session.invalidate();
            request.setAttribute("printString","Account deactivated successfully.");
        }else{
        	request.setAttribute("printString","Login to Deactivate");
        }
    	}catch(NullPointerException e){
    		request.setAttribute("printString","Login to Deactivate");
    	}
        RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/deactivate.jsp");
	    dispatcher.forward(request,response);
    }
}