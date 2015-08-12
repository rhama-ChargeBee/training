package user;

import java.sql.*;

public class DbConnection{
	private Connection con;

    public static byte[] encrypt(String s) throws Exception {
       byte[] key = "1q2w3e4r5t6y7u8i".getBytes("ASCII");
       byte[] data = s.getBytes();
       Cipher cipherAlgo = Cipher.getInstance("AES");
       SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
       cipherAlgo.init(Cipher.ENCRYPT_MODE, keySpec);
       byte[] encryptedData = cipherAlgo.doFinal(data);
       return encryptedData;
    }
    
	public DbConnection() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/self_service_portal";
        con = DriverManager.getConnection(connectionUrl,"root", "");
    }
    public ResultSet getUserDetails(int userId) throws SQLException{
    	String sql="select fname, lname, address, email from user_login join user_details on user_login.id = user_details.user_id where user_id=? ;";
		PreparedStatement userRow=con.prepareStatement(sql);
		userRow.setInt(1,userId);
		return userRow.executeQuery();
    }

    public ResultSet getUserLogin(String email) throws SQLException{
    	String sql= "select * from user_login where email= ? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setString(1,email);
        return userRow.executeQuery();
    }

    public void updateDetails(String fname, String lname, String address, int user_id) throws SQLException{
    	String sqlUpdate="update user_details set fname=?, lname=?, address=? where user_id=?;";
		PreparedStatement setRow= con.prepareStatement(sqlUpdate);
		setRow.setString(1, fname);
		setRow.setString(2, lname);
		setRow.setString(3,address);
		setRow.setInt(4,user_id);
		setRow.executeUpdate();
    }

    public void deleteUser(int user_id) throws SQLException{
    	String sqlDelLogin="delete from user_login where id=?;";
		PreparedStatement delLogin=con.prepareStatement(sqlDelLogin);
		delLogin.setInt(1,user_id);
		delLogin.executeUpdate();

		String sqlDelDetails="delete from user_details where id=?;";
		PreparedStatement delDetails=con.prepareStatement(sqlDelLogin);
		delDetails.setInt(1,user_id);
		delDetails.executeUpdate();
    }

    public void insertUser(DetailsBean details) throws SQLException{
    	String sqlInsertLogin="insert into user_login(email, password) values(?,?);";
        PreparedStatement newRowLogin = con.prepareStatement(sqlInsertLogin);
        newRowLogin.setString(1,details.getEmail());
        newRowLogin.setBytes(2,encrypt(details.getPass()));
        newRowLogin.executeUpdate();

        String sqlQuery="select id from user_login where email=?;";
        PreparedStatement getId= con.prepareStatement(sqlQuery);
        getId.setString(1,details.getEmail());
        ResultSet rs= getId.executeQuery();
        rs.next();
        int id=rs.getInt("id");

        String sqlInsertDetails="insert into user_details(fname, lname ,user_id) values(?,?,?);";
        PreparedStatement newRowDetails = con.prepareStatement(sqlInsertDetails);
        newRowDetails.setString(1,details.getFname());
        newRowDetails.setString(2,details.getLname());
        newRowDetails.setInt(3,id);
        newRowDetails.executeUpdate();
    }

    public int userCount(String email) throws SQLException{
    	String sql= "select count(id) as user from user_login where email like ? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setString(1,email);
        ResultSet rs= userRow.executeQuery();
        rs.next();
        return rs.getInt("user");
    }

    public void closeConnection() throws SQLException{
    	con.close();
    }
}