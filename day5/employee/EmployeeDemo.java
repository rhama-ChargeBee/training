package employee;

import java.io.*;
import java.util.*;

public class EmployeeDemo{
	public static void main(String[] args){
		int n1,n2;
		int i;
		Console cons=System.console();
		List <Employee> empList= new ArrayList();
		n1=Integer.parseInt(cons.readLine("Total no of emplyees: "));
		for(i=0;i<n1;i++){
			System.out.println("Employee "+(i+1));
			getValue(empList);
		}
		System.out.println("\nPick a field to sort: ");
		System.out.println("1.Name\n2.Age\n3.Salary");
		n2=Integer.parseInt(cons.readLine());
		System.out.println("\nSorting...");
		sortArray(empList, n2);
		for(i=0;i<n1;i++){
			empList.get(i).display();
		}
	}

	private static void sortArray(List <Employee> obj, int n){
		switch(n){
				case 1:
					Collections.sort(obj,obj.get(0).objName);
					break;
				case 2:
					Collections.sort(obj,obj.get(0).objAge);
					break;
				case 3:
					Collections.sort(obj,obj.get(0).objSalary);
					break;
				
				default:
					System.out.println("Wrong value, Taking default as name");
					Collections.sort(obj,obj.get(0).objName);
					break;
			}

	}

	private static void getValue(List <Employee> obj){
		String name;
		int age;
		float salary;
		Console cons=System.console();
		name=cons.readLine("Name: ");
		age=Integer.parseInt(cons.readLine("Age: "));
		salary=Float.parseFloat(cons.readLine("Salary: "));
		obj.add(new Employee(name, salary, age));

	}
}