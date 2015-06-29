package jsonWrite;

import org.json.*;

import java.util.*;

public class Teacher{
	private String dateOfJoining;
	private String id;
	private String name;
	private int salary;
	private List <String> classesTakingCareOf;

	private JSONObject teachObj;

	public Teacher(JSONObject teachObj){
		this.teachObj=teachObj;
	}

	public void setData() throws Exception{
		id= teachObj.getString("ID");
		name= teachObj.getString("Name");
		dateOfJoining= teachObj.getString("Date Of Joining");
		salary= teachObj.getInt("Salary");
		setClassesList(teachObj.getJSONArray("Classes Taking Care Of"));
	}

	private void setClassesList(JSONArray classesList) throws Exception{
		for(int i=0;i<classesList.length(); i++){
			classesTakingCareOf.add(classesList.getString(i));
		}
	}
}
