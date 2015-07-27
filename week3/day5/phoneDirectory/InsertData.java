package phoneDirectory;

import java.util.*;
import java.io.*;

import org.apache.commons.csv.*;
import java.sql.*;

public class InsertData{
	private List <CSVRecord> csvRecordsList;
    public InsertData(String filePathString){
    	try{
			FileReader input= new FileReader(filePathString);
			CSVFormat csvFileFormat = CSVFormat.DEFAULT.withSkipHeaderRecord(true);
			CSVParser recordsParser= new CSVParser(input, csvFileFormat);
			csvRecordsList = recordsParser.getRecords();
			//System.out.println(csvFileFormat.getSkipHeaderRecord());
		}catch(Exception e){
			System.err.println(e);
		}
    }

    //Function overloading
    //If the second arguement is a string, the retrieval of records by name takes place.
    private ResultSet retrieveRecords(Connection con, String name) throws SQLException{
            try (Statement st = con.createStatement()) {
                ResultSet rs= st.executeQuery("select * from phone_directory where name like \"%" + name + "%\";");
                printRecords(rs);
                return rs;
            }
    }

    //If the second arguement is a long, the retrieval of records by the phone numbers(mobile, work or home) takes place.
    private ResultSet retrieveRecords(Connection con, long phoneNumber) throws SQLException{
        ResultSet rs = null;
        try{
            Statement st= con.createStatement();
            rs= st.executeQuery("select * from phone_directory where mobile=" +phoneNumber+ " or work=" +phoneNumber+ " or home=" +phoneNumber+ ";");
            printRecords(rs);
        }catch(Exception e){
            System.err.println(e);
            e.printStackTrace();
        }
        return rs;
    }

    //prints the given ResultSet
    private void printRecords(ResultSet rs){
            try {
                while(rs.next()){
                    System.out.println();
                    System.out.print(rs.getString("name") + "	");
                    System.out.print(rs.getString("address") + "	");
                    System.out.print(rs.getLong("mobile") + "     ");
                    System.out.print(rs.getLong("home") + "	   ");
                    System.out.print(rs.getLong("work") + "	   ");
                } 
            	rs.close();
            }catch(Exception e){
            	System.err.println(e);
            	e.printStackTrace();
            }
        System.out.println();
        System.out.println();
    }

    //function overloading
    //updates the name, mobile, home and work fields of the records that match(partialy or completely) the given string, name;
    private void updateRecords(Connection con, String name)throws Exception{
        PreparedStatement ps= null;
        Scanner scan=new Scanner(System.in);
        System.out.println("What do u want to update?");
        System.out.println("1.Name\n2.Mobile\n3.Home\n4.Work");
        int n= scan.nextInt();
        System.out.println("Enter new value:");
        switch(n){
            case 1:
                String val= scan.next();
                ps=con.prepareStatement("update phone_directory set name=? where name like ?;");
                ps.setString(1,val);
                break;
            case 2:
                long val1=scan.nextLong();
                ps=con.prepareStatement("update phone_directory set mobile=? where name like ?;");
                ps.setLong(1,val1);
                break;
            case 3:
                val1=scan.nextLong();
                ps=con.prepareStatement("update phone_directory set home=? where name like ?;");
                ps.setLong(1,val1);
                break;
            case 4:
                val1=scan.nextLong();
                ps=con.prepareStatement("update phone_directory set work=? where name like?;");
                ps.setLong(1,val1);
                break;
            default:
                System.err.println("ERROR");
                break;
        }
        ps.setString(2,name);
        ps.executeUpdate();
        ps.close();
    }

    //updates the name, mobile, home and work fields of the records that match(completely) the given long, number;
    private void updateRecords(Connection con, long number)throws Exception{
        PreparedStatement ps= null;
        Scanner scan=new Scanner(System.in);
        System.out.println("What do u want to update?");
        System.out.println("1.Name\n2.Mobile\n3.Home\n4.Work");
        int n= scan.nextInt();
        System.out.println("Enter new value:");
        switch(n){
            case 1:
                String val= scan.next();
                ps=con.prepareStatement("update phone_directory set name=? where mobile=? or home= ? or work= ?;");
                ps.setString(1,val);
                break;
            case 2:
                long val1=scan.nextLong();
                ps=con.prepareStatement("update phone_directory set mobile=? where mobile=? or home= ? or work= ?;");
                ps.setLong(1,val1);
                break;
            case 3:
                val1=scan.nextLong();
                ps=con.prepareStatement("update phone_directory set home=? where mobile=? or home= ? or work= ?");
                ps.setLong(1,val1);
                break;
            case 4:
                val1=scan.nextLong();
                ps=con.prepareStatement("update phone_directory set work=? where mobile=? or home= ? or work= ?");
                ps.setLong(1,val1);
                break;
            default:
                System.err.println("ERROR");
                break;
        }
        ps.setLong(2,number);
        ps.setLong(3,number);
        ps.setLong(4,number);
        ps.executeUpdate();
        ps.close();
    }

