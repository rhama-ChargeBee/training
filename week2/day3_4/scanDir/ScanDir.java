package moveFiles;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.nio.file.attribute.*;
import static java.nio.file.FileVisitResult.*;
import static java.nio.file.FileVisitOption.*;

public class ScanDir {
    public static void main(String[] args) throws IOException {
        Path startingDir = Paths.get("/Users/cb-rhama/chargebee/training/week2/day3_4/scanDir/sample");
        FileToMap fileToMap = new FileToMap();
        Files.walkFileTree(startingDir, fileToMap);
        fileToMap.display();
    }
}
