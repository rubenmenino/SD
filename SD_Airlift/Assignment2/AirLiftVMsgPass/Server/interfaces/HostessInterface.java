package Server.interfaces;

/**
 * 
 * @author renato
 * @author ruben
 *
 */
public interface HostessInterface {
	
	/**
	 * 
	 * @return 
	 */
	public boolean isAllPassengersAttended();

	/**
	 * 
	 * @return 
	 */
	public void setHostessState(int waitForPassenger);
}
