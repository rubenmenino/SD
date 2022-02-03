package entities;

import sharedRegions.*;
import genclass.GenericIO;
import interfaces.DepartureAirportInterface;
import interfaces.DestinationAirportInterface;
import interfaces.PlaneInterface;

import java.rmi.RemoteException;

//import Common.*;

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
	private DepartureAirportInterface departureAirport;
	
	private DestinationAirportInterface destinationAirport;
	
	private PlaneInterface plane;

    
    /**
     * Referencia para o aviao
     */

    GeneralRepository repos;
    
    /**
     * 
     * @param departureAirport reference to the departure airport remote object
     * @param destinationAirport reference to the destination airport remote object
     * @param plane reference to the plane remote object
     */
    public Pilot(DepartureAirportInterface departureAirport, DestinationAirportInterface destinationAirport, PlaneInterface plane) {
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
        try {
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
        }
			catch(RemoteException e) {
				System.out.println("Remote exception: " + e.getMessage ());
				e.printStackTrace();
				System.exit(1);
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