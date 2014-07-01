package com.sinius15.cmdRep.commands;

import com.sinius15.cmdRep.Command;

public class PrintCommand extends Command{

	public PrintCommand() {
		super("print");
	}

	@Override
	public boolean call(String line) {
		return true;
	}
	
}
