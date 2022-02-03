package Client.entities;

import Server.sharedRegions.*;
import Server.stubs.DepartureAirportStub;
import Server.stubs.DestinationAirportStub;
import Server.stubs.PlaneStub;
import genclass.GenericIO;
import Common.*;

public class Passenger extends Thread{

    /**
     * id do passageiro
     */
    private int passengerID;

    /**
     * Referencia ao departureAirport
     */
    DepartureAirportStub departureAirport;
    
    /**
     * Referencia do destinationAirport
     */
    DestinationAirportStub destinationAirport;
    
    /**
     * Referencia ao plane
     */
    PlaneStub plane;
    
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
    public Passenger(int passengerID, DepartureAirportStub departureAirport, DestinationAirportStub destinationAirport, PlaneStub plane) {
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
    	departureAirport.travelToAirport();
        departureAirport.waitInQueue();
        departureAirport.showDocuments();

        //plane
        plane.boardPlane();
        plane.waitForEndOfFlight();
        plane.leavePlane();

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