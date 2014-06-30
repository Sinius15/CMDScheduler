package com.sinius15.cmdRep;

import java.util.ArrayList;

public abstract class Pattern {
	
	public static ArrayList<Pattern> patterns = new ArrayList<Pattern>();
	
	public Pattern(String name){
		patterns.add(this);
	}
	
	public abstract String getName();
	
	public abstract String replace();
	
}