    //Gets all the field required for a record and enters it into the database.
    private void putData(Connection con)throws Exception{
    	String sqlInsert= "insert into phone_directory values(? ,? ,? ,? ,? );";
    	PreparedStatement ps= con.prepareStatement(sqlInsert);
    	Scanner scan= new Scanner(System.in);
    	System.out.println("Name");
    	ps.setString(1, scan.next());
    	System.out.println("Address");
    	ps.setString(2, scan.next());
    	System.out.println("Mobile");
    	ps.setLong( 3, scan.nextLong() );
    	System.out.println("Home");
    	ps.setLong(4, scan.nextLong() );
    	System.out.println("Work");
    	ps.setLong(5, scan.nextLong());
    	ps.executeUpdate();
		ps.close();
    }

    //Searches the records 1st and edits the retrieved records. updateRecords(Connection, String): void and updateRecords(Connection, long): void are used for searching by name and phone number(mobile, home and work) respectively.
    private void editData(Connection con) throws Exception{
		Scanner scan= new Scanner(System.in);
        System.out.println("Search by...\n1.Name\n2.Phone Number");
		int n=scan.nextInt();
		ResultSet rs = null;
		switch(n){
			case 1:
                System.out.print("Enter the String: ");
                String name= scan.next();
				rs= retrieveRecords(con, name);
                updateRecords(con, name);
				break;
			case 2:
                System.out.print("Enter the Phone Number: ");
                long number= scan.nextLong();
				rs= retrieveRecords(con, number);
                updateRecords(con, number);
				break;
			default:
				System.out.println("Wrong option... Taking default option as \"Name\"");
				rs= retrieveRecords(con, scan.next());
				break;
		}

    }

    //returns Connection for the opened db
    public Connection openDBConnection() throws Exception{
    	Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/mysql?";
        Connection con = DriverManager.getConnection(connectionUrl,"root", "");
        return con;
    }
    
    //Creates a new table if the table does not already exists.
    public void createTable(Connection con) throws SQLException{
            try (Statement st = con.createStatement()) {
                st.addBatch("create database if not exists directory;");
                st.addBatch("use directory;");
                st.addBatch("create table if not exists phone_directory (name varchar(50) not null, address varchar(250), mobile bigint not null unique, home bigint unique, work bigint unique);");
                st.executeBatch();
            }
    } 

    //Inserts data into the table from the .csv file of the phone directory. the path of this csv file is given in the constructor
    public PreparedStatement batchInsertionOfData(Connection con) throws SQLException{
    	String sqlInsert= "insert into phone_directory values(? ,? ,? ,? ,? );";
    	PreparedStatement ps= con.prepareStatement(sqlInsert);
    	for(CSVRecord record: csvRecordsList){
		if(record.getRecordNumber() != 1){
                System.out.println(record.get(0)+" "+record.get(2)+" "+record.get(3)+" "+record.get(4));
		    	ps.setString(1, record.get(0));
		    	ps.setString(2, record.get(1));
		    	ps.setLong( 3, Long.parseLong(record.get(2)) );
		    	ps.setLong(4, Long.parseLong(record.get(3)) );
		    	ps.setLong(5, Long.parseLong(record.get(4)) );
                        ps.addBatch();
                    }
		}
		return ps;
    }   

    //This is to get the data from the table. Search by name and search by phone number are the two options provided,
    // the function retrieveRecords(Connection, String):void and retrieveRecords(Connection, long): void is called respectively.
    public void getQuery(Connection con) throws Exception{
		char c= 'y';
		Scanner scan= new Scanner(System.in);
		do{
            System.out.println("Search by...\n1.Name\n2.Phone Number");
			int n=scan.nextInt();
			switch(n){
				case 1:
                    System.out.print("Enter the String: ");
					retrieveRecords(con, scan.next());
					break;
				case 2:
                    System.out.print("Enter the Phone Number: ");
					retrieveRecords(con, scan.nextLong());
					break;
				default:
					System.out.println("Wrong option... Taking default option as \"Name\"");
					retrieveRecords(con, scan.next());
					break;
			}
                        System.out.println("Do you want to continue???(y/n)");
			c= scan.next().charAt(0);
		}while(c=='y');
    }

    //Inserts data into the database or updates data in the database, according to the user's choice, putData(Connection):void and editData(Connection): void are called respetively.
    public void insertAndUpdate(Connection con) throws Exception{
    	char c='y';
    	Scanner scan= new Scanner(System.in);

    	do{
    		System.out.println("Select an option \n1.InsertData\n2.EditData\n3.Exit\n");
			int n=scan.nextInt();
			switch(n){
				case 1:
                    putData(con);
                    break;
				case 2:
                    editData(con);
                    break;
				default:
					System.out.println("Exiting...");
                    c='n';
					break;
    	}
    }while(c=='y');
    }

    public static void main(String[] args) throws Exception{
    	InsertData obj=new InsertData("/Users/cb-rhama/chargebee/training/week2/day1_2/phoneDirectory/sample.csv");
    	Connection con= obj.openDBConnection();
    	obj.createTable(con);
        //obj.batchInsertionOfData(con).executeBatch();
    	obj.getQuery(con);
        obj.insertAndUpdate(con);
    }
}