package phoneDirectory;

import java.util.*;

public class Entry{
	private String name;
	private String address;
	private Map <String, Integer> phoneNumber;
	public Entry(String name, String address, int mobile, int home, int work){
		this.name=name;
		this.address=address;
		phoneNumber=new HashMap <String, Integer> ();
		phoneNumber.put("Mobile", Integer.valueOf(mobile));
		phoneNumber.put("Home", Integer.valueOf(home));
		phoneNumber.put("Work", Integer.valueOf(work));
	}

	public String getName(){
		return name;
	}
	public String getAddress(){
		return address;
	}
	public int getMobile(){
		return phoneNumber.get("Mobile").intValue();
	}
	public int getHome(){
		return phoneNumber.get("Home").intValue();
	}
	public int getWork(){
		return phoneNumber.get("Work").intValue();
	}
	public Collection getPhoneNumbers(){
		return phoneNumber.values();
	}

	public void display(){
		System.out.println(name+"\n"+"Address: "+ address);
		System.out.println("Mobile: "+getMobile()+"\tHome: "+getHome()+"\tWork: "+getWork()+"\n");
	}
}