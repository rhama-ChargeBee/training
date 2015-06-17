package factorial;

import java.io.*;


public class Factorial{
	private Integer lowerLimit;
	private Integer upperLimit;
	Factorial(Integer lowerLimit, Integer upperLimit){
		this.lowerLimit=lowerLimit;
		this.upperLimit= upperLimit;
	}

	private class InnerFactorial implements Iterator{
		public Integer factorial(Integer n){
			Integer f=1;
			while(n>0){
				f=f*n;
				n=n-1;
			}
			return f;
		}
	}

	private InnerFactorial iterator(){
		InnerFactorial obj=new InnerFactorial();
		return obj;
	}
	//To override object class, this function has to be public.
	public String toString(){
		Integer n=lowerLimit;
		String str="";
		InnerFactorial obj=iterator();
		while(n<=upperLimit){
			//System.out.println("n: "+n+"fact(n): "+obj.factorial(n));
			str=str+String.valueOf(obj.factorial(n));
			if(n!=upperLimit){
				str=str+", ";
			}
			n++;
		}
		//System.out.println("str:"+str);
		return str;
	}

	public static void main(String[] args){
		Console cons=System.console();
		Integer lowerLimit, upperLimit;
		lowerLimit= Integer.valueOf(cons.readLine("Enter the lower limit: "));
		upperLimit= Integer.valueOf(cons.readLine("Enter the upper limit: "));
		Factorial obj= new Factorial(lowerLimit, upperLimit);
		System.out.println("The output is\n"+obj.toString());
	}
}