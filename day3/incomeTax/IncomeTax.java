package incomeTax;

import java.io.Console;

public class IncomeTax{
	private static Employee calculateTax(Employee emp){
		int tax;
		double taxValue;
		if(emp.getGender()){
			tax=20;
		}
		else {
			tax=15;
		}
		taxValue= ((double) (tax) * emp.getTaxableIncome())/100.0d;
		//System.out.println(taxValue+"*************");
		emp.setTaxAmount(taxValue);
		return emp;
	}
	
	public static void main(String[] args) throws Exception{
		Console cons= System.console();
		String name;
		boolean gender;
		double income;
		Employee[] emp= new Employee[3];
		int i;
		for(i=0; i< emp.length; i++){
			System.out.println("Employee...");
			name=cons.readLine("Enter the name: ");
			gender= (cons.readLine("Gender(m/f): ").charAt(0) == 'm') ? true: false;
			income= Double.parseDouble(cons.readLine("Enter taxable income: "));
			Employee empTemp= new Employee(name, gender, income);
			emp[i]=  calculateTax(empTemp);
			System.out.println();
		}
		System.out.println("\nPrinting all employees");
		for(i=0; i<emp.length; i++){
			emp[i].display();
		}
	}
}