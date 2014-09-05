package Tasks;

import Global.Interval;
import States.Closed;
import States.ToDo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class Action
 * 
 * @author ASSO1011D
 *
 */

public class Action extends Leaf{

	public Action(){
		state = new ToDo();
		name = "";
		description = "";
	}
	
	public Action(String name, String description){
		state = new ToDo();
		this.name = name;
		this.description = description;
	}
	
        public Action(String name, String description, String state){
            this.name = name;
            this.description = description;
            if(state.equals("To Do")){
                    this.state = new ToDo();
            }
            else if(state.equals("Closed")){
                    this.state = new Closed();
            }
	}
	
	public long getElapsedTime() {
        return 0;
    }
	
	public void close() {
		this.state = new Closed();
	}

    @Override
    public void updateIntervals(HashMap<Component, List<Interval>> intervalsPerTask) {
        intervalsPerTask.put(this, new ArrayList<Interval>());
    }
}
