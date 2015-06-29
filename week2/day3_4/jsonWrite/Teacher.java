
package jsonWrite;

import org.json.simple.parser.*;
import org.json.simple.*;

import java.util.*;

public class Teacher{
	private String dateOfJoining;
	private String id;
	private String name;
	private Long salary;
	private List <String> classesTakingCareOf;

	private JSONObject teachObj;

	public Teacher(JSONObject teachObj){
		this.teachObj=teachObj;
		classesTakingCareOf= new ArrayList <String> ();
	}

	public void setData() throws Exception{
		id= (String) teachObj.get("ID");
		name= (String) teachObj.get("Name");
		dateOfJoining= (String) teachObj.get("Date Of Joining");
		salary= (Long) teachObj.get("Salary");
		setClassesList((JSONArray) teachObj.get("Classes Taking Care Of"));
		display();
	}

	private void setClassesList(JSONArray classesList) throws Exception{
		for(int i=0;i<classesList.size(); i++){
			classesTakingCareOf.add((String) classesList.get(i));
		}
	}

	private void display(){
		System.out.println("Teacher Class");
		System.out.format("%s; %s; %s; %d\n",id, dateOfJoining, name, salary);
		for(String std: classesTakingCareOf){
			System.out.print(std + " ");
		}
		System.out.println();
	}
}
