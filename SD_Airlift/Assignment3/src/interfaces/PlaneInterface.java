package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlaneInterface extends Remote {
	
	void boardPlane(int passengerId) throws RemoteException;
	
	void waitForEndOfFlight(int passengerId) throws RemoteException;
	
	void leavePlane(int passengerId) throws RemoteException;
	
	void flyToDestinationPoint() throws RemoteException;
	
	void announceArrival() throws RemoteException;
	

}
