package user;

import java.sql.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserLogin extends HttpServlet {
    private int id;
    private String formEmail;
    private String formPass;
    private String conformation="emailNotExists";

    private void setId(int id){
        this.id=id;
    }

    private void setFormEmail(String formEmail){
        this.formEmail=formEmail;
    }

    private void setFormPass(String formPass){
        this.formPass=formPass;
    }

    private int getId(){
        return id;
    }
    
    private String getFormEmail(){
        return formEmail;
    }

    private String getFormPass(){
        return formPass;
    }


    private void setCookie(HttpServletResponse response) throws Exception{
        Cookie userIdCookie=new Cookie("user_id",Integer.toString(getId()) );
        userIdCookie.setMaxAge(900);
        response.addCookie(userIdCookie);
    }

    public static byte[] decrypt(byte[] s) throws Exception {
       byte[] key = "1q2w3e4r5t6y7u8i".getBytes("ASCII");
       Cipher cipherAlgo = Cipher.getInstance("AES");
       SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
       cipherAlgo.init(Cipher.DECRYPT_MODE, keySpec);
       byte[] data = cipherAlgo.doFinal(s);
       return data;
    }

    private boolean getLoginConformation(HttpServletResponse response)throws Exception{
        DbConnection newConnection= new DbConnection();
        ResultSet rs= newConnection.getUserLogin(getFormEmail());

        boolean flag=false;
        conformation="emailNotExists";

        while(rs.next()){
            conformation="emailExists";
            if(rs.getString("password").equals(getFormPass()) ){
                flag=true;
                setId(rs.getInt("id"));
                setCookie(response);
            }else{
                conformation="passwordDoesNotMatch";
            }
        }
        rs.close();
        newConnection.closeConnection();
        return flag;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        System.out.println("Do get of UserLogin");
        setFormEmail(request.getParameter("email"));
        setFormPass(request.getParameter("pass"));

        try {
            HttpSession curSession=request.getSession();
            if(curSession.getAttribute("user_id")==null ){
                HttpSession newSession=request.getSession();
                if(getLoginConformation(response)){
                    newSession.setAttribute("user_id",getId());
                    response.addCookie(new Cookie("page", "fromLogin"));
                    System.out.println("Login confirmed.. redirecting...");
                    response.sendRedirect("userdetails");
                    //RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/userdetails");
                    //dispatcher.forward(request,response);
                    return;
                }
                else{
                    System.out.println("Login failure.. Try again...");
                    request.setAttribute("conformation", conformation);
                    RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request,response);
                }   
            }else{
                response.sendRedirect("userdetails");
            } 
        }catch (Exception e) {
            System.err.println("UserLogin Do get! "+e);
            e.printStackTrace();
        }
    }
}
    /*
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        System.out.println("Do get of UserLogin");
        HttpSession curSession=request.getSession(false);
        if(curSession==null){
            response.sendRedirect("login.html");
        }else{
            response.sendRedirect("userdetails");
        }
    } 
    */
