package user;
import java.util.*;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SetDetails extends HttpServlet{
	private Connection con;
    private Map < String,String[]> formParameters=null;
    private String id;
    
    private void setId(String id){
        this.id=id;
    }

    private String getFname(){
    	return formParameters.get("fname")[0];
    }
    private String getLname(){
    	return formParameters.get("lname")[0];
    }
    private String getAddress(){
    	return formParameters.get("address_line1")[0];
    }
    private String getUserId(){
    	return id;
    }

    private HttpServletResponse setInCookies(HttpServletRequest request, HttpServletResponse response){
    	Cookie[] allCookies= request.getCookies();
    	for(Cookie cookie: allCookies){
    		switch(cookie.getName()){
    			case "fname":
    				cookie.setValue(getFname());
    				break;
    			case "lname":
    				cookie.setValue(getLname());
    				break;
    			case "address":
    				cookie.setValue(getAddress());
    				break;
                case "user_id":
                      setId(cookie.getValue());
    		}
    		response.addCookie(cookie);
    	}
    	return response;
    }

    private void openDb()throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/self_service_portal";
        con = DriverManager.getConnection(connectionUrl,"root", "");
    }

    private void changePropertiesInDb()throws Exception{
		openDb();
		System.out.println("log2......... "+getUserId());
		String sqlUpdate="update user_details set fname=?, lname=?, address=? where user_id=?;";
		PreparedStatement setRow= con.prepareStatement(sqlUpdate);
		setRow.setString(1, getFname());
		setRow.setString(2,getLname());
		setRow.setString(3,getAddress());
		setRow.setInt(4,Integer.parseInt(getUserId()));
		setRow.executeUpdate();
		con.close();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		formParameters=request.getParameterMap();
                response=setInCookies(request, response);
		try {
                    
                changePropertiesInDb();
            } catch (Exception e) {
                System.err.println(e);
            }

		
		RequestDispatcher dispatcher =getServletContext().getRequestDispatcher("/userdetails");
        dispatcher.forward(request,response);

	}
	@Override  
    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {  
        doPost(req, resp);  
    }
}