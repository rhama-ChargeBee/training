import java.util.Scanner;

public class StringAndScanner{
	public static void main (String[] args) throws Exception{
	    Scanner scan = new Scanner(System.in);
		System.out.print("Enter your the string: ");
		String inpStr = scan.next().toLowerCase();
		int sum=0;
		char a='a';
		for(int i=0;i<inpStr.length();i++){
			sum=sum+ (int) inpStr.charAt(i) -(int) a + 1;
			}
		if(sum % 2 == 0){
			System.out.println("Even");
			}
		else{
			System.out.println("Odd");
			}
		}

}
