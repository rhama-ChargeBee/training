package user;

import java.io.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.*;
import javax.servlet.http.*;

public class UserContacts extends HttpServlet{
    private int userId;

    private void setUserId(String userId){
        this.userId=Integer.parseInt(userId);
    }

    private int getUserId(){
        return userId;
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
        
    private void showAllContacts(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        request.setAttribute("table",DbConnection.searchByName(getUserId()));
        RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/contacts.jsp");
        dispatcher.forward(request,response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        Cookie[] cookies=request.getCookies();
        HttpSession session= request.getSession(false);
        try{
        if(session.getAttribute("user_id")!=null){
        setUserId(getCookie(cookies, "user_id").getValue());
        String page=getCookie(cookies, "user_id").getValue();
        DbConnection.connect();
        if(page.equals("fromDetails") ){
            showAllContacts(request,response);
            
        }else if( page.equals("fromNewContacts") ){
                        ContactsBean contact=new ContactsBean();
                        contact.setContactInBean(request);
                        DbConnection.insertNewContact(contact, getUserId());
                        showAllContacts(request,response);
        }
        else{
            
            if(request.getParameter("searchbox")==null){
                System.out.println("searchbox == null");
                            request.setAttribute("table",DbConnection.searchByName(getUserId()));
            }
            else if(request.getParameter("option").equals("name")){
                System.out.println("searchbox != null and option == name ");
                            request.setAttribute("table",DbConnection.searchByName(getUserId(), request.getParameter("searchbox")));

            }
            else{
                System.out.println("searchbox != null and option == number ");
                            request.setAttribute("table",DbConnection.searchByNumber(getUserId(), request.getParameter("searchbox")));

            }
            RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/contacts.jsp");
            dispatcher.forward(request,response);
        }
        }
         else{
            response.sendRedirect("login.jsp");
        }
        }catch(NullPointerException e){
            response.sendRedirect("login.jsp");
        }catch(SQLException e){
                Logger.getLogger(UserContacts.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception e) {
                Logger.getLogger(UserContacts.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        Cookie[] cookies=request.getCookies();
        HttpSession session= request.getSession(false);
        
        try{
            DbConnection.connect();
        if(session.getAttribute("user_id")!=null){
            setUserId(getCookie(cookies, "user_id").getValue());
            ContactsBean contact=new ContactsBean();
                        contact.setContactInBean(request);
                        DbConnection.insertNewContact(contact, getUserId());
                        showAllContacts(request,response);
        }else{
            response.sendRedirect("login.jsp");
        }
        }catch(NullPointerException e){
            response.sendRedirect("login.jsp");
        }catch(SQLException e){
                System.out.println(e);
                e.printStackTrace();
        } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
        }
    }
}
