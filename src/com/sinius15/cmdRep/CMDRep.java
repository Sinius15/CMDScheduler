package com.sinius15.cmdRep;

import com.sinius15.cmdRep.commands.CmdCommand;
import com.sinius15.cmdRep.commands.SleepCommand;

public class CMDRep{
	
	public static CMDRepFrame repFrame;
	
	public static void main(String[] args) {
		new CmdCommand();
		new SleepCommand();
		
		repFrame = new CMDRepFrame();
	}
	
}
