package animal;

import java.util.*;
import java.io.*;

class Ostrich extends Bird{
	private static int id=0;
	private String name;
	public Ostrich(){
		setWalk(true);
		setHerb(false);
		id++;
		name="Ostrich"+id;
	}
	public String getName(){
		return name;
	}
}