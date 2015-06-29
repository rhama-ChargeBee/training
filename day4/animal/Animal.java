package animal;

import java.util.*;
import java.io.*;

public abstract class Animal implements setBool{
	private boolean isWalk;
	private boolean isHerb;
	public void setWalk(boolean isWalk){
		this.isWalk=isWalk;
	}
	public void setHerb(boolean isHerb){
		this.isHerb=isHerb;
	}
	public boolean getWalk(){
		return isWalk;
	}
	public boolean getHerb(){
		return isHerb;
	}

	public abstract String getName();
}