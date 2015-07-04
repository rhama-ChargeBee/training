import java.util.Scanner;

public class InterestCalc{
	private static float calcSi(float p,int n,float r){
		return (p*n*r)/100;
	}
	private static float calcCi(float p, int n, float r){
		float sum=p;
		for(int i = 1; i <= n; i++){
			sum = sum + calcSi(sum,1,r);
		}
		return sum - p;
	}
	public static void main (String[] args) throws Exception{
	    Scanner scan = new Scanner(System.in);
		System.out.print("Enter the principle(in INR): ");
		float p= scan.nextFloat();
		System.out.print("Enter the number of years to calculate interest: ");
		int n= scan.nextInt();
		System.out.print("Enter the Rate of Interest(in percentage): ");
		float r= scan.nextFloat();

		float si= calcSi(p,n,r);
		float ci=calcCi(p,n,r);

		System.out.println("Simple Interest is Rs."+si);
		System.out.println("Compound Interest is Rs."+ci);
	}

}
