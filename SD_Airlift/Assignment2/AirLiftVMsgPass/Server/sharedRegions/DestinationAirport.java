package Server.sharedRegions;

import java.util.LinkedList;

import Common.PilotState;
import Server.TunnelProvider;
import genclass.GenericIO;

public class DestinationAirport{
    
    /**
     * Referencia para o Repositorio
     */
    private GeneralRepository repos;
    
    /**
     * 
     * @param repos
     */
    public DestinationAirport(GeneralRepository repos) {
        this.repos = repos;
    }
    

    /**
     * O Piloto volta para a Origem
     */
	public void flyToDeparturePoint() {
		GenericIO.writelnString("\nVOLTA PARA A ORIGEM");
    	((TunnelProvider) Thread.currentThread()).setPilotState(PilotState.FLYING_BACK);
        repos.setPilotState(PilotState.FLYING_BACK);
        try
        { Thread.sleep ((long) (1 + 40 * Math.random ()));
        }
        catch (InterruptedException e) {}
	}

   
}
