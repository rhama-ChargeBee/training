package employee;

import java.util.*;

public class Employee{
	private String name;
	private float salary;
	private int age;

	public Employee(String name, float salary, int age){
		this.name=name;
		this.salary=salary;
		this.age=age;
	}

	private String getName(){
		return name;
	}
	private int getAge(){
		return age;
	}
	private float getSalary(){
		return salary;
	}

	public void display(){
		System.out.println(name+"\t"+age+"\t"+salary);
	}

	
	
	public Comparator <Employee> objName=new Comparator <Employee>(){
		public int compare(Employee obj1, Employee obj2){
			return obj1.getName().compareToIgnoreCase(obj2.getName());
		}
	};
	public Comparator <Employee> objAge=new Comparator <Employee>(){
		public int compare(Employee obj1, Employee obj2){
			return (obj1.getAge()-obj1.getAge());
		}
	};
	public Comparator <Employee> objSalary=new Comparator <Employee>(){
		public int compare(Employee obj1, Employee obj2){
			return (int) (obj1.getSalary()-obj2.getSalary());
		}
	};

}

/*
*/