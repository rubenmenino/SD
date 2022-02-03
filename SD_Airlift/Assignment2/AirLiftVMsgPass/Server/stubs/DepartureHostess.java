package Server.stubs;

/**
 * 
 * @author renato
 * @author ruben
 */
public interface DepartureHostess {
	
	/**
	 * waitingForNextFlight
	 */
	public void waitingForNextFlight();
	
	/**
	 * prepareForPassBoarding
	 */
	public void prepareForPassBoarding();
	
	/**
	 * checkDocuments
	 */
	public void checkDocuments();
	
	/**
	 * waitForNextPassenger
	 */
	public void waitForNextPassenger();
	
	/**
	 * informPlaneReadyToTakeOff
	 */
	public boolean informPlaneReadyToTakeOff();
}
