package user;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserLogin extends HttpServlet {
	private Connection con;
    private Map < String,String[]> formParameters=null;
    private int id;
    private String fname;
    private String lname;
    private String address;
    private String conformation="emailNotExists";

	private void setFname(String fname){
    	this.fname=fname;
    }
    private void setLname(String lname){
    	this.lname=lname;
    }
    private void setAddress(String address){
    	if(address!=null){
    		this.address=address;
    	}
    	else{
    		this.address="";
    	}
    }
    private void setId(int id){
    	this.id=id;
    }

    private String getFname(){
    	return fname;
    }
    private String getLname(){
    	return lname;
    }
    private String getAddress(){
    	return address;
    }
    private int getId(){
    	return id;
    }
    
    private String getEmail(){
    	return formParameters.get("email")[0];
    }

    private String getPass(){
    	return formParameters.get("pass")[0];
    }

    private void openDb()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/self_service_portal";
        con = DriverManager.getConnection(connectionUrl,"root", "");
    }


	private void setUserDetails() throws Exception{
		openDb();
		String sql="select * from user_details where user_id=? ;";
		PreparedStatement userRow=con.prepareStatement(sql);
		userRow.setInt(1,getId());
		ResultSet rs=userRow.executeQuery();
		while(rs.next()){
			setFname(rs.getString("fname"));
			setLname(rs.getString("lname"));
			setAddress(rs.getString("address"));
		}
		rs.close();
		con.close();

	}

	private void setCookies(HttpServletResponse response) throws Exception{
        Cookie[] signupDetails= new Cookie[5];
        setUserDetails();
        signupDetails[0]=new Cookie("user_id",Integer.toString(getId()) );
        signupDetails[1]=new Cookie("fname",getFname());
        signupDetails[2]=new Cookie("lname",getLname());
        signupDetails[3]=new Cookie("email",getEmail());
        signupDetails[4]=new Cookie("address",getAddress());
        System.err.println(signupDetails[0].getName());
        System.err.println(signupDetails[1].getValue());
        for(int i=0;i<signupDetails.length;i++){
            System.err.println(signupDetails[i].getValue());
            signupDetails[i].setMaxAge(900);
            response.addCookie(signupDetails[i]);
        }
    }

    private boolean getLoginConformation(HttpServletResponse response)throws Exception{
        openDb();
        String sql= "select * from user_login where email like ? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setString(1,getEmail());
        ResultSet rs= userRow.executeQuery();
        boolean flag=false;
        conformation="emailNotExists";
        while(rs.next()){
            conformation="emailExists";
            if(rs.getString("password").equals(getPass()) ){
                flag=true;
                setId(rs.getInt("id"));
                setCookies(response);
            }else{
                conformation="passwordDoesNotMatch";
            }
        }
        rs.close();
        con.close();
        return flag;
    }

    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
    	formParameters=request.getParameterMap();
        HttpSession session = request.getSession();
        try {
                if(getLoginConformation(response)){
                    RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/userdetails");
                    dispatcher.forward(request,response);
                }
                else{
                    request.setAttribute("conformation", conformation);
                    RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request,response);
                }   
            } catch (Exception ex) {
                Logger.getLogger(UserLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override  
    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {  
        doPost(req, resp);  
    } 

}
