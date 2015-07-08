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

	public Comparator <Employee> objName= (Employee obj1, Employee obj2) -> ( obj1.getName().compareToIgnoreCase( obj2.getName() ) );

	public Comparator <Employee> objAge= (Employee obj1, Employee obj2) ->  obj1.getAge()-obj1.getAge() ;

	public Comparator <Employee> objSalary= (Employee obj1, Employee obj2)-> (int) ( obj1.getSalary()-obj2.getSalary() ) ;

}
