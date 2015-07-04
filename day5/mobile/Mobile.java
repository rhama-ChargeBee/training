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

	private MobileInterface interfaceObj=new MobileInterface(){
		List <Mobile> mobileList= new ArrayList();
		int numberOfMobiles=3;
		Console cons=System.console();

		public void assignValues(){
			Random r= new Random();
			
			for(int i=0;i< numberOfMobiles; i++){
				String name= cons.readLine("Enter name"+(i+1)+": ");
				Mobile temp=new Mobile();
				temp.setName(name);
				temp.setRemainingCharge( r.nextInt(100) );
				mobileList.add(temp);
			}
		}

		public void printValues(){
			int i;
			for(i=0;i< numberOfMobiles; i++){
				//System.out.println("Index: "+i);
				mobileList.get(i).name();
				mobileList.get(i).remainingCharge();
			}
		}
	};

	public void output(){
		interfaceObj.assignValues();
		interfaceObj.printValues();
	}
}