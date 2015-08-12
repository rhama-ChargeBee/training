package user;

import java.sql.*;

public class DbConnection {

    private Connection con;

    public DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/contacts_portal";
        con = DriverManager.getConnection(connectionUrl, "root", "");
    }

    public ResultSet getUserDetails(int userId) throws SQLException {
        String sql = "select fname, lname, email from user_login where id=? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setInt(1, userId);
        return userRow.executeQuery();
    }
    
    public PreparedStatement getUserDetails() throws SQLException {
        String sql = "select fname, lname, email from user_login where id=? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        return userRow;
    }

    public ResultSet getUserLogin(String email) throws SQLException {
        String sql = "select id, email, password from user_login where email= ? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setString(1, email);
        return userRow.executeQuery();
    }

    public void updateDetails(String fname, String lname, int user_id) throws SQLException {
        String sqlUpdate = "update user_login set fname=?, lname=? where id=?;";
        PreparedStatement setRow = con.prepareStatement(sqlUpdate);
        setRow.setString(1, fname);
        setRow.setString(2, lname);
        setRow.setInt(3, user_id);
        setRow.executeUpdate();
    }

    public void deleteUser(int user_id) throws SQLException {
        String sqlDelLogin = "delete from user_login where id=?;";
        PreparedStatement delLogin = con.prepareStatement(sqlDelLogin);
        delLogin.setInt(1, user_id);
        delLogin.executeUpdate();
    }

    public void insertUser(DetailsBean details) throws SQLException {
        String sqlInsertLogin = "insert into user_login(fname,lname, email, password) values(?,?,?,?);";
        PreparedStatement newRowLogin = con.prepareStatement(sqlInsertLogin);
        newRowLogin.setString(1, details.getFname());
        newRowLogin.setString(2, details.getLname());
        newRowLogin.setString(3, details.getEmail());
        newRowLogin.setString(4, details.getPass());
        newRowLogin.executeUpdate();
    }

    public void insertNewContact(ContactsBean contact, int userId) throws SQLException {
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

    private void insertPhone(String phoneEnum, String phoneNumber, int userId, int contactId) throws SQLException {
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

    private void updatePhone(String phoneEnum, String phoneNumber, int userId, int contactId) throws SQLException {
        if (phoneNumber != null) {
            String sqlNumber = "update contact_numbers set phone_number=? where user_id=? and contact_id=? and number_type=?;";
            PreparedStatement newRow = con.prepareStatement(sqlNumber);
            newRow.setString(1, phoneNumber);
            newRow.setString(2, phoneEnum);
            newRow.setInt(3, userId);
            newRow.setInt(4, contactId);
            newRow.executeUpdate();
        }
    }

    public void updateOldContact(ContactsBean contact, int userId, String contactIdStr) throws SQLException {
        int contactId = Integer.parseInt(contactIdStr);
        String sql = "update contact_addresses set address_line1=?, address_line2=?, city=?, state=?, zip=?, country=?, fname=?, lname=? where user_id=? and id=?";
        PreparedStatement newRowContactAddresses = con.prepareStatement(sql);
        newRowContactAddresses.setString(1, contact.getAddressLine1());
        newRowContactAddresses.setString(2, contact.getAddressLine2());
        newRowContactAddresses.setString(3, contact.getCity());
        newRowContactAddresses.setString(4, contact.getState());
        newRowContactAddresses.setString(5, contact.getZip());
        newRowContactAddresses.setString(6, contact.getCountry());
        newRowContactAddresses.setString(7, contact.getFname());
        newRowContactAddresses.setString(8, contact.getLname());
        newRowContactAddresses.setInt(9, userId);
        newRowContactAddresses.setInt(10, contactId);
        newRowContactAddresses.executeUpdate();

        updatePhone("Mobile", contact.getMobile(), userId, contactId);
        updatePhone("Work", contact.getWork(), userId, contactId);
        updatePhone("Home", contact.getHome(), userId, contactId);

    }

    public int userCount(String email) throws SQLException {
        String sql = "select count(id) as user from user_login where email=? ;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setString(1, email);
        ResultSet rs = userRow.executeQuery();
        rs.next();
        return rs.getInt("user");
    }

    public ResultSet searchByName(int user_id, String name) throws SQLException {
        String sql = "select * from contact_addresses as t1 left outer join contact_numbers as t2 on t1.user_id=t2.user_id and t1.id=t2.contact_id where t1.user_id=? and (fname like ? or lname like ?) order by lname, fname, contact_id;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setInt(1, user_id);
        userRow.setString(2, "%" + name + "%");
        userRow.setString(3, "%" + name + "%");
        return userRow.executeQuery();
    }

    public ResultSet searchByName(int user_id) throws SQLException {
        String sql = "select * from contact_addresses as t1 left outer join contact_numbers as t2 on t1.user_id=t2.user_id and t1.id=t2.contact_id where t1.user_id=? order by lname, fname, contact_id;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setInt(1, user_id);
        return userRow.executeQuery();
    }

    public ResultSet searchByNumber(int user_id, String number) throws SQLException {
        String sql = "select * from contact_addresses as t1 left outer join contact_numbers as t2 on t1.user_id=t2.user_id and t1.id=t2.contact_id where t1.user_id=? and phone_number like ? order by lname, fname, contact_id;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setInt(1, user_id);
        userRow.setString(2, "%" + number + "%");
        return userRow.executeQuery();
    }

    public ResultSet searchContact(int user_id, int contact_id) throws SQLException {
        String sql = "select * from contact_addresses as t1 left outer join contact_numbers as t2 on t1.user_id=t2.user_id and t1.id=t2.contact_id where t1.user_id=? and contact_id=?;";
        PreparedStatement userRow = con.prepareStatement(sql);
        userRow.setInt(1, user_id);
        userRow.setInt(2, contact_id);
        return userRow.executeQuery();
    }

    public void closeConnection() throws SQLException {
        con.close();
    }
}
