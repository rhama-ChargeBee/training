package user;

import java.sql.*;

public class UserDetails{

	private int userId;
	private String fname;
	private String lname;
	private String address;
	private String email;
	private Connection con;

	public void setUserId(int userId){
		this.userId=userId;
	}

	public void setFname(String fname){
		this.fname=fname;
	}

	public void setLname(String lname){
		this.lname=lname;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public int getUserId(){
		return userId;
	}

	public String getFname(){
		if(fname==null){
			return "";
		}
		else{
			return fname;
		}
	}

	public String getLname(){
		return lname;
	}

	public String getAddress(){
		if(address==null){
			return "";
		}
		else{
			return address;
		}
	}

	public String getEmail(){
		return email;
	}

	private void openDb()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String connectionUrl = "jdbc:mysql://localhost/self_service_portal";
		con = DriverManager.getConnection(connectionUrl,"root", "");
	}

	private ResultSet getFromDb()throws Exception{
		String sqlQuery="select * from user_details where user_id=?;";
		PreparedStatement getRow= con.prepareStatement(sqlQuery);
		getRow.setInt(1,getUserId());
		return getRow.executeQuery();
	}

	public void setProperties()throws Exception{
		openDb();
		System.out.println("log1......... "+getUserId());
		ResultSet rs=getFromDb();
		rs.next();
		setFname(rs.getString("fname"));
		setLname(rs.getString("lname"));
		setAddress(rs.getString("address"));
		con.close();
	}
	public void changePropertiesInDb()throws Exception{
		openDb();
		System.out.println("log2......... "+getUserId());
		String sqlUpdate="update user_details set fname=?, lname=?, address=? where user_id=?;";
		PreparedStatement setRow= con.prepareStatement(sqlUpdate);
		setRow.setString(1, getFname());
		setRow.setString(2,getLname());
		setRow.setString(3,getAddress());
		setRow.setInt(4,getUserId());
		setRow.executeUpdate();
		con.close();
	}

	public void removeFromDb()throws Exception{
		openDb();
		String sqlDelLogin="delete from user_login where id=?;";
		PreparedStatement delLogin=con.prepareStatement(sqlDelLogin);
		delLogin.setInt(1,getUserId());
		delLogin.executeUpdate();
	}

}