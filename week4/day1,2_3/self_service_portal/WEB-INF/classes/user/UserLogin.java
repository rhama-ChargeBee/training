package user;

import java.sql.*;

public class UserLogin{
	private int id;
	private String email;
	private String pass;
	Connection con;

	public void setId(int id){
		this.id=id;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public void setPass(String pass){
		this.pass=pass;
	}

	public int getId(){
		return id;
	}

	public String getEmail(){
		return email;
	}

	public String getPass(){
		return pass;
	}

	private void openDb()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		String connectionUrl = "jdbc:mysql://localhost/self_service_portal";
		con = DriverManager.getConnection(connectionUrl,"root", "");
	}

	public boolean getLoginConformation()throws Exception{
		openDb();
		String sql= "select * from user_login where email like ? ;";
		PreparedStatement userRow = con.prepareStatement(sql);
		userRow.setString(1,getEmail());
		ResultSet rs= userRow.executeQuery();
		boolean flag=false;
		while(rs.next()){
			if(rs.getString("password").equals(getPass()) ){
				flag=true;
				setId(rs.getInt("id"));
			}
		}
		rs.close();
		con.close();
		return flag;
	}


}