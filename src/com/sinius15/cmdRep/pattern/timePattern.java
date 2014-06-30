package com.sinius15.cmdRep.pattern;

import com.sinius15.cmdRep.Pattern;

public class TimePattern extends Pattern{

	public TimePattern(String name) {
		super(name);
	}

	@Override
	public String getName() {
		return "time";
	}

	@Override
	public String replace() {
		return null;
	}
	
}
