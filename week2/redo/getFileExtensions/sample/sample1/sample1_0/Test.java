import java.util.*;



public class Test{
	public static void main(String[] args){
		ArrayList <String> l= new <String> ArrayList();
		String[] stringArray= {"Bat", "dog", "cat", "hat"};
		HashMap hm= new HashMap();
		//l=(List)hm;
		String s= new String("s");
		String s1= s;
		String s2= "s";
		String s3= "s";
		String s4= new String("s").intern();
		int[] ar={10,20,30,50};
		int  flag=0;
		/*
		System.out.println(s==s1);
		System.out.println(s1==s2);
		System.out.println(s.equals(s1));
		System.out.println(s1.equals(s2));
		System.out.println(s2=="s");
		System.out.println(s3==s4);
		*/
		l.add("Hello");
		l.add("MAp");
		l.add(new String("Mapping"));
		l.add(String.valueOf(3));

		for(int j: ar){
			l.add(String.valueOf(j));
		}

		System.out.println("ArrayList\n*****************part1*******************");
		for(String str: l){
			/*
			if(flag==2){
				l.add("inbetween1");
			}
			flag++;
			*/
			System.out.println(str);
		}
		flag=0;
		System.out.println("*******************part2********************");
		Iterator testIterator= l.iterator();
		while(testIterator.hasNext()){
			if(flag==2){
				testIterator.remove();
			}
			flag++;
			System.out.println((String) testIterator.next());
			//System.out.println(testIterator.next()+"?");
		}
		flag=0;
		System.out.println("*******************part3********************");
		for(int i=0;i<l.size(); i++, flag++){
			if(flag==2){
				l.add("inbetween3");
			}
			flag++;
			System.out.println(l.get(i));
		}

		System.out.println("\nStringArray\n*****************part1*******************");
		for(String str: stringArray){
			System.out.println(str);
		}
		/*
		System.out.println("*******************part2********************");
		Iterator stringIterator= stringArray.iterator();
		while(testIterator.hasNext()){
			System.out.println(stringIterator.next());
			System.out.println(stringIterator.next()+"?");
		}
		*/


	}
}