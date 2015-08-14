package user;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
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
        request.setAttribute("table",resultSetToMap(DbConnection.searchByName(getUserId())));
        RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/contacts.jsp");
        dispatcher.forward(request,response);
    }
    private HashMap<String, ArrayList <String>> resultSetToMap(ResultSet rs) throws SQLException{
        HashMap<String, ArrayList <String>> result= new HashMap<>();
        String[] keys=new String[] {"contact_id","fname", "lname", "address_line1", "address_line2", "city", "state", "zip", "country" ,"number_type", "phone_number"};
        for (String key : keys) {
            result.put(key, new ArrayList <String>());
        }
        while(rs.next()){
            for (String key : keys) {
                if(key.equals("contact_id")){
                    result.get(key).add(Integer.toString(rs.getInt(key)));
                }else{
                    result.get(key).add(rs.getString(key));
                }
            }
        }
        return result;
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
            switch (page) {
                case "fromDetails":
                    showAllContacts(request,response);
                    break;
                case "fromNewContacts":
                    ContactsBean contact=new ContactsBean();
                    contact.setContactInBean(request);
                    DbConnection.insertNewContact(contact, getUserId());
                    showAllContacts(request,response);
                    break;
                default:
                    if(request.getParameter("searchbox")==null){
                        System.out.println("searchbox == null");
                        request.setAttribute("table",resultSetToMap(DbConnection.searchByName(getUserId())));
                    }
                    else if(request.getParameter("option").equals("name")){
                        System.out.println("searchbox != null and option == name ");
                        request.setAttribute("table",resultSetToMap(DbConnection.searchByName(getUserId(), request.getParameter("searchbox"))));
                        
                    }
                    else{
                        System.out.println("searchbox != null and option == number ");
                        request.setAttribute("table",resultSetToMap(DbConnection.searchByNumber(getUserId(), request.getParameter("searchbox"))));
                        
                    }       RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/contacts.jsp");
                    dispatcher.forward(request,response);
                    break;
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
        }finally{
            try {
                DbConnection.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UserContacts.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        }finally{
            try {
                DbConnection.closeConnection();
            } catch (SQLException ex) {
                Logger.getLogger(UserContacts.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
