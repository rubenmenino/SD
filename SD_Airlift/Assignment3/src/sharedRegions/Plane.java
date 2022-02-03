package sharedRegions;

import java.rmi.RemoteException;
import interfaces.*;


import java.util.LinkedList;

import java.util.Queue;


import main.*;
import entities.*;
import Server.interfaces.PassengerInterface;
import sharedRegions.*;
import genclass.GenericIO;
import interfaces.RepositoryInterface;


/**
 * 
 * @author renato
 * @author ruben
 */
public class Plane implements SharedRegion, PlaneInterface {

    /**
     * Lista de Passageiros
     */
	 private Queue<Integer> passengerQueueInPlane;
	 
	 private boolean hasTerminated=false;
	 
	 /**
	  * Referencia ao repositorio
	  */
	 private RepositoryInterface repos;
	 
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
    public Plane(RepositoryInterface repos) {
    	passengerQueueInPlane = new LinkedList<Integer>();
        this.leavePlane = false;
        this.announceArrival = false;
        this.repos = repos;
    }

    //PASSAGEIROS


    /**
     * O Passageiro entra no aviao
     */
    public synchronized void boardPlane(int passengerId) throws RemoteException{
        // VER AQUI
        repos.setPassengerState(passengerId, PassengerState.GOING_TO_AIRPORT);
        GenericIO.writelnString("A Hostess recebe os documentos");
        passengerQueueInPlane.add(passengerId);
        GenericIO.writelnString("O Passageiro " + passengerId + " embarca no aviao");
    }

    /**
     * O Passageiro espera que o aviao chegue ao destino
     */
    public synchronized void waitForEndOfFlight(int passengerId) throws RemoteException{
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
    public synchronized void leavePlane(int passengerId) throws RemoteException{
        //((Passenger) Thread.currentThread()).setPassengerState(PassengerState.AT_DESTINATION);
        passengerQueueInPlane.remove(passengerId);
        GenericIO.writelnString("O Passageiro " + passengerId + " sai do aviao");
        if(passengerQueueInPlane.isEmpty()){
            leavePlane = true;
            notifyAll();
            GenericIO.writelnString("O Passageiro " + passengerId + " avisa o Piloto que e o ultimo");
        }
    	repos.setPassengerState(passengerId, PassengerState.AT_DESTINATION);


    }

    
    //PILOTO

    /**
     * O Piloto viaja para o destino
     */
	public void flyToDestinationPoint() throws RemoteException{
		GenericIO.writelnString("VAI PARA O DESTINO");
        repos.setPilotState(PilotState.FLYING_FORWARD);
        try
        { Thread.sleep ((long) (1 + 40 * Math.random ()));
        }
        catch (InterruptedException e) {}
	}
	
    /**
     * O Piloto anuncia a chegada ao destino
     */
    public synchronized void announceArrival() throws RemoteException{
        GenericIO.writelnString("O Piloto anuncia a chegada ao Destino");
        announceArrival = true;
        notifyAll();
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

	@Override
	public boolean getTerminationState() throws RemoteException {
		// TODO Auto-generated method stub
		return hasTerminated;
	}


}