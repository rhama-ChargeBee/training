package scanDir;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import static java.nio.file.FileVisitOption.*;

public class FileToMap extends SimpleFileVisitor<Path> {

        private Map <String, ArrayList <String> > allFiles;

        FileToMap() {
        	allFiles= new HashMap <String, ArrayList <String>> ();
        }

		//Given the path, returns the file name without the extension
        private String getFileWithoutExtension(Path file){
        	String fileName, name;
        	int index;
        	name= file.getFileName().toString();
        	index = name.lastIndexOf('.');
        	fileName = name.substring(0,index);
        	return fileName;
        }

        //Given the path, returns the extension of the file in that path
        private String getExtension(Path file){
        	String extension, name;
        	int index;
        	name= file.getFileName().toString();
        	index = name.lastIndexOf('.');
        	extension = name.substring(index);
        	return extension;
        }

        //Inserts the given key value pair into the Map
        private void insert(String extension, String fileName){
			if(!(allFiles.keySet().contains( extension ))) {
				allFiles.put(extension, new ArrayList <String> ());
			}
			allFiles.get(extension).add(fileName);
		}

		//Takes the path as input and finally inserts the extension as the key and the filename as value.
        private void insertInMap(Path file) {
            String fileName, extension;
            int index;
            //System.out.println(file.getFileName().toString());
            fileName= getFileWithoutExtension(file);
            extension= getExtension(file);
            insert(extension,fileName);
        }

        //Displays the extensions with the number of files in each extension.
        public void display(){
        	for(String extension: allFiles.keySet()){
        		System.out.println(extension + ": "+ allFiles.get(extension).size());
        	}
        }

        // Invoke the pattern matching
        // method on each file.
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        	if(!(Files.isDirectory(file))) {
        		insertInMap(file);
        	}
        	//System.out.println("File func:" + file.getFileName().toString());
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }
    }