package com.sinius15.cmdRep.commands;

import com.sinius15.cmdRep.CMDRep;
import com.sinius15.cmdRep.Command;

public class PrintCommand extends Command{

	public PrintCommand() {
		super("print");
	}

	@Override
	public boolean call(String line) {
		line = line.replace("print", "").trim();
		CMDRep.repFrame.log(line);
		return true;
	}
	
}
