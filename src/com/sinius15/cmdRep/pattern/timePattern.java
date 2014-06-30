package com.sinius15.cmdRep.pattern;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sinius15.cmdRep.Pattern;

public class TimePattern extends Pattern{

	public TimePattern() {
		super("time");
	}

	@Override
	public String replace() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}
	
}
