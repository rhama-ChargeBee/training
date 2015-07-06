package copyToDir;

import java.io.*;
import java.util.*;
import java.nio.file.*;

class CopyToDir{
	private HashSet <String> allFiles= new HashSet <String> ();
	private String newDirString;
	private File input;

	public CopyToDir(File input, File output) {
		this.input=input;
		this.newDirString= output.toString();
		try{
			Files.createDirectory(output.toPath());
		} catch (FileAlreadyExistsException e){
			System.err.println(e + " in new directory");
		} catch (IOException e){
			System.err.println(e+ " in new directory");
		}
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
    			copyFileToDir(file);
    		}
    		else{
    			traverseDir(file);
    		}
    	}
    }

	public void copyFileToDir(File file) {
		String fileNameWithExtension= file.getName().toString();
		int indexOfFileName = fileNameWithExtension.lastIndexOf('.');
		String fileName= fileNameWithExtension.substring(0,indexOfFileName);
		Path newDir;
		System.out.println("Copying "+fileNameWithExtension+"...");

        //To check if a file with the same name exists
        //If the file with the same name does not exist, create a new file with the same name
		if(!(allFiles.contains(fileName) ) ){
			newDir = Paths.get(newDirString + "/" +fileNameWithExtension);
			allFiles.add(fileName);
		}
        //if the file with the same name exists, find the index (stored in post numbering) and then append it to the new file name
		else{
			int postNumbering= getPostNumbering(fileName);
			newDir= Paths.get(newDirString+  "/" +fileName + String.valueOf(postNumbering) + fileNameWithExtension.substring(indexOfFileName));
			allFiles.add(fileName+ String.valueOf(postNumbering));
		}
		//copying file to the destination.
		try{
            Files.copy(file.toPath(), newDir, StandardCopyOption.REPLACE_EXISTING); //, REPLACE_EXISTING, COPY_ATTRIBUTES, ATOMIC_MOVE);
		} catch (IOException e){
			System.err.println("***********"+e + " in copy file************");
		}
	}

	//To find the index of the new file to be created if a file with the same name exists.
	private int getPostNumbering(String fileName){
		int count=1;
		for(String arrayFile: allFiles){
			if(arrayFile.contains(fileName) && !(arrayFile.equals(fileName)) ){
				String temp = arrayFile.replace(fileName, "");
				if(isInteger(temp)){
					count++;
				}
			}
		}
		return count;
	}
	//To check if the given string can be converted to an integer
	private boolean isInteger(String s) {
		try { 
			Integer.parseInt(s); 
		} catch(NumberFormatException e) { 
			return false; 
		} catch(NullPointerException e) {
			return false;
		}
		return true;
	}	

	public static void main(String[] args){
		File input=new File("/Users/cb-rhama/chargebee/training/week2/redo/getFileExtensions/sample");
		File output= new File("/Users/cb-rhama/chargebee/training/week2/redo/copyToDir/sample_output");
		CopyToDir obj= new CopyToDir(input,output);
		obj.traverse();
		System.out.println("Done!!!");
	}
}