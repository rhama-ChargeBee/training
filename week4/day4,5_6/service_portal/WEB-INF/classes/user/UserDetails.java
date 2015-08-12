package user;

import java.sql.*;
import java.io.*;
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
		DbConnection.connect();
		ResultSet rs= DbConnection.getUserDetails(getUserId());
		DetailsBean details=new DetailsBean();
		details.setInBean(rs);
		DbConnection.closeConnection();
		return details;
	}

	private HttpServletRequest setRequestAttribute(HttpServletRequest request, DetailsBean details){
		request.setAttribute("fname", details.getFname());
		request.setAttribute("lname", details.getLname());
		request.setAttribute("email", details.getEmail());
		request.setAttribute("address", details.getAddress());
		return request;
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		System.out.println("Do post of UserDetails\nPrinting the cookie names");
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
                    try {
                    	setUserId(session.getAttribute("user_id").toString());
                        request=setRequestAttribute(request, getDetails(allCookies));
                    } catch (Exception e) {
                        System.err.println(e);
                        e.printStackTrace();
                    }
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
    	}
	}
	/*
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		doPost(request,response);
	}*/
}