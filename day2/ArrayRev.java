import java.io.Console;

public class ArrayRev{
	public static void main(String[] args) throws Exception{
		Console cons= System.console(); //cons.readline();
		int row= Integer.parseInt(cons.readLine("Enter the number of rows/cols: "));
		int col= row;
		int[][] arr=new int[row][col];
		int[][] outputArr= new int[row][col];
		System.out.println("Enter the array elements: ");
		for(int i=0;i<row;i++){
			for(int j=0;j<col;j++){
				arr[i][j]= Integer.parseInt(cons.readLine());
			}
		}

		System.out.println("**************Input array**************");
		for(int i=0; i< row; i++){
			for(int j=0;j<col ; j++){
				System.out.print(arr[i][j] + " ");
			}
			System.out.println(); 
		}
		char rotation=cons.readLine("Rotation left or right?(l/r)").charAt(0);
		if (rotation  == 'l'){
			outputArr = rotateLeft(arr);
		}
		else{
			outputArr= rotateRight(arr);
		}

		System.out.println("Output array is, ");
		for(int i=0; i<arr.length; i++){
			for(int j=0;j< arr.length ; j++){
				System.out.print(outputArr[i][j] + " ");
			}
			System.out.println();
		}
		
	}

	private static int[][] rotateLeft(int[][] arr){
		int rowLength= arr.length;
		int colLength= arr[0].length;
		int[][] obj= new int[rowLength][colLength];
		//copying last row to 1st column and so on till we copy the 1st row to last column;
		for(int i=0, j= (rowLength-1); i<colLength;i++,j--){
			for(int k=0;k< rowLength; k++){
				obj[k][i]=arr[j][k];
			}
		}
		return obj;
	}

	private static int[][] rotateRight(int[][] arr){
		int rowLength= arr.length;
		int colLength= arr[0].length;
		int[][] obj= new int[rowLength][colLength];
		// copying the reverse fo first tow to 1st column and so on till the reverse of last row is copied to the last column
		for(int i=0; i<colLength;i++){
			for(int j=0, k= rowLength-1 ;j< rowLength; j++, k--){
				obj[j][i]= arr[i][k];
			}
		}
		return obj;
	}

}
