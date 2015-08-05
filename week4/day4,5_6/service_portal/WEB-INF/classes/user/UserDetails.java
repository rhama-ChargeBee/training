package user;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserDetails extends HttpServlet{
	private Map <String, String> userDetails= new HashMap <String, String>();

	
	private String getFname(){
		return userDetails.get("fname");
	}
	private String getLname(){
		return userDetails.get("lname");
	}
	private String getAddress(){
		try{
		if(userDetails.get("address").equals("")){
			return "-- NA --";
		}
		else{
			return userDetails.get("address");
		}
		}catch(NullPointerException e){
			return "-- NA --";
		}
	}
	private String getEmail(){
		return userDetails.get("email");
	}

	private void setDetails(Cookie[] allCookies){
		System.err.println("Entering SetDetails!!!!");
		for (Cookie allCookie : allCookies) {
				System.err.println("Entering loop!!!!");

				System.err.println("CookieName: "+allCookie.getName());
				System.err.println("CookieValue: "+allCookie.getValue());
				if(allCookie.getValue()==null){
					userDetails.put(allCookie.getName(), "");
				}else{
	                userDetails.put(allCookie.getName(), allCookie.getValue());
				}
            }
	}

	private HttpServletRequest setRequestAttribute(HttpServletRequest request){
		request.setAttribute("fname", getFname());
		request.setAttribute("lname", getLname());
		request.setAttribute("email", getEmail());
		request.setAttribute("address", getAddress());
		return request;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		Cookie[] allCookies=request.getCookies();
		if(allCookies==null){
			System.err.println("Problem! Check cookie.");
		}
		else{
			setDetails(allCookies);
			request=setRequestAttribute(request);
			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/details.jsp");
            dispatcher.forward(request,response);
        }
	}

	@Override  
    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {  
        doPost(req, resp);  
    }
}