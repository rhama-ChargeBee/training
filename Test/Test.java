import java.util.*;

public class Test{
	private Test(){

	}

	public void put(){
		Test obj= new Test();
	}
	public static void main(String[] args) throws Exception{
		Integer varInteger=3;
		Long varLong= 3040506070l;
		int varInt=  varLong.intValue();
		System.out.println(varLong+"\t"+varInt);
		TempClass obj1=new TempClass(0x58);
		TempClass obj2= new TempClass(0x50);

		//String str1=new String("Hello");
		//String str2= (String) str1.clone();

		TempClass obj3= (TempClass) ((TempClass) obj1).clone();
		System.out.println("obj1: "+obj1+"\tobj2: "+obj2);
		System.out.println(obj1.equals(obj3));
		obj1.varArgs("Hello", "World", "How", "Are", "You");
		Test newobj= new Test();
		newobj.put();

		ArrayList <String> array= new ArrayList <String>();
		array.add("Hello");
		array.add("World");
		array.add("Everyone");
		
		array.add(1,"inbetween");
		System.out.println("***************************");
		for(String str: array){
			System.out.println(str);
		}
	}
}
class TempClass implements Cloneable{
	private int data;
	//public int length=1;
	TempClass(int data){
		this.data=data;
	}
	public String toString(){
		char c= (char) data;
		return String.valueOf(c);
	}
	public Object clone() throws CloneNotSupportedException{
		return (TempClass)super.clone();
	}
	public void varArgs(String ... str){
		for(String val:str){
			System.out.print(val+"\t");
		}
		System.out.println();
	}
}
//0 10001011000111100101010
//0 01110100111000011010110