package incomeTax;

import java.io.Console;

public class Employee{
	private String name;
	private boolean gender;
	private double taxableIncome;
	private double taxAmount;

	public Employee(String name, boolean gender, double taxableIncome){
		this.name=name;
		this.gender=gender;
		this.taxableIncome=taxableIncome;
	}

	public void setTaxableIncome(double val){
		taxableIncome=val;
	}
	public void setTaxAmount(double val){
		taxAmount=val;
	}
	public String getName(){
		return name;
	}
	public boolean getGender(){
		return gender;
	}
	public double getTaxAmount(){
		return taxAmount;
	}
	public double getTaxableIncome(){
		return taxableIncome;
	}
	public void display(){
		System.out.println("["+name+"] | ["+(gender? "male":"female")+"] | ["+taxableIncome+"] | ["+taxAmount+"]");
	}
}