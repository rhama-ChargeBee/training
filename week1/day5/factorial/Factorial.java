package factorial;

import java.io.*;
import java.util.*;


public class Factorial implements Iterable{
	private Integer lowerLimit;
	private Integer upperLimit;
	private List <Long> factorialList= new ArrayList <Long>();
	public Factorial(Integer lowerLimit, Integer upperLimit){
		this.lowerLimit=lowerLimit;
		this.upperLimit= upperLimit;
	}

	public Iterator iteratorObject= new Iterator(){
		private int i=0;
		public boolean hasNext(){
			return i<factorialList.size()? true:false;
		}
		public Long next(){
			Long nextVal=null;
			try{
				nextVal= factorialList.get(i);
				i++;
			}
			catch(Exception e){
				i=0;
				System.err.println(e);
			}
			return nextVal;
		}

		public void remove(){
			try{
				factorialList.remove(i);
			}catch(Exception e){
				System.err.println(e);
			}
		}
	};

	public Iterator iterator(){
		return iteratorObject;
	}

	public void calculateFactorial(){
		int n=lowerLimit;
		while(n<=upperLimit){
			factorialList.add(factorial(n));
			n++;
		}
	}

	private Long factorial(int n){
		Long f=1l;
		while(n>0){
			f=f*n;
			n--;
		}
		return f;
	}

	public String toString(){
		StringBuilder str=new StringBuilder();
		for(Long fact: factorialList){
			str.append(fact).append(", ");
		}
		return str.toString();
	}

	

	public static void main(String[] args){
		Console cons=System.console();
		Integer lowerLimit, upperLimit;
		lowerLimit= Integer.valueOf(cons.readLine("Enter the lower limit: "));
		upperLimit= Integer.valueOf(cons.readLine("Enter the upper limit: "));
		Factorial obj= new Factorial(lowerLimit, upperLimit);
		obj.calculateFactorial();
		System.out.println("The output is\n"+obj.toString());
		Iterator iter= obj.iterator();
		System.out.println("Printing using Iterator...");
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
	}
}