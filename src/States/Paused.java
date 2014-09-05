package States;

import Global.Interval;

public class Paused implements State{

	private Interval elapsedTime;
	
	public Paused(){}
	
	public Paused(Interval interval) {
		elapsedTime = interval;
		elapsedTime.stop();
	}

	@Override
	public String getName() {
		return "Paused";
	}

	public Interval getElapsedTime() {
		return elapsedTime;
	}

}
