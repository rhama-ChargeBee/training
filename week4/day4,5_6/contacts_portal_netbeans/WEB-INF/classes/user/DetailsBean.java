package user;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DetailsBean{
	private String fname;
	private String lname;
	private String email;
	private String cemail;
	private String pass;
	private String cpass;

	private void setFname(String fname){
		this.fname=fname;
	}
	private void setLname(String lname){
		this.lname=lname;
	}
	private void setEmail(String email){
		this.email=email;
	}
	private void setCemail(String cemail){
		this.cemail=cemail;
	}
	private void setPass(String pass){
		this.pass=pass;
	}
	private void setCpass(String cpass){
		this.cpass=cpass;
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

	public void setInBean(ResultSet rs) throws Exception{
		rs.next();
		setFname(rs.getString("fname"));
		setLname(rs.getString("lname"));
		setEmail(rs.getString("email"));
	}
	public void setInBean(HttpServletRequest request)throws Exception{
		setFname(request.getParameter("fname"));
		setLname(request.getParameter("lname"));
		setEmail(request.getParameter("email"));
		setCemail(request.getParameter("cemail"));
		setPass(request.getParameter("pass"));
		setCpass(request.getParameter("cpass"));
	}
}