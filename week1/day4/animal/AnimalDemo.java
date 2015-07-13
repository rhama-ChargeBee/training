
package animal;

import java.util.*;

public class AnimalDemo{
	public static void main(String[] args) throws Exception{
		ArrayList <Animal> animals= new ArrayList <> ();
		Scanner scan = new Scanner(System.in);
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
                        System.out.println("Do u want to continue??? Hghjghjjhello world hjghjghjghjghjh (y/n)");
			try{
                            ch=scan.next().charAt(0);
                        }catch(Exception e){
                            ch='n';
                            System.err.println("Exception in ch: "+e);
                            e.printStackTrace();
                        }
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
            int n;
            System.out.println("1.Bat\n2.Dog\n3.Cow\n4.Ostrich\n5.Parrot");
            try{
		Scanner scan = new Scanner(System.in);
		n= scan.nextInt();
		return n;
            }catch(Exception e){
                n=1;
                System.err.println("Exception in findAnimal(): "+e);
                e.printStackTrace();
            }
            return n;
	}
	
}

//(Object)animals.get(i).getName()
