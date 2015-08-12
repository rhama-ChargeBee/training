package user;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SetDetails extends HttpServlet{
    private int id;
    
    private void setId(HttpServletRequest request){
        String id=getCookie(request.getCookies(), "user_id").getValue();
        this.id=Integer.parseInt(id);
    }

    private int getId(){
    	return id;
    }

    private Cookie getCookie(Cookie[] allCookies, String cookieName){
        Cookie val=null;
        for(Cookie cookie: allCookies){
            if(cookie.getName().equals(cookieName)){
                val=cookie;
            }
        }
        return val;
    }
 

    private void changePropertiesInDb(HttpServletRequest request)throws Exception{
        DbConnection dbCon= new DbConnection();
        dbCon.updateDetails(request.getParameter("fname"), request.getParameter("lname"), request.getParameter("address_line1"), getId());
		dbCon.closeConnection();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        setId(request);
		try {    
                changePropertiesInDb(request);
            } catch (Exception e) {
                System.err.println(e);
                e.printStackTrace();

            }
        HttpSession session=request.getSession(false);
        if(session==null){
                response.sendRedirect("/login.html");
            }else{
        Cookie pageCookie=getCookie(request.getCookies(), "page");
        pageCookie.setValue("fromDetailsEdit");
        response.addCookie(pageCookie);
        response.sendRedirect("userdetails");
		//RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/userdetails");
        //dispatcher.forward(request,response);
    }

	}
}