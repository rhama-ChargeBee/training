package package_day3;

import java.io.Console;

public class ResultGenerator{
	public static void generateResult(Student stu){
		float total= stu.subjects.marks1 + stu.subjects.marks2 + stu.subjects.marks3;
		float average= total/3;
		System.out.println("Name: "+stu.studentName+ "\nTotal: "+total+"\tAverage: "+ average);
	}
}