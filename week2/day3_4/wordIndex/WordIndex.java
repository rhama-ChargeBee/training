package wordIndex;

import java.lang.*;
import java.util.*;
import java.io.*;


public class WordIndex{
	BufferedReader inputFile;
	String searchWord;
	String inputFileName;
	Map <Integer, ArrayList <Integer> > words= new HashMap <Integer, ArrayList <Integer> > ();

	WordIndex(String file, String searchWord){
		this.searchWord=searchWord;
		inputFileName=file;
		try{	
			inputFile= new BufferedReader(new FileReader(file));
		} catch(FileNotFoundException e){
			System.err.println(e);
		}
	}

	private void insertIndex(String line, int lineNumber){
		words.put(Integer.valueOf(lineNumber), new ArrayList <Integer> () );
		Integer index;
		index= Integer.valueOf(line.indexOf(searchWord));
		while(index!=-1){
			System.out.print(index+" ");
			words.get(lineNumber).add(index);
			index=Integer.valueOf(line.indexOf(searchWord, index+1));
		}
		System.out.println();
	}

	public void readFile(){
		String line;
		int i=1;
		try{
			while( ( line=inputFile.readLine() ) != null ){
				System.out.print(i+": ");
				insertIndex(line, i);
				i++;
			}	
		}catch(IOException e){
			System.err.println(e);
		}
		
	}

	private File getOutputFile(String fileName){
		//String fileName= inputFile.toString();
		int index=  fileName.lastIndexOf('/');
		String newFileName= fileName.substring(0,index)+"/"+searchWord+".locations.txt";
		return ( new File(newFileName) );
	}

	private String getArrayString(Integer key){
		StringBuilder str= new StringBuilder();
		for(Integer index: words.get(key) ){
			str=str.append(index + " ");
		}
		return str.toString();
	}

	public void putInFile(){
		File output= getOutputFile(inputFileName);
		PrintWriter pw;
		try{
			output.createNewFile();
		}
		catch(Exception e){
			System.err.println(e);
		}

		try{
			pw= new PrintWriter(output);
			for(Integer key: words.keySet()){
				if(words.get(key).size() != 0){
					pw.print( key + ": "+ getArrayString(key) + "\n");
				}
    		}
    		pw.close();
    	} catch(IOException e){
    		System.err.println(e);
    	} 
    	
	}

	public static void main(String[] args){
		WordIndex obj= new WordIndex("/Users/cb-rhama/chargebee/training/week2/day3_4/wordIndex/sample.txt", "the");
		obj.readFile();
		obj.putInFile();
	}
}