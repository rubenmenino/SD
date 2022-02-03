package Client.entities;

import Server.sharedRegions.*;
import Server.stubs.DepartureAirportStub;
import Server.stubs.DestinationAirportStub;
import Server.stubs.GeneralRepositoryStub;
import Server.stubs.PlaneStub;
import genclass.GenericIO;
import Common.*;

/**
 * 
 * @author menino
 * @author renato
 *
 */
public class Pilot extends Thread{

	/**
     * Estado interno da hospedeira
     */
	private int pilotState;
	
	/**
     * Referencia para o aeroporto de partida (DepartureAirport)
     */
    DepartureAirportStub departureAirport;
    
    DestinationAirportStub destinationAirport;
    
    
    /**
     * Referencia para o aviao
     */
    PlaneStub plane;

    GeneralRepository repos;
    
    /**
     * 
     * @param departureAirport reference to the departure airport remote object
     * @param destinationAirport reference to the destination airport remote object
     * @param plane reference to the plane remote object
     */
    public Pilot(DepartureAirportStub departureAirport, DestinationAirportStub destinationAirport, PlaneStub plane) {
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.plane = plane;
        this.pilotState = PilotState.AT_TRANSFER_GATE;
    }

	/**
     * Ciclo de vida do piloto
     */
    @Override
    public void run() {
    	//O Piloto termina a atividade quando nao houver mais passageiros nem mais voos
        while(!departureAirport.endPilotActivity()){
        	
            parkAtTransferGate();
        	//departureAirport
            departureAirport.informPlaneReadyForBoarding();
            departureAirport.waitForAllInBoarding();
            
            //plane
            //flyDestinoOrigem();
            plane.flyToDestinationPoint();
            plane.announceArrival();
            //flyOrigemDestino();
            destinationAirport.flyToDeparturePoint();
        }

        GenericIO.writelnString("\nO Piloto acabou a atividade");
    }

	/**
     * Altera o estado interno do cliente
     * 
     * @param state o novo estado do piloto
     */
	public void setPilotState(int state) {
		this.pilotState = state;
		
	}
	
	/**
	 * tempo para o aviao estar no departureairport
	 */
	private void parkAtTransferGate() {
        try
        { sleep ((long) (1 + 40 * Math.random ()));
        }
        catch (InterruptedException e) {}
     }

}