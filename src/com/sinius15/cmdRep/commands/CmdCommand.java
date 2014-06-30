package com.sinius15.cmdRep.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import com.sinius15.cmdRep.CMDRepFrame;
import com.sinius15.cmdRep.Command;

public class CmdCommand extends Command{

	public CmdCommand() {
		super("cmd");
	}

	@Override
	public boolean call(String line) {
		line = line.replace("cmd", "").trim();
		
		
		ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", line);
		builder.directory(new File(System.getProperty("user.dir")));
		builder.redirectErrorStream(true);
		try {
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream(), "UTF-8"));
			String l;
			while ((l = r.readLine()) != null) {
				CMDRepFrame.errLog(l);
			}
			r.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
