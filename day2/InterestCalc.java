import java.util.Scanner;

public class InterestCalc{
	private static float calcSi(float p,int n,float r){
		return (p*n*r)/100;
	}
	private static float calcCi(float p, int n, float r){
		float ci;
		float sum=p;
		int i;
		for(i = 1; i <= n; i++){
			sum = sum + calcSi(sum,1,r);
		}
		ci = sum - p;
		return ci;
	}
	public static void main (String[] args) throws Exception{
	    Scanner scan = new Scanner(System.in);
		float p, r, si, ci;
		int n,i;
		System.out.print("Enter the principle(in INR): ");
		p= scan.nextFloat();
		System.out.print("Enter the number of years to calculate interest: ");
		n= scan.nextInt();
		System.out.print("Enter the Rate of Interest(in percentage): ");
		r= scan.nextFloat();
		si= (p*n*r)/100;
		ci=calcCi(p,n,r);
		System.out.println("Simple Interest is Rs."+si);
		System.out.println("Compound Interest is Rs."+ci);
	}

}
