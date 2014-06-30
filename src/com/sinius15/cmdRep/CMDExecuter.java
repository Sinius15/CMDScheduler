package com.sinius15.cmdRep;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

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
			
			String commandName = curLine.split(" ")[0];
			
			Command c = Command.commands.get(commandName);
			if(c.call(curLine) == false)
				break;  //fatal error;
			
			
			if (curLine.toLowerCase().startsWith("sleep")) {   // sleep!!!
				
			} else if (curLine.toLowerCase().startsWith("cmd")) { // cmd!!!
				curLine = curLine.replace("cmd", "").trim();
				curLine = curLine.replace("%time", new SimpleDateFormat("HH:mm:ss").format(new Date()));
				curLine = curLine.replace("%date", new SimpleDateFormat("dd.MM.yyy").format(new Date()));
				
				
				ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", curLine);
				builder.directory(new File(System.getProperty("user.dir")));
				builder.redirectErrorStream(true);
				try {
					Process p = builder.start();
					BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
					String line;
					while ((line = r.readLine()) != null) {
						CMDRepFrame.errLog(line);
					}
					r.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
			i++;
			if (i == lines.length)
				i = 0;
		}
		
	}
	
}
