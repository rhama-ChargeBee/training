package jsonWrite;

import java.util.*;
//import org.json.simple.*;
import org.json.*;

public class Student{

	private JSONObject stuObj;

	private String dateOfJoining;
	private String id;
	private List <Map <String, Object> > marks;
	private String name;
	private String std;
	
	public Student(JSONObject stuObj){
		this.stuObj=stuObj;
	}

	public void setData() throws Exception{
		dateOfJoining= stuObj.getString("Date Of Joining");
		id= stuObj.getString("ID");
		name= stuObj.getString("Name");
		std=stuObj.getString("Std");
		setMarksList(stuObj.getJSONArray("Marks"));
	}

	private void setMarksList(JSONArray marksJSON) throws Exception{
		for(int i=0; i<marksJSON.length(); i++){
			Map <String, Object> tempMap= new HashMap <String, Object> ();
			JSONObject tempMark= marksJSON.getJSONObject(i);
			tempMap.put("Subject",tempMark.getString("Subject"));
			tempMap.put("Mark",Integer.valueOf( tempMark.getInt("Mark") ));
			marks.add(tempMap);
		}
	}
}
