package day5;

import java.io.*;
import java.util.*;
import java.text.*;


import org.json.simple.parser.*;
import org.json.simple.*;
import org.apache.commons.csv.*;


public class TransformCSV{
	private String[] headers;
	private List <CSVRecord> inputCsv;
	private JSONObject jsonObject;
	private CSVPrinter outputPrinter;
	private JSONObject customerDetails;
	private FileWriter output;
	private FileReader input;
	private String last;

	public TransformCSV(String inputCsvFile, String jsonFile, String outputCsvFile){
		try{
			JSONParser parser=new JSONParser();
			Object obj=parser.parse(new FileReader(jsonFile)) ;
			jsonObject = (JSONObject) obj;
			headers= getHeaders();

			input= new FileReader(inputCsvFile);
			CSVFormat csvFileFormat= CSVFormat.DEFAULT. withHeader(headers);
			CSVParser recordsParser= new CSVParser(input, csvFileFormat);
			inputCsv= recordsParser.getRecords();
			//System.out.println("SIZE: "+inputCsv.isEmpty());
			
			input.close();

			output = new FileWriter(outputCsvFile);
			String[] outputHeaders= getOutputHeaders();
			CSVFormat csvFileFormatOutput= CSVFormat.DEFAULT. withHeader(outputHeaders);
			outputPrinter=  new CSVPrinter(output, csvFileFormatOutput);

			//output.close();

		} catch(Exception e){
			System.err.println("Exception :( :(\n"+e.getMessage());
			e.printStackTrace();
		}
		customerDetails= new JSONObject();
	}

	private String[] getOutputHeaders(){
		Set <String> headersList= new LinkedHashSet <String>();
		for(String heading: headers){
			try{
				String value= (String) jsonObject.get(heading);
				if(value.equals("Map") ){
					headersList.add("Customer Details");
				}
				else{
					headersList.add(heading);
				}
			}catch(Exception e){
				headersList.add(heading);
			}
		}
		String[] outputHeaders= new String[headersList.size()];
		int i=0;
		for(String head: headersList){
			//System.out.println(head);
			outputHeaders[i]= head;
			i++;
		}
		return outputHeaders;
	}

	private String[] getHeaders() throws Exception{
		JSONArray headerJson= (JSONArray) jsonObject.get("Headers");
		String[] headers= new String[headerJson.size()];
		Iterator iter= headerJson.iterator();
		for(int i=0;i<headerJson.size(); i++){
			headers[i]=(String) headerJson.get(i);
		}
		return headers;
	}



	public void compareAndWriteCSV() throws Exception{
		for(CSVRecord record: inputCsv){
			if(record.getRecordNumber() != 1){
				for(String head: headers){
					//System.out.print(head+ ": " +record.getRecordNumber()+"\t");
					writeOutput(head, record);
				}
				//System.out.println();
				outputPrinter.print(customerDetails.toString());
				System.out.println(customerDetails.toString());
				outputPrinter.print(last);
				outputPrinter.println();
			}
		}
		System.out.println("Done");
		input.close();
		output.close();
	}

	private boolean isMap(Object obj){
		try{
			String val= (String) obj;
			if(val.equals("Map")){
				return true;
			}
			else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}

	private boolean isLast(String val){
		try{
			if(val.equals("Tax Total")){
				return true;
			}
			else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}

	private void writeOutput(String head, CSVRecord currentRecord)throws Exception{
		if(currentRecord.get(head) == null){
			outputPrinter.print("");
		}
		else if( isMap(jsonObject.get(head)) ){
			customerDetails.put(head, currentRecord.get(head));
		}
		else if(jsonObject.containsKey(head)){
			checkNumberOrDate(jsonObject.get(head), currentRecord, head);
		}
		else{
			outputPrinter.print(currentRecord.get(head));
		}
	}

	private void checkNumberOrDate(Object value, CSVRecord currentRecord, String head)throws Exception{
		try{
			if( ((String) value).equals("Date") ){
				//System.out.println("\tDate: "+(String) value + head); 
				changeDateFormat(currentRecord.get(head));
			}
		}catch(Exception e){
			//System.err.println(e);
			Double val= ( (Double) value ) * Double.valueOf(currentRecord.get(head));
			if(isLast(head)){
				last=String.format("%.2f", val);
			}
			else{
				outputPrinter.print( String.format("%.2f", val) );
			}
		}
	}

	private void changeDateFormat(Object value)throws Exception{
		String dateAndTime= (String) value;
		//System.out.println("\n"+dateAndTime+"\t"+ dateAndTime.length());
		SimpleDateFormat inputFormatterDate = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm");
		try{
			Date date=inputFormatterDate.parse(dateAndTime.substring(0,10));
			Date time= formatterTime.parse(dateAndTime.substring(11,16));
			SimpleDateFormat outputFormatterDate= new SimpleDateFormat("dd/MM/yyyy");
			outputPrinter.print(outputFormatterDate.format(date)+ " " + formatterTime.format(time));
		}catch(Exception e){
			System.out.println("\ndateAndTime: "+dateAndTime+"\t"+ dateAndTime.length());
			System.out.println();
			System.err.println(e);
			e.printStackTrace();
		}

	}

	public static void main(String[] args)throws Exception{
		String input="/Users/cb-rhama/chargebee/training/week2/day5/Input.csv";
		String output= "/Users/cb-rhama/chargebee/training/week2/day5/output.csv";
		String json="/Users/cb-rhama/chargebee/training/week2/day5/config.json";
		TransformCSV obj= new TransformCSV(input,json,output);
		obj.compareAndWriteCSV();

	}
}