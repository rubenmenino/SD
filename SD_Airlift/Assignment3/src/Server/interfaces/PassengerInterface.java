package Server.interfaces;

/**
 * 
 * @author renato
 * @author ruben
 *
 */
public interface PassengerInterface {
	/**
	 * 
	 * @return
	 */
	public int getPassengerID();

	/**
	 * 
	 * @param inQueue
	 */
	public void setPassengerState(int inQueue);
	
	/**
	 * 
	 * @return
	 */
	public int getPassengerState();
}
