package animal;

import java.util.*;
import java.io.*;

class Cow extends Mammal{
	private static int id=0;
	private String name;
	public Cow(){
		setWalk(true);
		setHerb(true);
		id++;
		name="Cow"+id;
	}
	public String getName(){
		return name;
	}
}