package exception;

public class LimitReachedException extends Exception{


	private static final long serialVersionUID = 3522289691449422767L;
	public LimitReachedException(String Message){
		super(Message);
	}
}
