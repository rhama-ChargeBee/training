package removeDuplicates;

import java.lang.*;
import java.util.*;
import java.io.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class RemoveDuplicates{
	CSVParser recordsParser; 
	List <CSVRecord> recordsList;
	CSVFormat csvFileFormat;

	RemoveDuplicates(String file){
		try{
			FileReader input= new FileReader(file);
			csvFileFormat = CSVFormat.DEFAULT.withSkipHeaderRecord();
			recordsParser= new CSVParser(input, csvFileFormat);
			recordsList = recordsParser.getRecords();
			input.close();
		}catch(Exception e){
			System.err.println(e);
		}	}

	public void writeToCSV(String file){
		HashSet <String[]> csvHashSet= toHashset();
		try{
			FileWriter output = new FileWriter(file);
			CSVPrinter outputPrinter=  new CSVPrinter(output, csvFileFormat);
			for(String[] record: csvHashSet){
				for(String cell: record){
					outputPrinter.print(cell);
				}
				outputPrinter.println();
			}

			output.close();
		}catch(Exception e){
			System.err.println(e);
		}
	}

	private HashSet <String[]> toHashset(){
		HashSet <String[]> csvHashSet= new HashSet <String[]>();

		for(CSVRecord record: recordsList){
			String[] str=new String[record.size()];
			for(int i=0; i< record.size(); i++){
				str[i]=record.get(i);
			}
			if(check(str[0], csvHashSet) ){
				csvHashSet.add(str);
			}
		}
		return csvHashSet;
	}

	private boolean check(String str, HashSet <String[]> csvHashSet){
		boolean flag=true;
		for(String[] iter: csvHashSet){
			if(str.equals(iter[0])){
				return false;
			}
			else{
				flag=true;
			}
		}
		return flag;
	}

	public static void main(String[] args){
		RemoveDuplicates obj = new RemoveDuplicates("/Users/cb-rhama/chargebee/training/week2/day3_4/removeDuplicates/sample.csv");
		obj.writeToCSV("/Users/cb-rhama/chargebee/training/week2/day3_4/removeDuplicates/output.csv");
	}

}