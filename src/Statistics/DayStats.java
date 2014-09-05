package Statistics;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import Global.Time;

public class DayStats {

	private HashMap<Calendar, Long> statsPerDay = new HashMap<Calendar, Long>();
	
	public DayStats(){}
	
	public void add(Calendar cal, Long period){
		if(statsPerDay.containsKey(cal)){
			Long currentTime = statsPerDay.get(cal);
			statsPerDay.put(cal, currentTime + period);
		}else
			statsPerDay.put(cal, period);
	}

        public HashMap<Calendar, Long> getStats(){
            return statsPerDay;
        }
	
	public String toString(){
		String ret = "";
		Iterator daysIterator = statsPerDay.entrySet().iterator();
		while (daysIterator.hasNext()) {
			Map.Entry daysPairs = (Map.Entry)daysIterator.next();
			Calendar cal = (Calendar)daysPairs.getKey();
			ret += "Day: " + cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) + "-" + cal.get(Calendar.DAY_OF_MONTH) + " ";
			ret += "Time: " + Time.periodToFormat((Long)daysPairs.getValue());
		}
		return ret;
	}
}
