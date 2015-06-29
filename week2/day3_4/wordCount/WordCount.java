package wordCount;

import java.lang.*;
import java.util.*;
import java.io.*;


public class WordCount{
	private Scanner inputFile;
	private Map <String, Integer> words= new HashMap <String, Integer> ();

	public WordCount(String file){
		try{	
			inputFile= new Scanner(new BufferedReader( new FileReader(file)));
		} catch(FileNotFoundException e){
			System.err.println(e);
		}
	}

	private void insertInMap(String key){
		Integer value;
		if(words.keySet().contains(key)){
			value=words.get(key);
			words.replace(key, (value+1) );
		}
		else{
			words.put(key, 1);
		}
	}

	public void readFile(){
		inputFile.useDelimiter("\\s* | \\\" | .* | \\* | ,* | \\\'* | \\n* ");
		while(inputFile.hasNext()){
			String key= inputFile.next();
			insertInMap(key);
		}
		inputFile.close();
	}

	public void putInFile(){
		File output= new File("/Users/cb-rhama/chargebee/training/week2/day3_4/wordCount/output.txt");
		PrintWriter pw;
		try{
			output.createNewFile();
		}
		catch(Exception e){
			System.err.println(e);
		}

		try{
			pw= new PrintWriter(output);
			for(String key: words.keySet()){
				System.out.println( key+ "\t" +String.valueOf(words.get(key)) );
    			pw.print( key+ "\t" +String.valueOf(words.get(key)) + "\n" );
    		}
    		pw.close();
    	} catch(IOException e){
    		System.err.println(e);
		}
    	
	}

	public static void main(String[] args){
		WordCount obj= new WordCount("/Users/cb-rhama/chargebee/training/week2/day3_4/wordCount/sample.txt");
		obj.readFile();
		obj.putInFile();
	}
}