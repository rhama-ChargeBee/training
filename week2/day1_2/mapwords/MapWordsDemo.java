package mapwords;

import java.util.*;

public class MapWordsDemo{
	public static void main(String[] args){
		ArrayList <String> givenList= new ArrayList <String> ();
		addWords(givenList);
		MapWords obj=new MapWords(givenList);
		obj.mapWords();
		obj.display();
	}

	private static void addWords(ArrayList <String> givenList){
		givenList.add("Map");
		givenList.add("Mapping");
		givenList.add("Mapped");
		givenList.add("World");
		givenList.add("Well");
		givenList.add("Hello");
		givenList.add("Help");
		givenList.add("Held");
		givenList.add("Halt");
		givenList.add("Sing");
	}
}