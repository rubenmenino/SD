package sharedRegions;

import java.rmi.RemoteException;

/**
 * 
 * @author renato
 * @author ruben
 */
public interface SharedRegion {

	public boolean getTerminationState() throws RemoteException;

}