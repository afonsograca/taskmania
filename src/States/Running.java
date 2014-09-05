package States;

import Global.Interval;

public class Running implements State{

	private Interval elapsedTime;
	
	public Running(){
		this.elapsedTime = new Interval();
		this.elapsedTime.start();
	}
	@Override
	public String getName() {
		return "Running";
	}

	public Interval getInterval() {
		return elapsedTime;
	}

}
