package user;

import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ContactsBean{
	private String fname;
	private String lname;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String work;
	private String mobile;
	private String home;

	private void setFname(String fname){
		this.fname=fname!=null?fname:"";
	}
	private void setLname(String lname){
		this.lname=lname!=null?lname:"";
	}
	private void setAddressLine1(String addressLine1){
		this.addressLine1=addressLine1!=null?addressLine1:"";
	}
	private void setAddressLine2(String addressLine2){
		this.addressLine2=addressLine2!=null?addressLine2:"";
	}
	private void setCity(String city){
		this.city=city!=null?city:"";
	}
	private void setState(String state){
		this.state=state!=null?state:"";
	}
	private void setZip(String zip){
		this.zip=zip!=null?zip:"";
	}
	private void setCountry(String country){
		this.country=country!=null?country:"";
	}
	private void setMobile(String mobile){
		this.mobile=mobile!=null?mobile:"";
	}
	private void setWork(String work){
		this.work=work!=null?work:"";
	}
	private void setHome(String home){
		this.home=home!=null?home:"";
	}

	public  String getFname(){
		return fname;
	}
	public  String getLname( ){
		return lname;
	}
	public  String getAddressLine1( ){
		return addressLine1;
	}
	public  String getAddressLine2( ){
		return addressLine2;
	}
	public  String getCity( ){
		return city;
	}
	public  String getState( ){
		return state;
	}
	public  String getZip( ){
		return zip;
	}
	public  String getCountry(){
		return country;
	}
	public String getMobile(){
		return mobile;
	}
	public String getWork(){
		return work;
	}
	public String getHome(){
		return home;
	}

	public void setContactInBean(HttpServletRequest request)throws Exception{
		setFname(request.getParameter("fname"));
		setLname(request.getParameter("lname"));
		setAddressLine1(request.getParameter("address_line1"));
		setAddressLine2(request.getParameter("address_line2"));
		setCity(request.getParameter("city"));
		setState(request.getParameter("state"));
		setZip(request.getParameter("zip"));
		setCountry(request.getParameter("country"));
		setMobile(request.getParameter("mobile"));
		setWork(request.getParameter("work"));
		setHome(request.getParameter("home"));
	}
	public void setContactInBean(ResultSet rs) throws SQLException{
		boolean flag=true;
		while(rs.next()){
			if(flag){
				setFname(rs.getString("fname"));
				setLname(rs.getString("lname"));
				setAddressLine1(rs.getString("address_line1"));
				setAddressLine2(rs.getString("address_line2"));
				setCity(rs.getString("city"));
				setZip(rs.getString("zip"));
				setCountry(rs.getString("country"));
				flag=false;
			}
			if(rs.getString("number_type").equals("Mobile")){
				setMobile(rs.getString("phone_number"));
			}
			else if(rs.getString("number_type").equals("Work")){
				setWork(rs.getString("phone_number"));
			}
			else{
				setHome(rs.getString("phone_number"));
			}
		}
	}
}