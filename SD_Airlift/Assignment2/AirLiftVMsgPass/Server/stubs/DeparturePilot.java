package Server.stubs;

/**
 * 
 * @author renato
 * @author ruben
 */
public interface DeparturePilot {
	
	/**
	 * informPlaneReadyForBoarding
	 */
	public void informPlaneReadyForBoarding();
	
	/**
	 * waitForAllInBoarding
	 */
	public void waitForAllInBoarding();
	
	/**
	 * endPilotActivity
	 */
	public boolean endPilotActivity();
}
