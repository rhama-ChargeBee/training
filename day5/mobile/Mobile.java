/*
Class Mobile:
Members
-name : String
-remainingCharge : Integer
Methods:
+remainingCharge(): void
+name() : void
Create a list of mobiles overriding the remainingCharge() for each mobile(random value >0&<100). 
Print the mobile name and remaining charge. Use anonymous inner class for creating mobile objects.
*/
package mobile;

import java.util.*;
import java.io.*;

public class Mobile{
	private static List <Mobile> mobileList= new ArrayList <Mobile>();
	private String name;
	private Integer remainingCharge;

	public void remainingCharge(){
		System.out.println("Remaining Charge: "+ remainingCharge);
	}
	
	public void name(){
		System.out.print("Name: "+name+"\t");
	}

	private void setRemainingCharge(Integer remainingCharge){
		this.remainingCharge=remainingCharge;
	}

	private void setName(String name){
		this.name=name;
	}

	private static MobileInterface mobileAnonymousObj=new MobileInterface(){
		public Mobile getMobile(String name){
			Random r= new Random();
			Mobile temp=new Mobile();
			temp.setName(name);
			temp.setRemainingCharge(r.nextInt(100));
			return temp;
		}
	};

	public static void assignValues(int numberOfMobiles){
		Console cons=System.console();
		Mobile obj= new Mobile();
		for(int i=0;i< numberOfMobiles; i++){
			String name= cons.readLine("Enter name"+(i+1)+": ");
			mobileList.add( mobileAnonymousObj.getMobile(name) );
		}
	}
	public static void printValues(int numberOfMobiles){
		for(int i=0;i< numberOfMobiles; i++){
			mobileList.get(i).name();
			mobileList.get(i).remainingCharge();
		}
	}

	public static void main(String[] args){
		assignValues(3);
		printValues(3);
	}
}
