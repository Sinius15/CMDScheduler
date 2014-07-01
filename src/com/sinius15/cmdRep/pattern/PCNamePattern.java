package com.sinius15.cmdRep.pattern;

import java.net.InetAddress;

import com.sinius15.cmdRep.Pattern;

public class PCNamePattern extends Pattern{

	@Override
	public String getName() {
		return "pc";
	}

	@Override
	public String replace() {
		return InetAddress.getLocalHost().getHostName();
	}
	
}
