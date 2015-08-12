package user;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;


public class UserSignup extends HttpServlet{
    private DetailsBean details=new DetailsBean();
    private boolean emailMatchFlag;
    private boolean passMatchFlag;
    private boolean dbFlag= false;

    private void setEmailMatchFlag(){
        String emailStr=details.getEmail();
        String cemailStr=details.getCemail();
        emailMatchFlag=false;
        if( cemailStr.equals(emailStr) ){
            emailMatchFlag=true;
        }
    }

    private void setPassMatchFlag(){
        String passStr=details.getPass();
        String cpassStr=details.getCpass();
        passMatchFlag=false;
        if( passStr.equals(cpassStr) ){
            passMatchFlag=true;
        }
    }

    public boolean getSignupConformation()throws Exception{
        int users= DbConnection.userCount(details.getEmail());
        boolean flag=false;
        if(users==0 ){
            flag=true;
            DbConnection.insertUser(details);
        }
        DbConnection.closeConnection();
        return flag;
    }

    private void setFlags()throws Exception{
        setEmailMatchFlag();
        setPassMatchFlag();
        if(emailMatchFlag && passMatchFlag) {
            dbFlag=getSignupConformation();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        try{
            HttpSession curSession=request.getSession(false);
            if(curSession.getAttribute("user_id")==null){
                try {
                    DbConnection.connect();
                    details.setInBean(request);
                    setFlags();
                    if(dbFlag){
                        response.sendRedirect("login.jsp");
                        return;
                    }else{
                        SignupFlags flags= new SignupFlags(!passMatchFlag,!emailMatchFlag, !dbFlag && passMatchFlag && emailMatchFlag);
                        request.setAttribute("flags", flags);
                        RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/signup.jsp");
                        dispatcher.forward(request,response);
                    }
                } catch (Exception e) {
                    System.err.println(e);
                    e.printStackTrace();
                }
            } else{
                response.sendRedirect("userdetails");
            }
        }catch(NullPointerException e){
            response.sendRedirect("signup.jsp");
        }      
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        doPost(request, response);
 
    } 
}
