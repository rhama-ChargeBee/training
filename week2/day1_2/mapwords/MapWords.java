package mapwords;

import java.util.*;

public class MapWords{
	private Map <String, TreeSet <String>> map= new TreeMap <String, TreeSet <String>> ();;
	private ArrayList <String> givenList;

	public MapWords(ArrayList <String> givenList){
		this.givenList=givenList;
	}

	public void mapWords(){
		for(String value: givenList){
			insertWord(value);
		}
	}

	private void insertWord(String word){
		String key= word.substring(0,3);
		if( !(map.containsKey(key) ) ) {
			map.put(key, new TreeSet <String> () );
		}
		map.get(key).add(word);
	}

	public void display(){
		System.out.println("Mapping Words");
		for(String key: map.keySet()){
			System.out.print(key+"\t");
			for(String value: map.get(key)){
				System.out.print(value+" ");
			}
			System.out.println();
		}
	}

}