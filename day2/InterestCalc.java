import java.util.Scanner;

class CompoundInterest{
	float p,r,ci;
	int n;
	CompoundInterest(float pr, int ny, float rate){
		p=pr;
		n=ny;
		r=rate;
	}
	float getValue(){
		calcCi();
		return ci;
	}
	float calcSi(float pr,int ny,float rate){
		return (pr*ny*rate)/100;
	}
	void calcCi(){
		float sum=p;
		int i;
		for(i = 1; i <= n; i++){
			sum = sum + calcSi(sum,1,r);
		}
		ci = sum - p;
	}	
}

public class InterestCalc{
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
		CompoundInterest obj= new CompoundInterest(p,n,r);
		ci=obj.getValue();
		System.out.println("Simple Interest is Rs."+si);
		System.out.println("Compound Interest is Rs."+ci);
	}

}
