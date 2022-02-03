package SharedRegions;

import java.util.LinkedList;
import java.util.Queue;

import Main.AirLift;
import commonInfrastructures.MemException;
import commonInfrastructures.MemFIFO;
import entities.Passenger;
import entities.PassengerState;
import entities.Pilot;
import entities.PilotState;
import genclass.GenericIO;

public class Plane  {

    /**
     * Lista de Passageiros
     */
	 private Queue<Integer> passengerQueueInPlane;
	 
	 /**
	  * Referencia ao repositorio
	  */
	 private GeneralRepository repos;
	 
	/**
	 * passageiros saiem do aviao
	 */
    private boolean leavePlane;

    /**
     * piloto informa que chegou ao destino
     */
    private boolean announceArrival;

    /**
     * 
     * @param repos
     */
    public Plane(GeneralRepository repos) {
    	passengerQueueInPlane = new LinkedList<Integer>();
        this.leavePlane = false;
        this.announceArrival = false;
        this.repos = repos;
    }

    //PASSAGEIROS


    /**
     * O Passageiro entra no aviao
     */
    public synchronized void boardPlane() {
        Passenger p = (Passenger) Thread.currentThread();
        int passengerId = p.getPassengerID();
        //p.setPassengerState(PassengerState.GOING_TO_AIRPORT);
        repos.setPassengerState(passengerId, p.getPassengerState());
        GenericIO.writelnString("A Hostess recebe os documentos");
        passengerQueueInPlane.add(passengerId);
        GenericIO.writelnString("O Passageiro " + passengerId + " embarca no aviao");
    }

    /**
     * O Passageiro espera que o aviao chegue ao destino
     */
    public synchronized void waitForEndOfFlight() {
    	((Passenger) Thread.currentThread()).setPassengerState(PassengerState.IN_FLIGHT);
    	Passenger p = (Passenger) Thread.currentThread();
    	
        int passengerId = p.getPassengerID();
        //p.setPassengerState(PassengerState.IN_FLIGHT);
    	repos.setPassengerState(passengerId, PassengerState.IN_FLIGHT);

        GenericIO.writelnString("O Passageiro esta a espera que o aviao chegue ao destino");
        while(!announceArrival){
            try {
                wait();
            } catch (InterruptedException e) {}
        }
    }

    /**
     * O Passageiro desembarca e se for o ultimo informa o Piloto
     */
    public synchronized void leavePlane() {
        Passenger p = (Passenger) Thread.currentThread();
        int passengerId = p.getPassengerID();
        //((Passenger) Thread.currentThread()).setPassengerState(PassengerState.AT_DESTINATION);
        passengerQueueInPlane.remove(passengerId);
        GenericIO.writelnString("O Passageiro " + passengerId + " sai do aviao");
        if(passengerQueueInPlane.isEmpty()){
            leavePlane = true;
            notifyAll();
            GenericIO.writelnString("O Passageiro " + passengerId + " avisa o Piloto que e o ultimo");
        }
    	((Passenger) Thread.currentThread()).setPassengerState(PassengerState.AT_DESTINATION);
    	repos.setPassengerState(passengerId, PassengerState.AT_DESTINATION);


    }

    
    //PILOTO

    /**
     * O Piloto viaja para o destino
     */
	public void flyToDestinationPoint() {
    	((Pilot) Thread.currentThread()).setPilotState(PilotState.FLYING_FORWARD);
    	repos.setPilotState(PilotState.FLYING_FORWARD);

        try
        { Thread.sleep ((long) (1 + 40 * Math.random ()));
        }
        catch (InterruptedException e) {}
	}
	
    /**
     * O Piloto anuncia a chegada ao destino
     */
    public synchronized void announceArrival() {
        GenericIO.writelnString("O Piloto anuncia a chegada ao Destino");
        announceArrival = true;
        notifyAll();
    	((Pilot) Thread.currentThread()).setPilotState(PilotState.DEBOARDING);
    	repos.setPilotState(PilotState.DEBOARDING);


        while(!leavePlane){ //Os Passageiros desembarcam 
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        announceArrival = false;
        leavePlane = false; //Pode sair acho
        notifyAll();
    }


    /**
     * O Piloto volta para a Origem
     */
	public void flyToDeparturePoint() {
    	((Pilot) Thread.currentThread()).setPilotState(PilotState.FLYING_BACK);
    	repos.setPilotState(PilotState.FLYING_BACK);

        try
        { Thread.sleep ((long) (1 + 40 * Math.random ()));
        }
        catch (InterruptedException e) {}
	}

}