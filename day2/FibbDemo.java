import java.util.Scanner;

public class FibbDemo{
	public static void main (String[] args) throws Exception{
	    	Scanner scan = new Scanner(System.in);
		int n,i;
		int fib=1;
		System.out.print("Enter the number: ");
		n= scan.nextInt();
		for(i=2;i<=n;i++){
			fib=fib*i;
		}
		System.out.println("Fibonacci number is "+fib);
	}

}
