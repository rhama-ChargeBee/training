package user;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserDetails extends HttpServlet{
	private int userId;
	private String page;


	private void setUserId(String userId){
		this.userId=Integer.parseInt(userId);
	}
	private void setPage(String page){
		this.page=page;
	}

	private int getUserId(){
		return userId;
	}
	private String getPage(){
		return page;
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

	private DetailsBean getDetails(Cookie[] allCookies) throws ClassNotFoundException, SQLException, Exception{
		System.err.println("Entering GetDetails!!!!");
		setUserId( getCookie(allCookies, "user_id").getValue() ); 
		DbConnection newConnection= new DbConnection();
		ResultSet rs= newConnection.getUserDetails(getUserId());
		DetailsBean details=new DetailsBean();
		details.setInBean(rs);
		newConnection.closeConnection();
		return details;
	}

	private HttpServletRequest setRequestAttribute(HttpServletRequest request, DetailsBean details){
		request.setAttribute("fname", details.getFname());
		request.setAttribute("lname", details.getLname());
		request.setAttribute("email", details.getEmail());
		return request;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		System.out.println("Do get of UserDetails\nPrinting the cookie names");
		Cookie[] allCookies=request.getCookies();
		
		if(allCookies==null){
			System.err.println("Problem! Check cookie.");
		}
		else{
			try{
				HttpSession session= request.getSession(false);
				if(session==null){
					System.out.println("session false");
					response.sendRedirect("login.jsp");
					return;
				}else{
                   	setUserId(session.getAttribute("user_id").toString());
                    request=setRequestAttribute(request, getDetails(allCookies));
	            	System.err.println("Logged in Redirecting to jsp....");
	            	setPage(getCookie(allCookies, "page").getValue());
					if(getPage().equals("fromLogin")|| getPage().equals("fromDetailsEdit") ){
						RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/details.jsp");
	            		dispatcher.forward(request,response);
	        		}else{
	        			RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/detailsedit.jsp");
	            		dispatcher.forward(request,response);
	        		}
	        	}
	        }catch(NullPointerException e){
	        	response.sendRedirect("login.jsp");
	        }
	        catch(Exception e){
	        	System.out.println(e);
	        	e.printStackTrace();
	        }
    	}
	}
	/*
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		doPost(request,response);
	}*/
}