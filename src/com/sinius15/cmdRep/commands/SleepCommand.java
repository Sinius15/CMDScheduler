package com.sinius15.cmdRep.commands;

import javax.swing.JOptionPane;

import com.sinius15.cmdRep.CMDRep;
import com.sinius15.cmdRep.CMDRepFrame;
import com.sinius15.cmdRep.Command;

public class SleepCommand extends Command{

	public SleepCommand() {
		super("sleep");
	}

	@Override
	public boolean call(String line) {
		line = line.replace("sleep", "").trim();
		int sleepTime = 0;
		try {
			sleepTime = Integer.parseInt(line);
			
		} catch (NumberFormatException e) {
			CMDRepFrame.errLog("Fatal Error!!");
			JOptionPane.showMessageDialog(CMDRep.repFrame,
					"Invalid argument." + System.lineSeparator()
							+ "After 'sleep ' there must be a number!", "Fatal Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {}
		return true;
	}
	
}
