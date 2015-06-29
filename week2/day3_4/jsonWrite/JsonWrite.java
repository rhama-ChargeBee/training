package jsonWrite;

import java.io.*;
import java.util.*;

import org.json.simple.parser.*;
import org.json.simple.*;

public class JsonWrite{
	
	public static void main(String[] args) throws Exception{
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(new FileReader("/Users/cb-rhama/chargebee/training/week2/day3_4/jsonWrite/students-teachers.json")) ;
		JSONObject jsonObj= (JSONObject) obj;
		Student stuObj= new Student(jsonObj.getJSONObject("Student"));
		Teacher teachObj= new Teacher(jsonObj.get("Teacher"));
	}
}