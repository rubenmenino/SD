package entities;

import SharedRegions.DepartureAirport;
import SharedRegions.DestinationAirport;
import SharedRegions.Plane;
import genclass.GenericIO;

public class Passenger extends Thread{

    /**
     * id do passageiro
     */
    private int passengerID;

    /**
     * Referencia ao departureAirport
     */
    DepartureAirport departureAirport;
    
    /**
     * Referencia do destinationAirport
     */
    DestinationAirport destinationAirport;
    
    /**
     * Referencia ao plane
     */
    Plane plane;
    
    /**
     * Estado interno do passageiro
     */
    private int passengerstate;
    
    /**
     * 
     * @param passengerID
     * @param departureAirport
     * @param plane
     * @param destinationAirport
     */
    public Passenger(int passengerID, DepartureAirport departureAirport, Plane plane, DestinationAirport destinationAirport) {
        this.passengerID = passengerID;
        this.departureAirport = departureAirport;
        this.plane = plane;
        this.destinationAirport = destinationAirport;
        this.passengerstate = PassengerState.GOING_TO_AIRPORT;
    }


    /**
     * Ciclo de vida do hospedeiro
     */
    @Override
    public void run() {

    	travelToAirport();
    	//departureAirport
        departureAirport.waitInQueue();
        departureAirport.showDocuments();

        //plane
        plane.boardPlane();
        plane.waitForEndOfFlight();
        plane.leavePlane();

        GenericIO.writelnString("Passageiro acabou a atividade");
    }


    /**
     * o passageiro irá ter um tempo para chegar ao aeroporto e colocar-se na fila
     */
    private void travelToAirport(){
        try
        { sleep ((long) (1 + 100 * Math.random ()));
        }
        catch (InterruptedException e) {}
     }

    /**
     * 
     * @return id do passageiro
     */
    public int getPassengerID() {
        return passengerID;
    }

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

	public int getPassengerState() {
		return passengerstate;
	}
}