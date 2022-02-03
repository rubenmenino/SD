package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RepositoryInterface extends Remote  {
	
	void setPassengerState(int id, int state) throws RemoteException;
	
	void setHostessState(int idPassageiro, int state) throws RemoteException;
	
	void setPilotState(int state) throws RemoteException;
	
	void setInfoVoo(int nVoo, int npassageiros) throws RemoteException;
	
	//void reportInitialStatus();
	
	//void reportStatus();
	
	void resumoDoPrograma();

}
