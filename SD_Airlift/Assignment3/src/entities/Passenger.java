package entities;

import sharedRegions.*;

import genclass.GenericIO;
import interfaces.DepartureAirportInterface;
import interfaces.DestinationAirportInterface;
import interfaces.PlaneInterface;

import java.rmi.RemoteException;

//import Common.*;

public class Passenger extends Thread{

    /**
     * id do passageiro
     */
    private int passengerID;

    private DepartureAirportInterface departureAirport;
    
    private DestinationAirportInterface destinationAirport;
    
    private PlaneInterface plane;
    
    /**
     * Estado interno do passageiro
     */
    private int passengerstate;
    
    /**
     * 
     * @param passengerID
     * @param departureAirport  reference to the departure airport remote object
     * @param destinationAirport reference to the destination airport remote object
     * @param plane reference to the plane remote object
     */
    public Passenger(int passengerID, DepartureAirportInterface departureAirport, DestinationAirportInterface destinationAirport, PlaneInterface plane) {
        this.passengerID = passengerID;
        this.departureAirport = departureAirport;
        this.destinationAirport = destinationAirport;
        this.plane = plane;
        this.passengerstate = PassengerState.GOING_TO_AIRPORT;
    }


    /**
     * Ciclo de vida do hospedeiro
     */
    @Override
    public void run() {
    	
    	//departureAirport
    		try {
				departureAirport.travelToAirport(passengerID);
	
				departureAirport.waitInQueue(passengerID);
	
				departureAirport.showDocuments(passengerID);
	
				plane.boardPlane(passengerID);
	
				plane.waitForEndOfFlight(passengerID);
	
				plane.leavePlane(passengerID);
    		}
			catch(RemoteException e) {
				System.out.println("Remote exception: " + e.getMessage ());
				e.printStackTrace();
				System.exit(1);
			}

        GenericIO.writelnString("Passageiro acabou a atividade");
    }

    /**
     * 
     * @return id do passageiro
     */
    public int getPassengerID() {
        return passengerID;
    }

    /**
     * 
     * @param passengerID
     */
    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    /**
     * Altera o estado interno do cliente
     * 
     * @param state o novo estado do passageiro
     */
	public void setPassengerState(int state) {
		this.passengerstate = state;
		
	}

	/**
	 * 
	 * @return getpassengerstate
	 */
	public int getPassengerState() {
		return passengerstate;
	}
}