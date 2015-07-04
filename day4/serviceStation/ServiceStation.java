package serviceStation;

import java.util.*;
import java.io.*;

/*
Person - Employee/Customer (name, age, contact, emp_id) - an employee can also be a customer.
Vehicle - brand, color, service  (Car, Bike, Bus, service_charge)
Invoice (name_of_owner,vehicle, amount_total, employee_assigned)
*/

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

	private static Person getPerson(int i){
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
	private static Vehicle getVehicle(){
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

	private static ServiceType setServiceType(){
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

	private static int setEmpListIndex(int j, int len){
		return (j==len)? j: j+1;
	}

}