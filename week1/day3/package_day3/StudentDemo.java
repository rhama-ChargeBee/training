package package_day3;

import java.io.Console;

public class StudentDemo{

	public static void main(String[] args) throws Exception{
		StudentDemo stuDemo= new StudentDemo();
		Student stu= new Student();
		stu= stuDemo.storeStudentData(stu); 		//
		ResultGenerator printResult= new ResultGenerator();
		printResult.generateResult(stu);

	}

	public Student storeStudentData(Student stu){
		Subject sub= new Subject();
		sub.set("mat", "phy", "chem", 88,77,66);
		stu.set(1234, "qwerty", true, sub.get());
		return stu.get();
	}
}
