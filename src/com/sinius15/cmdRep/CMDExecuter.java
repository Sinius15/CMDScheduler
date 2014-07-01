package com.sinius15.cmdRep;

import javax.swing.JOptionPane;

public class CMDExecuter implements Runnable{

	private String inputText;
	
	public CMDExecuter(String inputText){
		this.inputText = inputText;
	}
	
	@Override
	public void run() {
		CMDRepFrame.log("initializing...");
		String[] lines = inputText.split("\\n");
		if (lines.length < 1) {
			return;
		}
		
		int i = 0;
		while (CMDRepFrame.isRunning) {
			String curLine = lines[i] + " ";
			
			for(Pattern pat : Pattern.patterns )
				curLine = curLine.replace("%"+pat.getName(), pat.replace());
			
			String commandName = curLine.split(" ", -1)[0].trim();
			
			Command c = Command.commands.get(commandName);
			if(commandName.equals(""))
				continue;
			if(c == null){
				JOptionPane.showMessageDialog(CMDRep.repFrame, "No command '" + commandName + "'", "fatal error", JOptionPane.ERROR_MESSAGE);
				break;
			}
			if(c.call(curLine) == false)
				break;  //fatal error;
			
			i++;
			if (i == lines.length)
				i = 0;
		}
		
	}
	
}


