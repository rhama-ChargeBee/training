package package_day3;

import java.io.Console;

public class Animal{
	private String class1, order, species;
	private String name;
	
	private Animal(String name, String species){
		this(name, species, "Mammalia", "Chordata");
	}

	private Animal(String name, String species, String order){
		this(name, species, order,"Chordata"); 
	}

	private Animal(String name, String species, String order, String class1){
		this.name= name;
		this.species= species;
		this.order= order;
		this.class1= class1;
	}
	
	public void toString(){
		System.out.println("Name: " +name+ "\nSpecies: "+species+ "\nOrder: "+order+"\nClass: "+class1);
	}

	public static void main(String[] args) throws Exception{
		Animal obj1= new Animal("human", "sapien");
		System.out.println("Default value");
		obj1.printDetails();
		Console cons= System.console();
		char flag='y';
		while(flag == 'y'){
			System.out.println("Enter the animal description: ");
			String name=cons.readLine("Name: ");
			String species= cons.readLine("Species: ");
			String order= cons.readLine("Order: ");
			Animal obj= new Animal(name, species, order);
			obj.toString();
			flag=cons.readLine("Do u want to continue??? (y/n)").charAt(0);
			
		}
		System.out.println("Exiting...");

	}
}