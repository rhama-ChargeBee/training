import java.io.Console;

class ClassDemo{
	int i;
	void  method(){
		class LocalClass{
			LocalClass(){
				System.out.println("Local Class");
			}
		}
		LocalClass obj= new LocalClass();
		InnerClass obj2= new InnerClass();

	}
	class InnerClass{
			InnerClass(){
			System.out.println("Inner class");
		}
	}
	class InnerClass1{
                InnerClass1(){
                        System.out.println("Inner class");
                }
        }
}

public class ArrayRev{
	public static void main(String[] args) throws Exception{
		ClassDemo obj= new ClassDemo();
		obj.method();
		Console cons= System.console(); //cons.readline();
		int row, col,i,j;
		char rotation;
		row= Integer.parseInt(cons.readLine("Enter the number of rows/cols: "));
		col= row;
		int[][] arr=new int[row][col];
		int[][] outputArr= new int[row][col];
		System.out.println("Enter the array elements: ");
		for(i=0;i<row;i++){
			for(j=0;j<col;j++){
				arr[i][j]= Integer.parseInt(cons.readLine());
			}
		}
		System.out.println("**************Input array**************");
		for(i=0; i< row; i++){
			for(j=0;j<col ; j++){
				System.out.print(arr[i][j] + " ");
			}
			System.out.println(); 
		}
		rotation=cons.readLine("Rotation left or right?(l/r)").charAt(0);
		if (rotation  == 'l'){
			outputArr = rotateLeft(arr);
		}
		else{
			outputArr= rotateRight(arr);
		}

		System.out.println("Output array is, ");
		for(i=0; i<arr.length; i++){
			for(j=0;j< arr.length ; j++){
				System.out.print(outputArr[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	static int[][] rotateLeft(int[][] arr){
		arr= transpose(arr);
		int j,i,k;
		
		int[] temp= new int[arr.length];
		for(j= 0; j< arr.length ; j++){
			k= arr.length - 1;
			for(i=0; i< arr.length ; i++){
				temp[i]= arr[k][j];
				k--;
			}
			for(i=0;i<arr.length ; i++){
				arr[i][j]= temp[i];
			}
		}
		return arr;
	}

	static int[][] rotateRight(int[][] arr){
		arr = transpose(arr);
		int j,i,k;
		int[]  temp= new int[arr.length];
		for(i=0; i<arr.length; i++){
			k= arr.length-1;
			for(j=0; j< arr.length ; j++){
				temp[j]=arr[i][k];
				k--;
			}
			for(j=0; j<arr.length ; j++){
				arr[i][j]=temp[j];
			}
		}
		return arr;
	}

	static int[][] transpose(int[][] arr){
		int[][] transArr= new int[arr.length][arr.length];
		int i,j;
		for(i=0; i< arr.length ; i++){
			for(j=0; j< arr.length; j++){
				transArr[i][j]=arr[j][i];
			}
		}
		return transArr;
	}
	

}
