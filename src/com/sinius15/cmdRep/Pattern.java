package com.sinius15.cmdRep;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Pattern {
	
	public ArrayList<Pattern> patterns = new ArrayList();
	
	public Pattern(String name){
		patterns.add(this);
	}
	
	public abstract String getName();
	
	public abstract String replace();
	
}
