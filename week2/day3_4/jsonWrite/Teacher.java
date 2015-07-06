
package jsonWrite;

import org.json.*;

import java.util.*;
import java.text.*;

public class Teacher{
	private Date dateOfJoining;
	private String id;
	private String name;
	private Double salary;
	private List <Classes> classesTakingCareOf= new ArrayList <Classes> ();;

	private JSONObject teachObj;

	public Teacher(JSONObject teachObj){
		this.teachObj=teachObj;
	}

	public void setData() throws Exception{
		id= teachObj.getString("ID");
		name= teachObj.getString("Name");
		dateOfJoining= getDateOfJoining();
		salary= teachObj.getDouble("Salary");
		setClassesList(teachObj.getJSONArray("Classes Taking Care Of"));
	}

	private Date getDateOfJoining(){
		try{
			SimpleDateFormat dateOfJoiningFormat= new SimpleDateFormat("dd/MM/yyyy");
			return dateOfJoiningFormat.parse( teachObj.getString("Date Of Joining") );
		}catch(Exception e){
			System.err.println(e);
			e.printStackTrace();
		}
		return null;
	}

	private void setClassesList(JSONArray classesList) throws Exception{
		for(int i=0; i<classesList.length(); i++){
			classesTakingCareOf.add( Classes.valueOf(classesList.getString(i)) );
		}
	}

	public String toString(){
		StringBuilder returnString= new StringBuilder();
		returnString.append("Teacher Class\n").append(String.format("%s; %s; %s; %f\n",id, dateOfJoining, name, salary));
		for(Classes std: classesTakingCareOf){
			returnString.append(std).append(" ");
		}
		returnString.append("\n");
		return returnString.toString();
	}
}
