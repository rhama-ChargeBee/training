package animal;

import java.util.*;
import java.io.*;

class Cow extends Mammal{
	private static int id=0;
	private String name;
	Cow(){
		setWalk(true);
		setHerb(true);
		id++;
		name="Cow"+id;
	}
	String getName(){
		return name;
	}
}