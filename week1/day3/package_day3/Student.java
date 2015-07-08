package package_day3;

import java.io.Console;

public class Student{
	private int studentId;
	private String studentName;
	private boolean gender;
	private Subject subjects;

	public void set(int id, String name, boolean gen, Subject subj){
		studentId=id;
		studentName= name;
		gender=gen;
		subjects= subj;
	}
	public Student get(){
		return this;
	}
}
