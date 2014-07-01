package com.sinius15.cmdRep.pattern;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.sinius15.cmdRep.Pattern;

public class DatePattern extends Pattern{

	@Override
	public String replace() {
		return new SimpleDateFormat("dd.MM.yyy").format(new Date());
	}

	@Override
	public String getName() {
		return "date";
	}
	
	
}
