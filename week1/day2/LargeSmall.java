import java.util.Scanner;
import java.util.Arrays;

public class LargeSmall{
	public static void main (String[] args) throws Exception{
	    Scanner scan = new Scanner(System.in);
		System.out.print("Enter the number of elements: ");
		int n= scan.nextInt();
		int[] arr=new int[n];
		System.out.println("Enter the array numbers: ");
		for(int i=0;i<n;i++){
			arr[i]=scan.nextInt();
		}
		Arrays.sort(arr);
		System.out.println("Min: "+ arr[0]+"\t Max: " + arr[n-1]);
		
	}

}
