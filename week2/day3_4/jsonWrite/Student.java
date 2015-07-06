
package jsonWrite;

import java.util.*;
import java.text.*;
import java.lang.*;

import org.json.*;

public class Student{

	private JSONObject stuObj;

	private Date dateOfJoining;
	private String id;
	private Map <String, Long> marks= new HashMap <String, Long>();
	private String name;
	private Classes std;
	
	public Student(JSONObject stuObj){
		this.stuObj=stuObj;
	}

	public void setData() throws Exception{
		dateOfJoining= getDateOfJoining();
		id= stuObj.getString("ID");
		name= stuObj.getString("Name");
		std= Classes.valueOf(stuObj.getString("Std"));
		setMarksList(stuObj.getJSONArray("Marks"));
	}

	private Date getDateOfJoining(){
		try{
			SimpleDateFormat dateOfJoiningFormat= new SimpleDateFormat("dd/MM/yyyy");
			return dateOfJoiningFormat.parse( stuObj.getString("Date Of Joining") );
		}catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return null;
	}

	private void setMarksList(JSONArray marksJSON) throws Exception{
		for(int i=0; i<marksJSON.length(); i++){
			JSONObject tempMark= marksJSON.getJSONObject(i);
			marks.put(tempMark.getString("Subject"), tempMark.getLong("Mark"));
		}
	}

	public String toString(){
		StringBuilder returnString= new StringBuilder();
		returnString.append("Student Class\n").append(String.format("%s; %s; %s; ",id, dateOfJoining, name)).append(std).append("\n");
		for(String key: marks.keySet()){
			returnString.append(key).append(": ").append(marks.get(key)).append("\t");
		}
		returnString.append("\n");
		return returnString.toString();
	}
}
