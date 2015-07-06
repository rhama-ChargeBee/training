import java.util.*;

public class TestCollection{
  public static void main(String args[]){  
   
  HashMap<Integer,String> hm=new HashMap<Integer,String>();  
  
  hm.put(100,"Amit");  
  hm.put(101,"Vijay");  
  hm.put(102,"Rahul"); 
  hm.put(102,"Ajay") ;
  
  for(Map.Entry m:hm.entrySet()){  
   System.out.println(m.getKey()+" "+m.getValue());  
  }  
  hm.replaceAll((k,v) -> funct(v));
  for(Map.Entry m:hm.entrySet()){  
   System.out.println(m.getKey()+" "+m.getValue());  
  }  
 }  
 private static String funct(String strs){
 	return strs+" hello";
 }
}