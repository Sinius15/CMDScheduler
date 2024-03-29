package com.sinius15.cmdRep;

import com.sinius15.cmdRep.commands.CmdCommand;
import com.sinius15.cmdRep.commands.PrintCommand;
import com.sinius15.cmdRep.commands.SleepCommand;
import com.sinius15.cmdRep.pattern.DatePattern;
import com.sinius15.cmdRep.pattern.PCNamePattern;
import com.sinius15.cmdRep.pattern.TimePattern;

public class CMDRep{
	
	public static CMDRepFrame repFrame;
	
	public static void main(String[] args) {
		//init commands
		new CmdCommand();
		new SleepCommand();
		new PrintCommand();
		
		//init patterns
		new DatePattern();
		new TimePattern();
		new PCNamePattern();
		
		repFrame = new CMDRepFrame();
	}
	
}
