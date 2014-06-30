package com.sinius15.cmdRep;

import java.util.HashMap;

public abstract class Command {
	
	public static HashMap<String, Command> commands = new HashMap<>();
	
	public Command(String name){
		commands.put(name, this);
	}
	
	
	public abstract void call();
	
}
