package com.sinius15.cmdRep.pattern;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.sinius15.cmdRep.Pattern;

public class PCNamePattern extends Pattern{

	@Override
	public String getName() {
		return "pc";
	}

	@Override
	public String replace() {
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			return "Could not find computer name: " + e.getMessage();
		}
		
	}
	
}
