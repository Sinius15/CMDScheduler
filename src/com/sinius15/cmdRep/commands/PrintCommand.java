package com.sinius15.cmdRep.commands;

import com.sinius15.cmdRep.CMDRepFrame;
import com.sinius15.cmdRep.Command;

public class PrintCommand extends Command{

	public PrintCommand() {
		super("print");
	}

	@Override
	public boolean call(String line) {
		line = line.replace("print", "").trim();
		CMDRepFrame.log(line);
		return true;
	}
	
}
