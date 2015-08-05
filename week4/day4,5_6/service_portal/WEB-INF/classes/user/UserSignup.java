package user;

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
    private boolean emailMatchFlag;
    private boolean passMatchFlag;
    private boolean dbFlag= false;
    private int id;

    private void setId(int id){
        this.id=id;
    }

    private int getId(){
        return id;
    }


    private void setEmailMatchFlag(){
        String emailStr=formParameters.get("email")[0];
        String cemailStr=formParameters.get("cemail")[0];
        emailMatchFlag=false;
        if( cemailStr.equals(emailStr) ){
            emailMatchFlag=true;
        }
    }

    private void setPassMatchFlag(){
        String passStr=formParameters.get("pass")[0];
        String cpassStr=formParameters.get("cpass")[0];
        passMatchFlag=false;
        if( passStr.equals(cpassStr) ){
            passMatchFlag=true;
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
        setId(rs.getInt("id"));

        String sqlInsertDetails="insert into user_details(fname, lname, user_id) values(?,?,?);";
        PreparedStatement newRowDetails = con.prepareStatement(sqlInsertDetails);
        newRowDetails.setString(1,formParameters.get("fname")[0]);
        newRowDetails.setString(2,formParameters.get("lname")[0]);
        newRowDetails.setInt(3,getId() );
        //newRowDetails.setInt(4,"");
        newRowDetails.executeUpdate();
    }

    public boolean getSignupConformation()throws Exception{
        openDb();
        String sql= "select count(id) as user from user_login where email like ? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setString(1,formParameters.get("email")[0]);
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

    private void setFlags()throws Exception{
        setEmailMatchFlag();
        setPassMatchFlag();
        if(emailMatchFlag && passMatchFlag) {
            dbFlag=getSignupConformation();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
        formParameters=request.getParameterMap();
        
        try {
            setFlags();
            if(dbFlag){
                response.sendRedirect("login.html");
                return;
            }else{
                SignupFlags flags= new SignupFlags(!passMatchFlag,!emailMatchFlag, !dbFlag);
                request.setAttribute("flags", flags);
                RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/signup.jsp");
                dispatcher.forward(request,response);
            }
        } catch (Exception ex) {
            Logger.getLogger(UserSignup.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    } 
    @Override  
    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {  
        doPost(req, resp);  
    } 
}
