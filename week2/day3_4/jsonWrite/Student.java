
package jsonWrite;

import java.util.*;
//import org.json.simple.*;
import org.json.simple.parser.*;
import org.json.simple.*;

public class Student{

	private JSONObject stuObj;

	private String dateOfJoining;
	private String id;
	private List <Map <String, Object> > marks;
	private String name;
	private String std;
	
	public Student(JSONObject stuObj){
		this.stuObj=stuObj;
		marks= new ArrayList <Map <String, Object> > ();
	}

	public void setData() throws Exception{
		dateOfJoining= (String) stuObj.get("Date Of Joining");
		id= (String) stuObj.get("ID");
		name= (String) stuObj.get("Name");
		std=(String) stuObj.get("Std");
		setMarksList((JSONArray) stuObj.get("Marks"));
		display();
	}

	private void setMarksList(JSONArray marksJSON) throws Exception{
		for(int i=0; i<marksJSON.size(); i++){
			Map <String, Object> tempMap= new HashMap <String, Object> ();
			JSONObject tempMark= (JSONObject) marksJSON.get(i);
			tempMap.put("Subject",(String) tempMark.get("Subject"));
			tempMap.put("Mark",(Long) tempMark.get("Mark") );
			marks.add(tempMap);
		}
	}

	private void display(){
		System.out.println("Student Class");
		System.out.format("%s; %s; %s; ",id, dateOfJoining, name);
		System.out.println(std);
		for(Map <String, Object> mark: marks){
			System.out.println( "Subject: "+ mark.get("Subject") + "\t"+"Mark: "+ String.valueOf( mark.get("Mark") ) );
		}
		System.out.println();
	}
}
