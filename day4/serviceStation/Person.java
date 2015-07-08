package serviceStation;

import java.util.*;
import java.io.*;


class Person{
	private String name;
	private int age;
	private long contact;
	private int empId;

	public void setValues(String name, int age, long contact, int empId){
		this.name=name;
		this.age= age;
		this.contact= contact;
		this.empId= empId;
	}

	public String getName(){
		return name;
	}
}