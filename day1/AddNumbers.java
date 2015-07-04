import java.io.*;

public class AddNumbers{
	public static void main(String[] args) throws Exception{
		int x= Integer.parseInt(args[0]);
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the 2nd number: ");
		int y=Integer.parseInt(br.readLine());
		int z= x+y;
		System.out.println("The sum of the two numbers "+x+" and "+y+" is "+z);
		int xor= x^y;
		int ior= x|y;
		System.out.println("Xor= "+xor+" Ior= " +ior);
        System.out.println("Hello");
	}
}
