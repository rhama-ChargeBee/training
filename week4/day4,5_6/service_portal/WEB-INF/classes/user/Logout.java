package user;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Logout extends HttpServlet{

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		Cookie[] allCookies=request.getCookies();
		for(Cookie allCookie: allCookies){
			allCookie.setMaxAge(0);
			response.addCookie(allCookie);
		}
		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/logout.html");
        dispatcher.forward(request,response);

	}

	@Override  
    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {  
        doPost(req, resp);  
    }
}