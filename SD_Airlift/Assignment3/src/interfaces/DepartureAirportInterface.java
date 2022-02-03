package interfaces;

import java.rmi.Remote;

import java.rmi.RemoteException;

public interface DepartureAirportInterface extends Remote{

	void travelToAirport(int passengerId) throws java.rmi.RemoteException;
	
	void waitInQueue(int passengerId) throws java.rmi.RemoteException;
	
	void showDocuments(int passengerId) throws java.rmi.RemoteException;
	
	void waitingForNextFlight() throws java.rmi.RemoteException;
	
	void prepareForPassBoarding() throws java.rmi.RemoteException;
	
	void checkDocuments() throws java.rmi.RemoteException;
	
	void waitForNextPassenger() throws java.rmi.RemoteException;
	
	boolean informPlaneReadyToTakeOff() throws java.rmi.RemoteException;
	
	void informPlaneReadyForBoarding() throws java.rmi.RemoteException;
	
	void waitForAllInBoarding() throws java.rmi.RemoteException;
	
	boolean endPilotActivity() throws java.rmi.RemoteException;
	
	void setEndPilotActivity(boolean endPilotActivity) throws java.rmi.RemoteException;
}
