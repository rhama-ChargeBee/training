package phoneDirectory;

import java.lang.*;
import java.util.*;
import java.io.*;

import org.apache.commons.csv.*;

public class CsvEntry{
	public static void main(String[] args){
		CSVParser recordsParser; 
		List <CSVRecord> recordsList;
		CSVFormat csvFileFormat;

		ArrayList <Entry> directory= new ArrayList <Entry> ();
		
		try{
			FileReader input= new FileReader("/Users/cb-rhama/chargebee/training/week2/day1_2/phoneDirectory/sample.csv");
			csvFileFormat = CSVFormat.DEFAULT.withSkipHeaderRecord(true);
			recordsParser= new CSVParser(input, csvFileFormat);
			recordsList = recordsParser.getRecords();
			//System.out.println(csvFileFormat.getSkipHeaderRecord());
			for(CSVRecord record: recordsList){
				if(record.getRecordNumber() != 1){
					directory.add(new Entry(record.get(0),record.get(1), Integer.parseInt(record.get(2)), Integer.parseInt(record.get(3)), Integer.parseInt(record.get(4)) ));
				}
			}	
			PhoneDirectory obj= new PhoneDirectory(directory);
			obj.userOptions();
			input.close();

		}catch(Exception e){
			System.err.println(e);
		}
	}
}