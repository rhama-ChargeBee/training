import java.io.Console;
class Employee{
	private String name;
	private boolean gender;
	private double taxableIncome;
	private double taxAmount;

	Employee(String name, boolean gender, double taxableIncome){
		this.name=name;
		this.gender=gender;
		this.taxableIncome=taxableIncome;
	}

	void setTaxableIncome(double val){
		taxableIncome=val;
	}
	void setTaxAmount(double val){
		taxAmount=val;
	}
	String getName(){
		return name;
	}
	boolean getGender(){
		return gender;
	}
	double getTaxAmount(){
		return taxAmount;
	}
	double getTaxableIncome(){
		return taxableIncome;
	}
	void display(){
		System.out.println("["+name+"] | ["+(gender? "male":"female")+"] | ["+taxableIncome+"] | ["+taxAmount+"]");
	}
}

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