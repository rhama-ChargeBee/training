package user;

import java.sql.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DbConnection {

    private static Connection con;
    
    public static byte[] encrypt(String s) throws Exception {
       byte[] key = "1q2w3e4r5t6y7u8i".getBytes("ASCII");
       byte[] data = s.getBytes();
       Cipher cipherAlgo = Cipher.getInstance("AES");
       SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
       cipherAlgo.init(Cipher.ENCRYPT_MODE, keySpec);
       byte[] encryptedData = cipherAlgo.doFinal(data);
       return encryptedData;
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/contacts_portal";
        con = DriverManager.getConnection(connectionUrl, "root", "");
    }

    public static ResultSet getUserDetails(int userId) throws SQLException {
        String sql = "select fname, lname, email from user_login where id=? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setInt(1, userId);
        return userRow.executeQuery();
    }
    
    public static PreparedStatement getUserDetails() throws SQLException {
        String sql = "select fname, lname, email from user_login where id=? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        return userRow;
    }

    public static ResultSet getUserLogin(String email) throws SQLException {
        String sql = "select id, email, password from user_login where email= ? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setString(1, email);
        return userRow.executeQuery();
    }

    public static void updateDetails(String fname, String lname, int user_id) throws SQLException {
        String sqlUpdate = "update user_login set fname=?, lname=? where id=?;";
        PreparedStatement setRow = con.prepareStatement(sqlUpdate);
        setRow.setString(1, fname);
        setRow.setString(2, lname);
        setRow.setInt(3, user_id);
        setRow.executeUpdate();
    }

    public static void deleteUser(int user_id) throws SQLException {
        String sqlDelLogin = "delete from user_login where id=?;";
        PreparedStatement delLogin = con.prepareStatement(sqlDelLogin);
        delLogin.setInt(1, user_id);
        delLogin.executeUpdate();
    }

    public static void insertUser(DetailsBean details) throws SQLException, Exception {
        String sqlInsertLogin = "insert into user_login(fname,lname, email, password) values(?,?,?,?);";
        PreparedStatement newRowLogin = con.prepareStatement(sqlInsertLogin);
        newRowLogin.setString(1, details.getFname());
        newRowLogin.setString(2, details.getLname());
        newRowLogin.setString(3, details.getEmail());
        newRowLogin.setBytes(4, encrypt(details.getPass()));
        newRowLogin.executeUpdate();
    }

    public static void insertNewContact(ContactsBean contact, int userId) throws SQLException {
        String sql = "insert into contact_addresses values(null,?,?,?,?,?,?,?,?,?);";
        PreparedStatement newRowContactAddresses = con.prepareStatement(sql);
        newRowContactAddresses.setString(1, contact.getAddressLine1());
        newRowContactAddresses.setString(2, contact.getAddressLine2());
        newRowContactAddresses.setString(3, contact.getCity());
        newRowContactAddresses.setString(4, contact.getState());
        newRowContactAddresses.setString(5, contact.getZip());
        newRowContactAddresses.setString(6, contact.getCountry());
        newRowContactAddresses.setInt(7, userId);
        newRowContactAddresses.setString(8, contact.getFname());
        newRowContactAddresses.setString(9, contact.getLname());
        newRowContactAddresses.executeUpdate();

        String addressIdSql = "select max(id) from contact_addresses;";
        PreparedStatement getId = con.prepareStatement(addressIdSql);
        ResultSet rs = getId.executeQuery();
        rs.next();
        int contactId = rs.getInt("max(id)");
        System.out.println("Mobile_value:" + contact.getMobile());

        insertPhone("Mobile", contact.getMobile(), userId, contactId);
        insertPhone("Work", contact.getWork(), userId, contactId);
        insertPhone("Home", contact.getHome(), userId, contactId);
    }

    private static void insertPhone(String phoneEnum, String phoneNumber, int userId, int contactId) throws SQLException {
        if (phoneNumber != null) {
            String sqlNumber = "insert into contact_numbers values(null,?,?,?,?);";
            PreparedStatement newRow = con.prepareStatement(sqlNumber);
            newRow.setString(1, phoneEnum);
            newRow.setString(2, phoneNumber);
            newRow.setInt(3, userId);
            newRow.setInt(4, contactId);
            newRow.executeUpdate();
        }
    }


    public static int userCount(String email) throws SQLException {
        String sql = "select count(id) as user from user_login where email=? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setString(1, email);
        ResultSet rs = userRow.executeQuery();
        rs.next();
        return rs.getInt("user");
    }

    public static ResultSet searchByName(int user_id, String name) throws SQLException {
        String sql = "select * from contact_addresses as t1 left outer join contact_numbers as t2 on t1.user_id=t2.user_id and t1.id=t2.contact_id where t1.user_id=? and (fname like ? or lname like ?) order by lname, fname, contact_id;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setInt(1, user_id);
        userRow.setString(2, "%" + name + "%");
        userRow.setString(3, "%" + name + "%");
        return userRow.executeQuery();
    }

    public static ResultSet searchByName(int user_id) throws SQLException {
        String sql = "select * from contact_addresses as t1 left outer join contact_numbers as t2 on t1.user_id=t2.user_id and t1.id=t2.contact_id where t1.user_id=? order by lname, fname, contact_id;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setInt(1, user_id);
        return userRow.executeQuery();
    }

    public static ResultSet searchByNumber(int user_id, String number) throws SQLException {
        String sql = "select * from contact_addresses as t1 left outer join contact_numbers as t2 on t1.user_id=t2.user_id and t1.id=t2.contact_id where t2.contact_id in (select distinct contact_id from contact_numbers where user_id= ? and phone_number like ?) order by lname, fname, contact_id;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setInt(1, user_id);
        userRow.setString(2, "%" + number + "%");
        return userRow.executeQuery();
    }

    public static ResultSet searchContact(int user_id, int contact_id) throws SQLException {
        String sql = "select * from contact_addresses as t1 left outer join contact_numbers as t2 on t1.user_id=t2.user_id and t1.id=t2.contact_id where t1.user_id=? and contact_id=?;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setInt(1, user_id);
        userRow.setInt(2, contact_id);
        return userRow.executeQuery();
    }

    public static void closeConnection() throws SQLException {
        con.close();
    }
}
