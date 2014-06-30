package com.sinius15.cmdRep.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sinius15.cmdRep.CMDRepFrame;
import com.sinius15.cmdRep.Command;

public class CmdCommand extends Command{

	public CmdCommand() {
		super("cmd");
	}

	@Override
	public boolean call(String line) {
		line = line.replace("cmd", "").trim();
		line = line.replace("%time", new SimpleDateFormat("HH:mm:ss").format(new Date()));
		line = line.replace("%date", new SimpleDateFormat("dd.MM.yyy").format(new Date()));
		
		
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
		return true;
	}
	
}
