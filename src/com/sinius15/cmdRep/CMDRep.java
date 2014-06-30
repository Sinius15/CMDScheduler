package com.sinius15.cmdRep;

import com.sinius15.cmdRep.commands.CmdCommand;
import com.sinius15.cmdRep.commands.SleepCommand;
import com.sinius15.cmdRep.pattern.DatePattern;
import com.sinius15.cmdRep.pattern.TimePattern;

public class CMDRep{
	
	public static CMDRepFrame repFrame;
	
	public static void main(String[] args) {
		//init commands
		new CmdCommand();
		new SleepCommand();
		
		//init patterns
		new DatePattern();
		new TimePattern();
		
		repFrame = new CMDRepFrame();
	}
	
}
