package sharedRegions;

import java.rmi.RemoteException;




import java.util.LinkedList;

import java.util.Queue;

//import Common.*;
import entities.*;
import Server.interfaces.*;
import sharedRegions.*;
import genclass.GenericIO;
import interfaces.DepartureAirportInterface;
import interfaces.RepositoryInterface;

public class DepartureAirport implements DepartureAirportInterface, SharedRegion{

    /**
     * Lista de passageiros
     */
    private Queue<Integer> passengerQueue;
    
    /**
     * Referencia para o Repositorio
     */
    private RepositoryInterface repos;
    
    private boolean hasTerminated=false;
    /**
     * numero do voo
     */
    private int numeroDoVoo;

    /**
     * numero de passageiros no aviao
     */
    private int passengersInPlane;
    
    /**
     * passageiro atual
     */
    private int passageiroAtual;

    
    /**
     * numero minimo de passageiros no aviao
     */
    private int MIN;
    
    /**
     * numero maximo de passageiros no aviao
     */
    private int MAX;

    /**
     * numero de passageiros total
     */
    private int N;
    
    /**
     * numero de passageiros checked
     */
    private int numberChecked;
    
    /**
     * passageiro mostra os documentos � hospedeira
     */
    private boolean showDocumentsToHostess;
    
    /**
     * passageiro mostrou os documementos
     */
    private boolean checked;

    /**
     * hospedeira espera por um novo passageiro
     */
    private boolean waitForNextPassenger;
    
    /**
     * hospedeira pede documentos a um passageiro
     */
    private boolean askDocuments;
    
    /**
     * hospedeira informa que o aviao j� tem as condi�oes necessarias para levantar voo
     */
    private boolean informPlaneReadyToLeave;
    
    /**
     * nao h� mais passageiros, pode terminar a sua atividade
     */
    private boolean endPilotActivity;
    
    /**
     * informa a hospedeira que j� � possivels os passageiros embarcarem
     */
    private boolean informPlaneReadyForBoarding;

    
    /**
     * 
     * @param MIN numero minimo de passageiros
     * @param MAX numero maximo de passageiros
     * @param repositoryInterface repositorio
     */
    public DepartureAirport(int MIN,int MAX,int N, RepositoryInterface repos) {
        passengerQueue = new LinkedList<Integer>();
        passengersInPlane = 0;
        this.MIN = MIN;
        this.MAX = MAX;
        this.N = N;
        showDocumentsToHostess = false;
        checked = false;
        waitForNextPassenger = true;
        askDocuments = false;
        informPlaneReadyToLeave = false;
        endPilotActivity = false;
        informPlaneReadyForBoarding = false;
        this.repos = repos;
        this.passageiroAtual = 0;
        this.numeroDoVoo=0;
        this.numberChecked = 0;

    }

    
    //PASSAGEIROS

    /**
     * 
     */
    @Override
    public synchronized void travelToAirport(int passengerId) throws RemoteException{        
        repos.setPassengerState(passengerId, PassengerState.GOING_TO_AIRPORT);
        try
        { Thread.sleep ((long) (1 + 40 * Math.random ()));
        }
        catch (InterruptedException e) {}
        GenericIO.writelnString("O Passageiro " + passengerId + " viaja para o Aeroporto");
        notifyAll();

    }
	
