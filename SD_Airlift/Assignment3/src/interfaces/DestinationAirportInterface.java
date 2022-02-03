package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DestinationAirportInterface extends Remote{
	
	void flyToDeparturePoint() throws RemoteException;

}
