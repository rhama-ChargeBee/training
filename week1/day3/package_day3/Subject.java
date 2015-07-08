package package_day3;

import java.io.Console;

public class Subject{
	private float marks1, marks2, marks3;
	private String subject1, subject2, subject3;
	
	public void set(String sub1, String sub2, String sub3, float mark1, float mark2, float mark3){
		subject1= sub1;
		marks1= mark1;
		subject2= sub2;
		marks2= mark2;
		subject3= sub3;
		marks3= mark3;
	}

	public Subject get(){
		return this;
	}

}