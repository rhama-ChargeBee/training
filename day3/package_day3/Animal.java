package package_day3;

import java.io.Console;

public class Animal{
	
	public static void main(String[] args) throws Exception{
		AnimalProp obj1= new AnimalProp("human", "sapien");
		System.out.println("Default value");
		obj1.printDetails();
		Console cons= System.console();
		String name, species, order;
		char flag='y';
		while(flag == 'y'){
			System.out.println("Enter the animal description: ");
			name=cons.readLine("Name: ");
			species= cons.readLine("Species: ");
			order= cons.readLine("Order: ");
			AnimalProp obj= new AnimalProp(name, species, order);
			flag=cons.readLine("Do u want to continue??? (y/n)").charAt(0);
			obj.printDetails();
		}
		System.out.println("Exiting");

	}
}


class AnimalProp{
	
	static int count=0;
	String class1, order, species;
	String name;
	
	AnimalProp(String name, String species){
		this(name, species, "Mammalia", "Chordata");
	}

	AnimalProp(String name, String species, String order){
		this(name, species, order,"Chordata"); 
	}

	AnimalProp(String name, String species, String order, String class1){
		this.name= name;
		this.species= species;
		this.order= order;
		this.class1= class1;
	}
	
	void printDetails(){
		System.out.println("Name: " +name+ "\nSpecies: "+species+ "\nOrder: "+order+"\nClass: "+class1);
	}
}
