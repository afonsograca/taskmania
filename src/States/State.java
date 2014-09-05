package States;


public interface State {

	public String getName();
	
	class InvalidTransitionException extends Exception{
		private static final long serialVersionUID = 1L;
		String fromState, toState;
		public InvalidTransitionException(State fs, State ts){
			fromState = fs.getName();
			toState = ts.getName();
		}
		
		public String toString(){
			return "Invalid state transition: " + fromState + " to " + toState;
		}
	};
	
}
