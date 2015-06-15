package serviceStation;

import java.util.*;
import java.io.*;
/*
Person - Employee/Customer (name, age, contact, emp_id) - an employee can also be a customer.
Vehicle - brand, color, service  (Car, Bike, Bus, service_charge)
Invoice (name_of_owner,vehicle, amount_total, employee_assigned)
*/
enum ServiceType {
    CAR(20_000f),
    BIKE(10_000f),
    BUS(30_000f),
    TRUCK(40_000f);
    private float serviceCharge;
    ServiceType(float charge){
    	serviceCharge=charge;
    }
    
    float serviceCharge(){
    	return serviceCharge;
    }
}

class Person{
	private String name;
	private int age;
	private int contact;
	private int empId;

	void setValues(String name, int age, int contact, int empId){
		this.name=name;
		this.age= age;
		this.contact= contact;
		this.empId= empId;
	}

	String getName(){
		return name;
	}
}

class Vehicle{
	private String brand;
	private String colour;
	private ServiceType service;//make this enum... check it later...

	void setValues(String brand, String colour, ServiceType service){
		this.brand=brand;
		this.colour= colour;
		this.service= service;
	}

	ServiceType getType(){
		return service;
	}

	float getCharge(){
		return service.serviceCharge();
	}
}

class Invoice{
	private static int id=0;
	private Person owner;
	private Vehicle vehicle;
	private float amountTotal;
	private Person employeeAssigned;

	void setValues(Person owner, Vehicle vehicle, Person employeeAssigned){
		this.owner= owner;
		this.vehicle= vehicle;
		this.employeeAssigned=employeeAssigned;
	}

	void generateInvoice(){
		System.out.println();
		System.out.println("*********************************Invoice**********************************");
		System.out.println("Customer Name: "+ owner.getName()+"\tVehicle Type: "+ vehicle.getType());
		System.out.println("One time service\t"+vehicle.getCharge());
		System.out.println("by "+ employeeAssigned.getName());
		System.out.println();
		System.out.println("Total\t\t\t"+vehicle.getCharge());
		System.out.println("******************************End of Invoice******************************");
		System.out.println();

	}

}

public class ServiceStation{ 
	public static void main(String[] args) throws Exception{
		Console cons= System.console();
		List <Person> custList= new ArrayList();
		List <Person> empList= new ArrayList();
		int i,n,j;
		
		char ch;

		System.out.println("The services availabe are....");
		for(ServiceType serv: ServiceType.values()){
			System.out.println(serv+": "+serv.serviceCharge());
		}

		n=Integer.parseInt(cons.readLine("Enter the number of employees: "));
		System.out.println("Enter the list of employees below");
		Person p=new Person();

		for(i=0;i<n;i++){
			System.out.println("Employee "+ (i+1));
			p=getPerson(i+1);
			empList.add(p);
		}

		Vehicle vehicle=new Vehicle();
		Invoice invoice= new Invoice();
		Person p1;

		j=0;

		System.out.println("\n*******************************************************************************");


		do{
			System.out.println("Enter the customer details...");
			p=getPerson(0);
			System.out.println("\nEnter the vehicle details: ");
			vehicle=getVehicle();
			p1= empList.get(j);
			invoice.setValues(p,vehicle,p1);
			j=setEmpListIndex(j,(empList.size() -1));
			invoice.generateInvoice();

			ch=cons.readLine("Do u want to enter another customer?(y/n) ").charAt(0);
		}while(ch=='y');
	}

	static Person getPerson(int i){
		Console cons= System.console();
		String name;
		int age, contact;
		name= cons.readLine("Name: ");
		age= Integer.parseInt(cons.readLine("Age: "));
		contact= Integer.parseInt(cons.readLine("Contact number: "));
		Person p=new Person();
		p.setValues(name, age, contact, i);
		return p;

	}
	static Vehicle getVehicle(){
		Vehicle vehicle=new Vehicle();
		int i=1;
		Console cons= System.console();
		String brand, colour;
		ServiceType service;
		brand= cons.readLine("Brand: ");
		colour= cons.readLine("Colour: ");
		System.out.println("Pick u your Vehicle...");
		service= setServiceType();
		vehicle.setValues(brand,colour,service);
		return vehicle;
	}

	static ServiceType setServiceType(){
		ServiceType service;
		Console cons= System.console();
		int i=1;
		for(ServiceType serv: ServiceType.values()){
			System.out.println(i+". "+serv);
			i++;
		}

		service=ServiceType.CAR;
		i=Integer.parseInt(cons.readLine());
		switch(i){
			case 1:
				service= ServiceType.CAR;
				break;
			case 2:
				service= ServiceType.BIKE;
				break;
			case 3:
				service= ServiceType.BUS;
				break;
			case 4:
				service= ServiceType.TRUCK;
				break;
			default:
				System.out.println("Wrong option, default value CAR taken.");
				break;
		}
		return service;
	}

	static int setEmpListIndex(int j, int len){
		if(j==len){
			j=0;
		}
		else{
			j++;
		}
		return j;
	}

}