import java.io.*;

public class AddNumbers{
	public static void main(String[] args) throws Exception{
		int x= Integer.parseInt(args[0]);
		int y, z, xor, ior;
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter the 2nd number: ");
		y=Integer.parseInt(br.readLine());
		z= x+y;
		System.out.println("The sum of the two numbers "+x+" and "+y+" is "+z);
		xor= x^y;
		ior= x|y;
		System.out.println("Xor= "+xor+" Ior= " +ior);
        System.out.println("Hello");
	}
}
