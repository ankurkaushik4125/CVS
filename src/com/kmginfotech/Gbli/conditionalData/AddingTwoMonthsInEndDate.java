package com.kmginfotech.Gbli.conditionalData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddingTwoMonthsInEndDate {

	String newDate = null;

	public String addTwoMonthInAPassedDate(String endorsementDate) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String actualDate = endorsementDate;

		Date current = sdf.parse(actualDate);

		Calendar c = Calendar.getInstance();

		c.setTime(current);

		c.set(Calendar.MONTH, (c.get(Calendar.MONTH) + 2));

		current = c.getTime();

		c.setTime(current);

		newDate = (c.get(Calendar.YEAR) + "-" + String.format("%2s", (c.get(Calendar.MONTH) + 1)).replace(' ', '0')
				+ "-" + "15");

		return newDate;

	}

}
