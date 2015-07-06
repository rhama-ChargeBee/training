import java.lang.*;
import java.nio.file.*;
import java.io.*;

import static java.nio.file.StandardCopyOption.*;

public class FileOp
{
	public static void main(String[] args) {
		Path path= Paths.get("//Users/cb-rhama/chargebee/training/week2/day1_2/mapwords/MapWordsDemo.java");
		//Path path1= Paths.get("day1_2/stringmap/StringMap.java");
		System.out.format("toString: %s%n", path.toString());	
		System.out.format("getFileName: %s%n", path.getFileName());
		System.out.format("getName(0): %s%n", path.getName(1));
		System.out.format("getNameCount: %d%n", path.getNameCount());
		System.out.format("subpath(0,2): %s%n", path.subpath(0,2));
		System.out.format("getParent: %s%n", path.getParent());
		System.out.format("getRoot: %s%n", path.getRoot());
		System.out.println("URI: "+ path.toUri());
		
		//System.out.println("P1 to p2:"+ path.relativize(path1));
		//System.out.println("P2 tp p1"+ path1.relativize(path));
		for (Path name: path) {
    		System.out.println(name);
		}

		Path path1= Paths.get("/Users/cb-rhama/chargebee/training/week2/day3_4/scanDir/sample/sample0/world.txt");
		Path path2= Paths.get("/Users/cb-rhama/chargebee/training/week2/day3_4/scanDir/sample/sample1/hello.txt");
		//Files.copy(path1,path2);
		try {
			System.out.println("\nToReal:"+ path.toRealPath());
    		Path tempFile = Files.createTempFile(null, ".myapp");
    		System.out.format("The temporary file" + " has been created: %s%n", tempFile);
    		System.out.println("\nCopied");
    		Files.copy(path1,path2);
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
		}
		
	}
}