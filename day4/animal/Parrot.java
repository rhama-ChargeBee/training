package animal;

import java.util.*;
import java.io.*;

class Parrot extends Bird{
	private static int id=0;
	private String name;
	Parrot(){
		setWalk(false);
		setHerb(false);
		id++;
		name="Parrot"+id;
	}
	String getName(){
		return name;
	}
}