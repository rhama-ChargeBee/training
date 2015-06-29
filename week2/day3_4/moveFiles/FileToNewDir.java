package moveFiles;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import static java.nio.file.FileVisitOption.*;
import static java.nio.file.StandardCopyOption.*;


//Copies files from a given directory and subdirectories into a single directory.
public class FileToNewDir extends SimpleFileVisitor<Path> {

        private HashSet <String> allFiles;
        private String newDirString;

        public FileToNewDir(Path newDir) {
            allFiles= new HashSet <String> ();
            this.newDirString= newDir.toString();

            try{
                Files.createDirectory(newDir);
            } catch (FileAlreadyExistsException e){
                System.err.println(e + " in new directory");
            } catch (IOException e){
                System.err.println(e+ " in new directory");
            }
        }

		//called for every file in the walkFileTree(Path, SimpleFileVisitor). copies the file directly if it is a new file. if a file with the same name exists, the subsequent files with the same name are indexed.
        public void copyToDir(Path file) {
            String fileNameWithExtension= file.getFileName().toString();
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
                Files.copy(file, newDir);//, REPLACE_EXISTING, COPY_ATTRIBUTES, ATOMIC_MOVE);
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

        // Invoke the pattern matching
        // method on each file.
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        	copyToDir(file);
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }

        //void main
        public static void main(String[] args) throws Exception {
            Path originalDir = Paths.get("/Users/cb-rhama/chargebee/training/week2/day3_4/moveFiles/sample");
            Path copyToDir=  Paths.get("/Users/cb-rhama/chargebee/training/week2/day3_4/moveFiles/sample_copy/");
            System.out.println("\nCopying Files...");
            FileToNewDir fileToNewDir = new FileToNewDir(copyToDir);
            Files.walkFileTree(originalDir, fileToNewDir);
            System.out.println("\nCopying Done\n");
        }
}
