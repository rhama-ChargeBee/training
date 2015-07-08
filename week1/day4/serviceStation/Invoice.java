package serviceStation;

import java.util.*;
import java.io.*;

class Invoice{
	private static int id=0;
	private String invoiceId;
	private Person owner;
	private Vehicle vehicle;
	private float amountTotal;
	private Person employeeAssigned;

	Invoice(){
		id++;
		invoiceId="ID"+id;
	}

	public void setValues(Person owner, Vehicle vehicle, Person employeeAssigned){
		this.owner= owner;
		this.vehicle= vehicle;
		this.employeeAssigned=employeeAssigned;
		amountTotal=vehicle.getCharge();
	}

	public float getTotal(){
		return amountTotal;
	}

	public void generateInvoice(){
		System.out.println();
		System.out.println("*********************************Invoice**********************************");
		System.out.println("Customer Name: "+ owner.getName()+"\tVehicle Type: "+ vehicle.getType());
		System.out.println("One time service\t"+vehicle.getCharge());
		System.out.println("by "+ employeeAssigned.getName());
		System.out.println();
		System.out.println("Total\t\t\t"+getTotal());
		System.out.println("******************************End of Invoice******************************");
		System.out.println();

	}

}