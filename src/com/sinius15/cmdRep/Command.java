package com.sinius15.cmdRep;

public abstract class Command {
	
	public Command(){
		
	}
	
	public abstract String getName();
	
	public abstract void call();
	
}