    /**
     * o passageiro esta na fila ate ser chamado
     */
    @Override
    public synchronized void waitInQueue(int passengerId) throws RemoteException{

        repos.setPassengerState(passengerId, PassengerState.IN_QUEUE);
        GenericIO.writelnString("O Passageiro " + passengerId + " esta a espera na fila");
        passengerQueue.add(passengerId);
        
        notifyAll();
        
        while(passengerQueue.contains(passengerId) ){
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        repos.setPassengerState(passengerId, PassengerState.IN_FLIGHT);
    }

    
    /**
     * O Passageiro mostra os documentos 
     */
    @Override
    public synchronized void showDocuments(int passengerId) throws RemoteException {

    	// VER AQUI
        repos.setPassengerState(passengerId, PassengerState.IN_QUEUE);
        
        GenericIO.writelnString("Sao pedidos os documentos ao Passageiro");
        while(!askDocuments){
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        showDocumentsToHostess = true;
        
        checked = true;
        passengersInPlane++;
        numberChecked++;
        notifyAll();
        GenericIO.writelnString("O Passageiro mostrou os documentos");
    }
    
    
    //HOSTESS
    
    /**
     * Esperar que o aviao esteja pronto para os Passageiros entrarem
     */
    @Override
    public synchronized void waitingForNextFlight() throws RemoteException{
        while(!informPlaneReadyForBoarding){
            GenericIO.writelnString("A Hostess esta a espera que o aviao esteja pronto para os Passageiros entrarem");
            try {
                wait();
            } catch (InterruptedException e) {}
        }
    	//((Hostess) Thread.currentThread()).setHostessState(HostessState.WAIT_FOR_FLIGHT);
    	repos.setHostessState(passageiroAtual, HostessState.WAIT_FOR_FLIGHT);
    	
    	
    }

    /**
     * A Hostess esta a espera de passageiros para o checkin e remove um Passageiro da fila 
     */
    @Override
    public synchronized void prepareForPassBoarding() throws RemoteException{
        waitForNextPassenger = false;
        notifyAll();
        int nextPassenger;
        while(passengerQueue.isEmpty()){
            GenericIO.writelnString("A Hostess esta a espera de passageiros para o checkin");
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        nextPassenger = passengerQueue.remove();
        notifyAll();
    	repos.setHostessState(passageiroAtual, HostessState.WAIT_FOR_PASSENGER);

    }

    /**
     * A Hostess pergunta pelos documentos e espera que o Passageiro os mostre
     */
    @Override
    public synchronized void checkDocuments() throws RemoteException{
    	
    	
    	GenericIO.writelnString("A Hostess pergunta pelo documentos ao Passageiro");
        askDocuments = true;
        notifyAll();
        
        while(!showDocumentsToHostess){ //A Hostess espera que o passageiro mostre os Documentos
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        
        repos.setHostessState(passageiroAtual, HostessState.CHECK_PASSENGER);
    }

    /**
     * Esperar que o Passageiro mostre os documentos a Hostess e depois espera pelo proximo passageiro
     */
    @Override
    public synchronized void waitForNextPassenger() throws RemoteException{
    	repos.setHostessState(passageiroAtual, HostessState.WAIT_FOR_PASSENGER);
    	waitForNextPassenger = true;
        showDocumentsToHostess = false;    
        notifyAll();

        while(!checked){
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        if(!passengerQueue.isEmpty()) {
        	passageiroAtual = passengerQueue.peek();  //Atualizar para o primeiro passageiro da fila
        }
        checked = false;
    	
    }

    /**
     * A Hostess verifica se o aviao esta pronto para voar e avisa o Piloto caso esteja
     */
    @Override
    public synchronized boolean informPlaneReadyToTakeOff() throws RemoteException{
    	
        boolean condition1 = (passengerQueue.isEmpty() && numberChecked == N);
        boolean condition2 = passengersInPlane == MAX;
        boolean condition3 = (passengerQueue.isEmpty() && passengersInPlane > MIN)	;
        
        //caso os passageiros no Aviao sejam o minimo pedido ou o aviao esteja cheio ou a fila esta vazia e sao os ultimos passageiros, o aviao pode descolar
        if( condition1 ||  condition2 || condition3){
            informPlaneReadyToLeave = true;
            if(numberChecked == N){
            	endPilotActivity = true;
                GenericIO.writelnString("A Hostess informa o Piloto que ele pode acabar a atividade");
            }
            notifyAll();
            GenericIO.writelnString("A Hostess informa que o aviao pode descolar");

            while(informPlaneReadyForBoarding){
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            numeroDoVoo++;
            repos.setInfoVoo(numeroDoVoo, passengersInPlane);
            passengersInPlane = 0;
            informPlaneReadyToLeave = false;
        	repos.setHostessState(passageiroAtual, HostessState.READY_TO_FLY);
        	return true;
        }
        return false;

    }

    
    //PILOTO
    
    /**
     * O Piloto informa a Hostess que o aviao esta pronto para voar
     */
    @Override
    public synchronized void informPlaneReadyForBoarding() throws RemoteException{
        GenericIO.writelnString("O Piloto informa que o aviao esta pronto para voar");
        informPlaneReadyForBoarding = true;
        notifyAll();
    	repos.setPilotState(PilotState.READING_FOR_BOARDING);
    }

    /**
     * O Piloto espera ate a Hostess dar o sinal de que os passageiros ja embarcaram e que o aviao pode voar
     */
    public synchronized void waitForAllInBoarding() throws RemoteException{
    	repos.setPilotState(PilotState.WAIT_FOR_BOARDING);
        GenericIO.writelnString("O Piloto esta a espera que os Passageiros embarquem no aviao");
        while(!informPlaneReadyToLeave){
            try {
                wait();
            } catch (InterruptedException e) {}
        }

        informPlaneReadyForBoarding = false;
        notifyAll();
        GenericIO.writelnString("O Embarque esta completo e o aviao pode voar");
    }
    
    /**
     * 
     * @return
     */
    @Override
    public synchronized boolean endPilotActivity() throws RemoteException{
    	if(endPilotActivity == true) {
    		repos.resumoDoPrograma();
    		
    		
    	}
        return endPilotActivity;
    }
    
    /**
     * 
     * @param endPilotActivity
     */
    public synchronized void setEndPilotActivity(boolean endPilotActivity) throws RemoteException{
        this.endPilotActivity = endPilotActivity;
    }


	@Override
	public boolean getTerminationState() throws RemoteException {
		// TODO Auto-generated method stub
		return hasTerminated;
	}


}