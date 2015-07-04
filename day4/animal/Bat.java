package animal;

import java.util.*;
import java.io.*;

class Bat extends Mammal{
	private static int id=0;
	private String name;
	public Bat(){
		setWalk(false);
		setHerb(false);
		id++;
		name="Bat"+id;
	}
	public String getName(){
		return name;
	}
}