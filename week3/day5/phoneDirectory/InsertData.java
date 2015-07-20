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


    private void retrieveRecords(Connection con, String name) throws SQLException{
            try (Statement st = con.createStatement()) {
                ResultSet rs= st.executeQuery("select * from phone_directory where name like \"%" + name + "%\";");
                printRecords(rs);
            }
    }

    private void retrieveRecords(Connection con, long phoneNumber) throws SQLException{
        ResultSet rs = null;
        try{
            Statement st= con.createStatement();
            rs= st.executeQuery("select * from phone_directory where mobile=" +phoneNumber+ " or work=" +phoneNumber+ " or home=" +phoneNumber+ ";");
            printRecords(rs);
        }catch(Exception e){
            System.err.println(e);
            e.printStackTrace();
        }finally{
        	rs.close();
        }
    }

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
    public Connection openDBConnection() throws Exception{
    	Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://localhost/mysql?";
        Connection con = DriverManager.getConnection(connectionUrl,"root", "");
        return con;
    }
    
    public void createTableWithData(Connection con) throws SQLException{
            try (Statement st = con.createStatement()) {
                st.addBatch("create database if not exists directory;");
                st.addBatch("use directory;");
                st.addBatch("create table if not exists phone_directory (name varchar(50) not null, address varchar(250), mobile bigint not null unique, home bigint unique, work bigint unique);");
                st.executeBatch();
            }
    } 

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
    public static void main(String[] args) throws Exception{
    	InsertData obj=new InsertData("/Users/cb-rhama/chargebee/training/week2/day1_2/phoneDirectory/sample.csv");
    	Connection con= obj.openDBConnection();
    	obj.createTableWithData(con);
        //obj.batchInsertionOfData(con).executeBatch();
    	obj.getQuery(con);
    }
}