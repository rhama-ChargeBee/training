package package_day3;

import java.io.Console;

class Student{
	private int studentId;
	String studentName;
	private boolean gender;
	Subject subjects;

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

class Subject{
	float marks1, marks2, marks3;
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


class ResultGenerator{
	static void generateResult(Student stu){
		float total, average;
		total= stu.subjects.marks1 + stu.subjects.marks2 + stu.subjects.marks3;
		average= total/3;
		System.out.println("Name: "+stu.studentName+ "\nTotal: "+total+"\tAverage: "+ average);
	}
}

public class StudentDemo{

	public static void main(String[] args) throws Exception{
		StudentDemo stuDemo= new StudentDemo();
		Student stu= new Student();
		stu= stuDemo.storeStudentData(stu); 		//
		ResultGenerator printResult= new ResultGenerator();
		printResult.generateResult(stu);

	}

	public static Student storeStudentData(Student stu){
		Subject sub= new Subject();
		sub.set("mat", "phy", "chem", 88,77,66);
		stu.set(1234, "qwerty", true, sub.get());
		return stu.get();
	}
}
