package servlets;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;


public class UserSignup extends HttpServlet{
    private Connection con;
    private Map < String,String[]> formParameters=null;
    private boolean emailValidFlag=true;
    private boolean cemailValidFlag=true;
    private boolean emailMatchFlag=true;
    private boolean passMatchFlag=true;
    private boolean dbFlag= false;


    private void setCookies(HttpServletResponse response){
        setCookies(response);
        Cookie[] signupDetails= new Cookie[4];
        signupDetails[0]=new Cookie("fname",formParameters.get("fname")[0]);
        signupDetails[1]=new Cookie("lname",formParameters.get("lname")[0]);
        signupDetails[2]=new Cookie("email",formParameters.get("email")[0]);
        signupDetails[3]=new Cookie("pass",formParameters.get("pass")[0]);
        for(int i=0;i<signupDetails.length;i++){
            response.addCookie(signupDetails[i]);
        }
    }

    private void setEmailValidFlag(){
        String emailStr=formParameters.get("email")[0];
        if(!emailStr.contains("@") && !emailStr.contains(".") && (emailStr.lastIndexOf('.')-emailStr.indexOf('@') ) <1){
            emailValidFlag=false;
        }
    }

    private void setCemailValidFlag(){
        String cemailStr=formParameters.get("cemail")[0];
        if(!cemailStr.contains("@") && !cemailStr.contains(".") && (cemailStr.lastIndexOf('.')-cemailStr.indexOf('@') ) <1){
            cemailValidFlag=false;
        }
    }

    private void setEmailMatchFlag(){
        String emailStr=formParameters.get("email")[0];
        String cemailStr=formParameters.get("cemail")[0];
        if( !cemailStr.equals(emailStr) ){
            emailMatchFlag=false;
        }
    }

    private void setPassMatchFlag(){
        String passStr=formParameters.get("pass")[0];
        String cpassStr=formParameters.get("cpass")[0];
        if( !passStr.equals(cpassStr) ){
            passMatchFlag=false;
        }
    }

