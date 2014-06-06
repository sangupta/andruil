package com.sangupta.andruil.commands.basic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sangupta.andruil.commands.base.AbstractAndruilCommand;
import com.sangupta.jerry.util.AssertUtils;
import com.sangupta.jerry.util.ConsoleUtils;

/**
 * Find out the weekday today, or on a given date.
 * 
 * @author sangupta
 *
 */
public class DayCommand extends AbstractAndruilCommand {
	
	private final String[] WEEKDAYS = { "Saturday", "Sunday", "Monday", "Tueday", "Wednesday", "Thursday", "Friday"};

	@Override
	public String getName() {
		return "day";
	}

	@Override
	public String getHelpLine() {
		return "Display the day today or a given date";
	}

	@Override
	public void execute(String[] arguments) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println("Today is " + WEEKDAYS[day]);
		
		String date = ConsoleUtils.readLine("Enter date (dd/MM/yyyy): ", true);
		if(AssertUtils.isEmpty(date)) {
			return;
		}
		
		Date d = null;
		try {
			d = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch(ParseException e) {
			System.out.println("Date entered not in valid format.");
			return;
		}
		
		calendar.setTimeInMillis(d.getTime());
		day = calendar.get(Calendar.DAY_OF_WEEK);
		System.out.println("Day on " + date + " was " + WEEKDAYS[day]);
	}

}
