package phoneDirectory;

import java.lang.*;
import java.util.*;
import java.io.*;

import org.json.simple.parser.*;
import org.json.simple.*;

public class JSONEntry{
	public static void main(String[] args) throws Exception{
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(new FileReader("/Users/cb-rhama/chargebee/training/week2/day1_2/phoneDirectory/sample.json")) ;
		JSONObject jsonObj= (JSONObject) obj;

		ArrayList <Entry> directory= new ArrayList <Entry> ();
		
		JSONArray jsonRecords= (JSONArray) jsonObj.get("Directory");
		for(Object record: jsonRecords){
			JSONObject jsonRecord = (JSONObject) record;
			String name= (String) jsonRecord.get("Name");
			String address= (String) jsonRecord.get("Address");
			JSONObject phone= (JSONObject) jsonRecord.get("Phone");
			Long mobile=(Long) phone.get("Mobile");
			Long home=(Long) phone.get("Home");
			Long work= (Long) phone.get("Work");
			directory.add(new Entry(name,address, mobile.intValue() , home.intValue() , work.intValue() ));
		}	
		PhoneDirectory obj1= new PhoneDirectory(directory);
		obj1.userOptions();
	}
}