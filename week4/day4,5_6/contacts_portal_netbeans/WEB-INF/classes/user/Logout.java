package user;

import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Logout extends HttpServlet{

	@Override  
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {  
    	System.err.println("Logout***************DoGet**************");
         Cookie[] allCookies=request.getCookies();
		for(Cookie allCookie: allCookies){
			allCookie.setMaxAge(0);
			response.addCookie(allCookie);
		}
		HttpSession session = request.getSession(false);
		if(session != null){
            session.invalidate();
        }
		response.sendRedirect("logout.jsp");
    }
}