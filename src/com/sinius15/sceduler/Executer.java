package com.sinius15.sceduler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

public class Executer implements Runnable{

	@Override
	public void run() {
		log("initializing...");
		String[] lines = txtArea.getText().split("\\n");
		if (lines.length < 1) {
			return;
		}
		
		int i = 0;
		while (isRunning) {
			String curLine = lines[i];
			log("now handeling '" + curLine + "'"); 
			if (curLine.toLowerCase().startsWith("sleep")) {   // sleep!!!
				curLine = curLine.replace("sleep", "").trim();
				int sleepTime;
				try {
					sleepTime = Integer.parseInt(curLine);
				} catch (NumberFormatException e) {
					errLog("Fatal Error!!");
					JOptionPane.showMessageDialog(this,
							"Invalid argument." + System.lineSeparator()
									+ "After 'sleep ' there must be a number!", "Fatal Error",
							JOptionPane.ERROR_MESSAGE);
					break;
				}
				try {
					log("Sleeping for " + sleepTime + "ms");
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {}
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
						errLog(line);
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
