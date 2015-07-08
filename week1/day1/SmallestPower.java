import java.io.*;
//import java.lang.Math.pow;

public class SmallestPower{
	public static void main(String[] args) throws Exception{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter x"); 
		int x= Integer.parseInt(br.readLine());
		System.out.println("Enter y");
		int y= Integer.parseInt(br.readLine());
		int a=1;
		//to find the smallest power of x that is greater than y.
		if ((x<=0) || (y<=0)){
			System.out.println("Not applicable");
		}
		else {
			while(powCalc(x,a)<y){
				a=a+1;
			}
			System.out.println("The smallest power of "+x+" that is greater than "+y+" is "+ a);
		}
	}

	private static int powCalc(int x, int a){
		int y=1;
		for(int i=0;i<a; i++){
			y=x*y;
		}
		return y;
	}
}
