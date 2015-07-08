import java.util.Scanner;

public class Pattern{
	public static void main(String[] args) throws Exception{
		Scanner scan= new Scanner(System.in);
		System.out.print("Enter the max number of rows: ");
		int maxRow=scan.nextInt();
		int maxCol= ( maxRow*2 ) ;
		printPattern(maxRow, maxCol);
	}
	private static void printPattern(int row, int col){
		int n=col+1;
		for(int i=0; i<row; i++){
			int k=0;
			for(int j=1; j<col; j++){
				if( (j>= (n/2) - i) && (j<= (n/2))){
					k=k+1;
					System.out.print(k + " ");
				}
				else if( (j>n/2) && (j<= (n/2)+i )){
					k=k-1;
					System.out.print( k + " ");
				}
				else{
					System.out.print("  ");
				}
			}
			System.out.println();
		}
	}
}
