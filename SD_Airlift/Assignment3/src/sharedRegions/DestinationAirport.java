package sharedRegions;

import java.rmi.RemoteException;
import interfaces.*;


import java.util.LinkedList;

import entities.PilotState;
import genclass.GenericIO;
import interfaces.RepositoryInterface;

public class DestinationAirport implements DestinationAirportInterface, SharedRegion{
    
    /**
     * Referencia para o Repositorio
     */
    private RepositoryInterface repos;
    
    private boolean hasTerminated=false;
    /**
     * 
     * @param repos
     */
    public DestinationAirport(RepositoryInterface repos) {
        this.repos = repos;
    }
    

    /**
     * O Piloto volta para a Origem
     */
	public void flyToDeparturePoint() throws RemoteException{
		GenericIO.writelnString("\nVOLTA PARA A ORIGEM");
        repos.setPilotState(PilotState.FLYING_BACK);
        try
        { Thread.sleep ((long) (1 + 40 * Math.random ()));
        }
        catch (InterruptedException e) {}
	}


	@Override
	public boolean getTerminationState() throws RemoteException {
		// TODO Auto-generated method stub
		return hasTerminated;
	}

   
}
