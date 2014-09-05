package Statistics;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Global.Interval;
import Global.Time;
import Tasks.Component;

public class Statistics {

	private static final Statistics INSTANCE = new Statistics();
	private HashMap<Component, DayStats> statsHash = new HashMap<Component, DayStats>();
	 
	// Private constructor prevents instantiation from other classes
	private Statistics() {
	}
	 
	public static Statistics getInstance() {
	    return INSTANCE;
	}
	
	public HashMap<Component, DayStats> build(Component root){
            HashMap<Component, List<Interval> > intervalsPerTask = new HashMap<Component, List<Interval> >();
	    root.updateIntervals(intervalsPerTask);
            statsHash.clear();
            buildStatsPerDay(statsHash, intervalsPerTask);
            return statsHash;
	}
	
	private void buildStatsPerDay(HashMap<Component, DayStats> to, HashMap<Component, List<Interval> > from){
	  Iterator componentsIterator = from.entrySet().iterator();
	  while (componentsIterator.hasNext()) {
	        Map.Entry pairs = (Map.Entry)componentsIterator.next();
                Component node = (Component)pairs.getKey();

                DayStats componentStats = to.containsKey(node)? to.get(node): new DayStats();
                HashMap<Component, DayStats> parents = new HashMap<Component, DayStats>();
                Component parent = node;
                while ((parent = parent.getParent()) != null){
                    DayStats parentStats = to.containsKey(parent)? to.get(parent): new DayStats();
                    parents.put(parent, parentStats);
                }
	        
	        /* Convert intervals to DayStats*/
	        Iterator<Interval> intervalsIterator = ((List<Interval>)pairs.getValue()).iterator();
	        while (intervalsIterator.hasNext()) {
		        Interval interval = intervalsIterator.next();
		        
		        /*Set stats for first day*/
		        Calendar calInit = interval.startTime;
		        Calendar calEnd = Calendar.getInstance();
		        Calendar calTemp = Calendar.getInstance();
		        		        		        
        		/*Set stats for other days*/
        		while(calInit.get(Calendar.DAY_OF_MONTH) < interval.endTime.get(Calendar.DAY_OF_MONTH)){
        	        
        			calTemp.clear();
        			calEnd.clear();
    		        calTemp.set(calInit.get(Calendar.YEAR), calInit.get(Calendar.MONTH), calInit.get(Calendar.DAY_OF_MONTH));  			
        			calEnd.set(calInit.get(Calendar.YEAR), calInit.get(Calendar.MONTH), calInit.get(Calendar.DAY_OF_MONTH +1));
        			
            		componentStats.add(calTemp, Time.getPeriod(calInit, calEnd));
            		calInit = calEnd;
        		}
        		
        		calTemp.clear();
        		calTemp.set(calInit.get(Calendar.YEAR), calInit.get(Calendar.MONTH), calInit.get(Calendar.DAY_OF_MONTH));
        		componentStats.add(calTemp, Time.getPeriod(calInit, interval.endTime));

                        Iterator parentsIterator = parents.entrySet().iterator();
                        while (parentsIterator.hasNext()) {
                            Map.Entry parentsPair = (Map.Entry)parentsIterator.next();
                            ((DayStats)parentsPair.getValue()).add(calTemp, Time.getPeriod(calInit, interval.endTime));
                        }
	        }

        	to.put(node, componentStats);
                Iterator parentsIterator = parents.entrySet().iterator();
                while (parentsIterator.hasNext()) {
                    Map.Entry parentsPair = (Map.Entry)parentsIterator.next();
                    to.put((Component)parentsPair.getKey(), (DayStats)parentsPair.getValue());
                }
	    }
	}
	
	public String toString(){
		String ret = "";
		int i = 1;
		Iterator statsIterator = statsHash.entrySet().iterator();
		while (statsIterator.hasNext()) {
			Map.Entry componentPairs = (Map.Entry)statsIterator.next();
			
			ret += i + " ";
			ret += "Component: " + ((Component)componentPairs.getKey()).getName() + "\n";
			ret += "DayStats:" + "\n" + (DayStats)componentPairs.getValue();
		}
		return ret;
	}

    public DayStats getStats(Component node) {
        if (statsHash.containsKey(node))
            return statsHash.get(node);
        else
            return null;
    }
}