    private void openDb()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/self_service_portal";
        con = DriverManager.getConnection(connectionUrl,"root", "");
    }

    private void insertIntoTable()throws Exception{
        String sqlInsertLogin="insert into user_login values(null, ?,?);";
        PreparedStatement newRowLogin = con.prepareStatement(sqlInsertLogin);
        newRowLogin.setString(1,formParameters.get("email")[0]);
        newRowLogin.setString(2,formParameters.get("pass")[0]);
        newRowLogin.executeUpdate();

        String sqlQuery="select id from user_login where email like ?;";
        PreparedStatement getId= con.prepareStatement(sqlQuery);
        getId.setString(1,formParameters.get("email")[0]);
        ResultSet rs= getId.executeQuery();
        rs.next();
        String[] idStr={Integer.toString(rs.getInt("id"))};
        formParameters.put("id",idStr);

        String sqlInsertDetails="insert into user_details(fname, lname, user_id) values(?,?,?);";
        PreparedStatement newRowDetails = con.prepareStatement(sqlInsertDetails);
        newRowDetails.setString(1,formParameters.get("fname")[0]);
        newRowDetails.setString(2,formParameters.get("lname")[0]);
        newRowDetails.setInt(3,Integer.parseInt(formParameters.get("id")[0]) );
        //newRowDetails.setInt(4,"");
        newRowDetails.executeUpdate();
    }

    public boolean getSignupConformation()throws Exception{
        openDb();
        String sql= "select count(id) as user from user_login where email like ? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setString(1,formParameters.get("fname")[0]);
        ResultSet rs= userRow.executeQuery();
        boolean flag=false;
        rs.next();
        if(rs.getInt("user")==0 ){
            flag=true;
            insertIntoTable();
        }
        rs.close();
        con.close();
        return flag;
    }

    private void setFlags(HttpServletResponse response) throws Exception{
        setEmailValidFlag();
        setCemailValidFlag();
        setEmailMatchFlag();
        setPassMatchFlag();
        if(emailValidFlag && cemailValidFlag && emailMatchFlag && passMatchFlag) {
            dbFlag=getSignupConformation();
            if(dbFlag){
                response.sendRedirect("login.html");
                return;
            }
        }
    }

    private String[] setHtmlStrings(){
        String part1="<html> <head> <title>SignUp page</title> <link rel=\"stylesheet\" type=\"text/css\" href=\"custom.css\"> </head> <body> <br><br><br><br> <form method=\"post\" action=\"signup.jsp\"> <center> <table id=\"rcorners2\"> <thead> <tr> <th id=\"reg_to\" colspan=\"2\">Register to</th> </tr> <tr> <th id=\"space_portal\" colspan=\"2\">Space Portal</th> </tr> </thead> <tbody> <tr> <td><input type=\"text\" name=\"fname\" value=\"\" size=25 placeholder=\"First Name\" /></td> <td><input type=\"text\" name=\"lname\" value=\"\" size=25 placeholder=\"Last Name\" required/></td> </tr> <tr> <td><input type=\"text\" name=\"email\" value=\"\" size=25 placeholder=\"Email\" required /></td> <td><input type=\"text\" name=\"cemail\" value=\"\" size=25/ placeholder=\"Confirm Email\" required /></td> </tr> <tr> <td><input type=\"password\" name=\"pass\" value=\"\" size=25 placeholder=\"Password\" required /></td> <td><input type=\"password\" name=\"cpass\" value=\"\" size=25 placeholder=\"ConfirmPassword\" required  /></td> </tr> <tr> <td colspan=\"2\" align=\"center\"><input id=\"reg_button\" type=\"submit\" value=\"Create an Account\" /></td> </tr> <tr> <td id=\"login_ref\" colspan=\"2\" align=\"center\"><a href=\"login.html\">Have an account already?</a></td> </tr> </tbody> </table> </center> </form> <script> if(";
        String part2=" ){document.getElementByName(\"pass\")[0].className = \"changeRed\"; document.getElementByName(\"pass\")[0].placeholder = \"Passwords do Not Match!\"; document.getElementByName(\"pass\")[0].style.border-color= 'red'; document.getElementByName(\"cpass\")[0].className = \"changeRed\"; document.getElementByName(\"cpass\")[0].placeholder = \"Passwords do Not Match!\"; document.getElementByName(\"cpass\")[0].style.border-color= 'red'; } if(";
        String part3="){document.getElementByName(\"email\")[0].className = \"changeRed\"; document.getElementByName(\"email\")[0].placeholder = \"Enter Proper Email!!!\"; document.getElementByName(\"email\")[0].style.border-color= 'red'; } if(";
        String part4="){document.getElementByName(\"cemail\")[0].className = \"changeRed\"; document.getElementByName(\"cemail\")[0].placeholder = \"Enter Proper Email!!!\"; document.getElementByName(\"cemail\")[0].style.border-color= 'red'; } if(";
        String part5="){document.getElementByName(\"email\")[0].placeholder = \"Emails do Not Match!\"; document.getElementByName(\"cemail\")[0].placeholder = \"Emails do Not Match!\"; document.getElementByName(\"email\")[0].style.border-color= \'red\'; document.getElementByName(\"cemail\")[0].style.border-color= \'red\'; } </script> </body> </html>";
        return new String[]{part1,part2,part3,part4,part5};
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        formParameters=request.getParameterMap();
        HttpSession session = request.getSession();
        if(session.getAttribute("user_id")!=null){
            response.sendRedirect("###");
            return;
        }

        try {
            setFlags(response);
        } catch (Exception ex) {
            Logger.getLogger(UserSignup.class.getName()).log(Level.SEVERE, null, ex);
        }

        String[] htmlStrings=setHtmlStrings();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(htmlStrings[0]);
        out.println(!passMatchFlag);
        out.println(htmlStrings[1]);
        out.println(!emailValidFlag || !emailMatchFlag || !dbFlag);
        out.println(htmlStrings[2]);
        out.println(!cemailValidFlag || !emailMatchFlag || !dbFlag);
        out.println(htmlStrings[3]);
        out.println(!passMatchFlag);
        out.println(htmlStrings[4]);
    } 
}
