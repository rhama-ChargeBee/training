package animal;

import java.util.*;
import java.io.*;

class Dog extends Mammal{
	private static int id=0;
	private String name;
	Dog(){
		setWalk(true);
		setHerb(false);
		id++;
		name="Dog"+id;
	}
	String getName(){
		return name;
	}
}