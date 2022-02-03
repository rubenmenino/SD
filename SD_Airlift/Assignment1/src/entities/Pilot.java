package entities;

import SharedRegions.DepartureAirport;
import SharedRegions.Plane;
import genclass.GenericIO;

public class Pilot extends Thread{

	/**
     * Estado interno da hospedeira
     */
	private int pilotState;
	
	/**
     * Referencia para o aeroporto de partida (DepartureAirport)
     */
    DepartureAirport departureAirport;
    
    /**
     * Referencia para o aviao
     */
    Plane plane;

    
    /**
     * 
     * @param departureAirport
     * @param plane
     */
    public Pilot(DepartureAirport departureAirport, Plane plane) {
        this.departureAirport = departureAirport;
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
            plane.flyToDestinationPoint();
            plane.announceArrival();
            plane.flyToDeparturePoint();
        }

        GenericIO.writelnString("O Piloto acabou a atividade");
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