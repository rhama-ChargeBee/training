package animal;

import java.util.*;
import java.io.*;


interface setBool{
	public void setWalk(boolean isWalk);
	public void setHerb(boolean isHerb);
}

abstract class Animal implements setBool{
	private boolean isWalk;
	private boolean isHerb;
	public void setWalk(boolean isWalk){
		this.isWalk=isWalk;
	}
	public void setHerb(boolean isHerb){
		this.isHerb=isHerb;
	}
	public boolean getWalk(){
		return isWalk;
	}
	public boolean getHerb(){
		return isHerb;
	}

	abstract String getName();
}

abstract class Mammal extends Animal{
	abstract String getName();
}

abstract class Bird extends Animal{
	abstract String getName();
}

class Bat extends Mammal{
	private static int id=0;
	String name;
	Bat(){
		setWalk(false);
		setHerb(false);
		id++;
		name="Bat"+id;
	}
	public String getName(){
		return name;
	}
}

class Dog extends Mammal{
	private static int id=0;
	String name;
	Dog(){
		setWalk(true);
		setHerb(false);
		id++;
		name="Dog"+id;
	}
	String getName(){
		return name;
	}
}

class Cow extends Mammal{
	private static int id=0;
	String name;
	Cow(){
		setWalk(true);
		setHerb(true);
		id++;
		name="Cow"+id;
	}
	String getName(){
		return name;
	}
}

class Ostrich extends Bird{
	private static int id=0;
	String name;
	Ostrich(){
		setWalk(true);
		setHerb(false);
		id++;
		name="Ostrich"+id;
	}
	String getName(){
		return name;
	}
}

class Parrot extends Bird{
	private static int id=0;
	String name;
	Parrot(){
		setWalk(false);
		setHerb(false);
		id++;
		name="Parrot"+id;
	}
	String getName(){
		return name;
	}
}

public class AnimalDemo{
	public static void main(String[] args) throws Exception{
		LinkedList <Animal> animals= new LinkedList();
		Console cons=System.console();
		int n;
		char ch;
		//Bat obj=new Bat();
		do{
			System.out.println("Enter a new Animal...\nWhich animal?");
			n=findAnimal(); 
			switch(n){
				case 1:
					animals.add(new Bat());
					break;
				case 2: 
					animals.add(new Dog());
					break;
				case 3:
					animals.add(new Cow());
					break;
				case 4:
					animals.add(new Ostrich());
					break;
				case 5:
					animals.add(new Parrot());
					break;
			}
			
			ch=cons.readLine("Do u want to continue??? (y/n)").charAt(0);

		}while(ch=='y');

		//System.out.println(obj.getName()+"\n"+obj.getHerb()+"\n"+obj.getWalk());
		//System.out.println(obj.name+"\t"+ (animals.get(0) instanceof Bat )+ "\tbat1: "+animals.get(0)+"\tbat2: "+animals.get(1));

		
		putAnimals(animals);
		putHerb(animals);
		putFly(animals);
		
	}
	
	private static void putAnimals(List <Animal> animals){
		int i;
		System.out.println("Listing all the Animals...");
		for(i=0; i< animals.size(); i++){
			System.out.println( (Object) animals.get(i).getName() );
		}
	}
	private static void putHerb(List <Animal> animals){
		int i;
		System.out.println("Listing all the Herbivores...");
		for(i=0; i< animals.size(); i++){
			if(animals.get(i).getHerb()){
				System.out.println( animals.get(i).getName() );
			}
		}
	}
	private static void putFly(List <Animal> animals){
		int i;
		System.out.println("Listing all the Animals that can fly...");
		for(i=0; i< animals.size(); i++){
			if(!(animals.get(i).getWalk())){
				System.out.println( animals.get(i).getName() );
			}
		}
	}
	

	private static int findAnimal(){
		System.out.println("1.Bat\n2.Dog\n3.Cow\n4.Ostrich\n5.Parrot");
		Console cons=System.console();
		int n=Integer.parseInt(cons.readLine());
		return n;
	}
	
}

//(Object)animals.get(i).getName()
