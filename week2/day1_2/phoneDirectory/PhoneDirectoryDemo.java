package phoneDirectory;

import java.util.*;

public class PhoneDirectoryDemo{
	public static void main(String[] args){
		String[][] inputStringArray= new String[3][5];

		inputStringArray[0][0]="Bright Wayn";
		inputStringArray[0][1]="Chennai";
		inputStringArray[0][2]="1234567890";
		inputStringArray[0][3]="1212121212";
		inputStringArray[0][4]="2121212121";

		inputStringArray[1][0]="Donald";
		inputStringArray[1][1]="Warner Tower";
		inputStringArray[1][2]="1020304050";
		inputStringArray[1][3]="2040302010";
		inputStringArray[1][4]="1231231234";

		inputStringArray[2][0]="Bruce Light";
		inputStringArray[2][1]="Gothem City";
		inputStringArray[2][2]="1213214323";
		inputStringArray[2][3]="1342345234";
		inputStringArray[2][4]="1232343456";

		ArrayList <Entry> directory= new ArrayList <Entry> ();
		for(String[] entry: inputStringArray){
			directory.add(new Entry(entry[0],entry[1], Integer.parseInt(entry[2]), Integer.parseInt(entry[3]), Integer.parseInt(entry[4]) ));
		}

		PhoneDirectory obj= new PhoneDirectory(directory);
		obj.userOptions();
	}
}