package user;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.http.*;

public class UserContacts extends HttpServlet{
	private int userId;
	private DbConnection dbCon;

    public UserContacts() throws ClassNotFoundException, SQLException {
        this.dbCon = new DbConnection();
    }

	private void setUserId(String userId){
		this.userId=Integer.parseInt(userId);
	}

	private int getUserId(){
		return userId;
	}

	private void showAllContacts(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
		request.setAttribute("table",dbCon.searchByName(getUserId()));
    	RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/contacts.jsp");
        dispatcher.forward(request,response);
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

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
    	Cookie[] cookies=request.getCookies();
    	HttpSession session= request.getSession(false);
    	setUserId(getCookie(cookies, "user_id").getValue());

    	if(getCookie(cookies, "page").getValue().equals("fromDetails") ){
                    try {
                        showAllContacts(request,response);
                    } catch (SQLException ex) {
                        Logger.getLogger(UserContacts.class.getName()).log(Level.SEVERE, null, ex);
                    }
    		
    	}
    	else{
    		if(request.getParameter("searchbox")==null){
                System.out.println("searchbox == null");
                        try {
                            request.setAttribute("table",dbCon.searchByName(getUserId()));
                        } catch (SQLException ex) {
                            Logger.getLogger(UserContacts.class.getName()).log(Level.SEVERE, null, ex);
                        }
    		}
    		else if(request.getParameter("option").equals("name")){
                System.out.println("searchbox != null and option == name ");
                        try {

                            request.setAttribute("table",dbCon.searchByName(getUserId(), request.getParameter("searchbox")));
                        } catch (SQLException ex) {
                            Logger.getLogger(UserContacts.class.getName()).log(Level.SEVERE, null, ex);
                        }
    		}
    		else{
                System.out.println("searchbox != null and option == number ");
                        try {
                            request.setAttribute("table",dbCon.searchByNumber(getUserId(), request.getParameter("searchbox")));
                        } catch (SQLException ex) {
                            Logger.getLogger(UserContacts.class.getName()).log(Level.SEVERE, null, ex);
                        }
    		}
    		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/contacts.jsp");
            dispatcher.forward(request,response);
    	}
    	/*
            try {
                //dbCon.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UserContacts.class.getName()).log(Level.SEVERE, null, ex);
            }
            */
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        Cookie[] cookies=request.getCookies();
        HttpSession session= request.getSession(false);
        
        try{
            if(session.getAttribute("user_id")!=null){
                String page=(String)session.getAttribute("page");
                setUserId(getCookie(cookies, "user_id").getValue());
                
                if( getCookie(cookies, "page").getValue().equals("fromNewContacts") ){
                    try {
                        ContactsBean contact=new ContactsBean();
                        contact.setContactInBean(request);
                        dbCon.insertNewContact(contact, getUserId());
                        showAllContacts(request,response);
                    } catch (Exception ex) {
                        Logger.getLogger(UserContacts.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }else{
                response.sendRedirect("login.jsp");
                return;
            }
        }catch(NullPointerException e){
            response.sendRedirect("login.jsp");
            return;
        }
    }
}
