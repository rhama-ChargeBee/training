import java.util.Scanner;

public class Pattern{
	public static void main(String[] args) throws Exception{
		Scanner scan= new Scanner(System.in);
		int maxRow, maxCol;
		System.out.print("Enter the mox number of rows: ");
		maxRow=scan.nextInt();
		maxCol= ( maxRow*2 ) - 1;
		printPattern(maxRow, maxCol);
	}
	static void printPattern(int row, int col){
		int i,j,k,n;
		n=col+1;
		for(i=0; i<row; i++){
			k=0;
			for(j=1; j<col; j++){
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
