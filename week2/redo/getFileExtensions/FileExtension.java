package getFileExtensions;

import java.io.*;
import java.util.*;

public class FileExtension{
	private File input;
	private Map <String, Integer> allFiles= new HashMap <String, Integer>();

	FileExtension(File input){
		this.input=input;
	}

	//calls the recursive function traverseDir
    public void traverse(){
    	traverseDir(input);
    }

    //inserts all the files in the directory to the map and also lists all other directories in this directories for recursion
    private void traverseDir(File dirName){
    	File[] filesInDir= dirName.listFiles();
    	for(File file: filesInDir){
    		if(file.isFile()){
    			insertInMap(file);
    		}
    		else{
    			traverseDir(file);
    		}
    	}
    }

    //inserts the given file in to the map according to the extension. if extension exixts, increaments the value, else initialises the value of that extension as 1
    private void insertInMap(File file) {
        String extension= getExtension(file);
        if(allFiles.containsKey(extension)) {
        	allFiles.replace(extension, allFiles.get(extension)+1);
        }
        else{
        	allFiles.put(extension, 1);
        }
    }

    //returns the extension if given a file as input
	private String getExtension(File file){
		try{
	       	String name= file.getName();
    	   	int index = name.lastIndexOf('.');
       		return name.substring(index);
       	}
       	catch(StringIndexOutOfBoundsException e){
       		if( !(file.isDirectory()) ){
       			return "None";
       		}
       		else{
       			return "Dir";
       		}
       	}
    }

    //Converts the map to string
    public String toString(){
    	StringBuilder str= new StringBuilder();
    	for(String key: allFiles.keySet()){
    		str.append(key).append(": ").append(allFiles.get(key)).append("\n");
    	}
    	return str.toString();
    }

	public static void main(String[] args){
		FileExtension obj= new FileExtension(new File("/Users/cb-rhama/chargebee/training/week2/redo/getFileExtensions/sample"));
		obj.traverse();
		System.out.println("File and its Extensions: \n"+ obj.toString());
	}
}