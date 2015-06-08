import java.io.*;
//import java.lang.Math.pow;

public class SmallestPower{
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter x"); 
		int x= Integer.parseInt(br.readLine());
		System.out.println("Enter y");
		int y= Integer.parseInt(br.readLine());
		int a=0;
		//to find the smallest power of x that is greater than y.
		if ((x<=0) || (y<=0)){
			System.out.println("Not applicable");
			a=-1;
		}
		else {
			while(Math.pow(x,a)<y){
			a=a+1;
			}
			System.out.println("The smallest power of "+x+" that is greater than "+y+" is "+ a);
		}
}
}
