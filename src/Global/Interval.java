package Global;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Interval {
	public Calendar startTime = null;
	public Calendar endTime = null;

	public Interval(){}
	
	public Interval(String start, String end){
                Calendar calStart = Calendar.getInstance();
                calStart.setTimeInMillis(Long.parseLong(start));

                Calendar calEnd = Calendar.getInstance();
                calEnd.setTimeInMillis(Long.parseLong(end));

                this.startTime = calStart;
                this.endTime =calEnd;
	}
	public void start(){
		startTime = Calendar.getInstance();
	}

	public void stop(){
		endTime = Calendar.getInstance();
		System.out.println("stop: " + Time.periodToFormat(getIntervalTime()));
	}

	public long getIntervalTime(){
		return Time.getPeriod(startTime, endTime);	
	}

}
