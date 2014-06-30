package com.sinius15.cmdRep;

import java.util.HashMap;

public abstract class Pattern {
	
	public HashMap<String, Pattern> patterns = new HashMap<>();
	
	public Pattern(String name){
		patterns.put(name, this);
	}
	
	public abstract String replace();
	
}
