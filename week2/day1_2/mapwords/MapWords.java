package mapwords;

import java.util.*;

public class MapWords{
	private Map <String, TreeSet <String>> map;
	private ArrayList <String> givenList;
	MapWords(ArrayList <String> givenList){
		this.givenList=givenList;
		map= new TreeMap <String, TreeSet <String>> ();
		//System.out.println("Test Constructor");
	}

	private void checkArray(String prefix){
		//System.out.println("Test checkArray");
		TreeSet <String> tempSet= new TreeSet <String> ();
		String tempWord;
		String prefixLowerCase= prefix.toLowerCase();
		int i;
		int len= givenList.size();
		int prefixLength= prefix.length();
		for(i=0; i<len; i++){
			tempWord = givenList.get(i).toLowerCase();
			if(tempWord.contains(prefixLowerCase)){
				if(prefixLowerCase.equals(tempWord.substring(0,prefixLength))){
					tempSet.add(tempWord);
				}
			}
		}
		if(tempSet.size() >1){
			map.put(prefix, tempSet);
		}
	}

	private void listAllPrefixes(String word){
		//System.out.println("Test listAllPrefixes");
		int i;
		int len= word.length();
		for(i=len;i>0;i--){
			checkArray(word.substring(0,i));
		}
	}

	private void createMap(){
		//System.out.println("Test createMap");
		for(String temp: givenList){
			listAllPrefixes(temp);
		}
	}
	public void display(){
		createMap();
		System.out.println("Mapping Words");
		//Map<String, String> treeMap = new TreeMap<String, String>(map);
		for(String key: map.keySet()){
			System.out.print(key+"\t");
			for(String value: map.get(key)){
				System.out.print(value+" ");
			}
			System.out.println();
		}
	}

}