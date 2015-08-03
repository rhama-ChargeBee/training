package user;

import java.sql.*;

public class UserSignup{
	private int userid;
	private String fname="";
	private String lname;
	private String email;
	private String cemail;
	private String pass;
	private String cpass;
	Connection con;

	public void setUserid(int userid){
		this.userid=userid;
	}

	public void setFname(String fname){
		this.fname=fname;
	}

	public void setLname(String lname){
		this.lname=lname;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public void setCemail(String cemail){
		this.cemail=cemail;
	}
	public void setPass(String pass){
		this.pass=pass;
	}

	public void setCpass(String cpass){
		this.cpass=cpass;
	}

	public int getUserid(){
		return userid;
	}

	public String getFname(){
		return fname;
	}

	public String getLname(){
		return lname;
	}

	public String getEmail(){
		return email;
	}

	public String getCemail(){
		return cemail;
	}

	public String getPass(){
		return pass;
	}

	public String getCpass(){
		return cpass;
	}


	private void openDb()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String connectionUrl = "jdbc:mysql://localhost/self_service_portal";
		con = DriverManager.getConnection(connectionUrl,"root", "");
	}

	private void insertIntoTable()throws Exception{
		String sqlInsertLogin="insert into user_login values(null, ?,?);";
		PreparedStatement newRowLogin = con.prepareStatement(sqlInsertLogin);
		newRowLogin.setString(1,getEmail());
		newRowLogin.setString(2,getPass());
		newRowLogin.executeUpdate();

		String sqlQuery="select id from user_login where email like ?;";
		PreparedStatement getId= con.prepareStatement(sqlQuery);
		getId.setString(1,getEmail());
		ResultSet rs= getId.executeQuery();
		rs.next();
		setUserid(rs.getInt("id"));

		String sqlInsertDetails="insert into user_details(fname, lname, user_id) values(?,?,?);";
		PreparedStatement newRowDetails = con.prepareStatement(sqlInsertDetails);
		newRowDetails.setString(1,getFname());
		newRowDetails.setString(2,getLname());
		newRowDetails.setInt(3,getUserid());
		//newRowDetails.setInt(4,"");
		newRowDetails.executeUpdate();


	}

	public boolean getSignupConformation()throws Exception{
		openDb();
		String sql= "select count(id) as user from user_login where email like ? ;";
		PreparedStatement userRow = con.prepareStatement(sql);
		userRow.setString(1,getEmail());
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

	public static void main(String[] args)throws Exception {
		UserSignup obj= new UserSignup();
		obj.setEmail("abc");
		obj.setPass("pass");
		System.out.println(obj.getSignupConformation());
	}
}