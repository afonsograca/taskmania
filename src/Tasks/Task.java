package Tasks;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import Global.Interval;
import States.*;
import States.State.InvalidTransitionException;
import java.util.HashMap;

/**
 * Class Task
 * 
 * @author ASSO1011D
 *
 */

public class Task extends Leaf{
	
	public List<Interval> intervals = new ArrayList<Interval>();
	
	public Task(){}
	
	public Task(String name, String description){
		this.name = name;
		this.description = description;
	}
        
        public Task(String name, String description, String state){
            this.name = name;
            this.description = description;
            if(state.equals("To Do")){
                    this.state = new ToDo();
            }
            else if(state.equals("Paused")){
                    this.state = new Paused();
            }
            else if(state.equals("Running")){
                    this.state = new Running();
            }
            else if(state.equals("Closed")){
                    this.state = new Closed();
            }
	}
	
	public void start() throws InvalidTransitionException{
            if (state.getClass() != Closed.class && state.getClass() != Running.class)
                    this.state = new Running();
            else
                    throw new InvalidTransitionException(this.state, new Running());
	}
	
	public void pause() throws InvalidTransitionException{
            if (state.getClass() == Running.class) {
                    Interval interval = ((Running)this.state).getInterval();
                    this.state = new Paused(interval);
                    Interval time =  ((Paused)this.state).getElapsedTime();
                    intervals.add(time);
            }else
                    throw new InvalidTransitionException(this.state, new Paused());
	}
	
	public void close() throws InvalidTransitionException{
            if (state.getClass() != Closed.class){
                    if (state.getClass() == Running.class)
                        pause();
                    this.state = new Closed();
            }else
                    throw new InvalidTransitionException(this.state, new Closed());
	}
	
	public long getElapsedTime(){
		Iterator<Interval> itr = intervals.iterator();
		long time = 0;
		while(itr.hasNext())
			time += itr.next().getIntervalTime();
		return time;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
        
        public List<Interval> getIntervals(){
		return intervals;
	}
	
	@Override
	public void updateIntervals(HashMap<Component, List<Interval>> intervalsPerTask) {
		intervalsPerTask.put(this, intervals);
	}
}
