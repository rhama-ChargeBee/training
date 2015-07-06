
package jsonWrite;

import java.io.*;
import java.util.*;
import java.text.*;

import org.json.*;

public class JsonWrite{
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new FileReader("/Users/cb-rhama/chargebee/training/week2/day3_4/jsonWrite/students-teachers.json"));
		StringBuilder jsonString= new StringBuilder();
		String line;
		while( (line=br.readLine())!= null ){
			jsonString.append(line);
		}

		JSONObject jsonObj= new JSONObject(jsonString.toString());
		Student stuObj= new Student(jsonObj.getJSONObject("Student"));
		Teacher teachObj= new Teacher(jsonObj.getJSONObject("Teacher"));
		stuObj.setData();
		teachObj.setData();
		System.out.println();
		System.out.println(stuObj.toString());
		System.out.println(teachObj.toString());
	}
}