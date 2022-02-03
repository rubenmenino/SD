package Client.entities;

import Server.sharedRegions.*;
import Server.stubs.DepartureAirportStub;
import genclass.GenericIO;
import Common.*;


public class Hostess extends Thread{

	/**
	 * numero de passageiros
	 */
    private int nOfPassengers;
    
    /**
     * numero de passageiros com os documentos vistos
     */
    private int passengersChecked;
    
    /**
     * Estado interno da hospedeira
     */
    private int hostessState;
    
    /**
     * Referencia para o aeroporto de partida (DepartureAirport)
     */
    //Regioes partilhadas
    DepartureAirportStub departureAirport;

    /**
     * 
     * @param nOfPassengers
     * @param departureAirport reference to the departure airport remote object
     */
    
    public Hostess(int nOfPassengers, DepartureAirportStub departureAirport) {
        this.nOfPassengers = nOfPassengers;
        this.departureAirport = departureAirport;
        this.passengersChecked = 0;
        this.hostessState = HostessState.WAIT_FOR_FLIGHT;
    }

    /**
     * Ciclo de vida da hospedeira
     */
    @Override
    public void run() {
    	//A Hostepeira termina a atividade quando nao houver mais Passageiros para atender
        while(!allPassengersChecked()){
        
        	boolean all = false;
        	//departureAirport
            departureAirport.waitingForNextFlight();
            
            while(!all) {
	            departureAirport.prepareForPassBoarding();
	            departureAirport.checkDocuments();
	            departureAirport.waitForNextPassenger();
	            passengersChecked++;
	            all = departureAirport.informPlaneReadyToTakeOff();
            }
        }

        GenericIO.writelnString("A Hostess acabou a atividade");
    }

    /**
     * 
     * @return true or false consoante os passageiros checkados ou nao
     */
    public boolean allPassengersChecked(){
        if(passengersChecked == nOfPassengers)
            return true;
        else
            return false;
    }

    /**
     * Altera o estado interno do cliente
     * 
     * @param state o novo estado da hospedeira
     */
	public void setHostessState(int state) {
		this.hostessState = state;
		
	}
}