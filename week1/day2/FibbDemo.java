import java.util.Scanner;

public class FibbDemo{
	public static void main (String[] args) throws Exception{
	    	Scanner scan = new Scanner(System.in);
		int n1=0,n2=1, temp, n;
		int fib=1;
		System.out.print("Enter the number: ");
		n= scan.nextInt();
		System.out.print("Fibonacci series is ");
		while(n1<=n){
			System.out.print(n1+" ");
			temp=n1;
			n1=n1+n2;
			n2=temp;
		}
		System.out.println();
	}
}
