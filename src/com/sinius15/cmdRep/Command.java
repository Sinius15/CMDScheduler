package com.sinius15.cmdRep;

import java.util.HashMap;

public abstract class Command {
	
	public static HashMap<String, Command> commands = new HashMap<>();
	
	public Command(){
		commands.put(getName(), this);
	}
	
	public abstract String getName();
	
	public abstract void call();
	
}
