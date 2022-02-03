package SharedRegions;

import java.util.LinkedList;
import java.util.Queue;

import commonInfrastructures.MemFIFO;
import entities.Hostess;
import entities.HostessState;
import entities.Passenger;
import entities.PassengerState;
import entities.Pilot;
import entities.PilotState;
import genclass.GenericIO;
import Main.SimulPar;
import SharedRegions.GeneralRepository;
import Main.AirLift;

public class DepartureAirport {

    /**
     * Lista de passageiros
     */
    private Queue<Integer> passengerQueue;
    
    /**
     * Referencia para o Repositorio
     */
    private GeneralRepository repos;
    
    
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
     * passageiro mostra os documentos à hospedeira
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
     * hospedeira informa que o aviao já tem as condiçoes necessarias para levantar voo
     */
    private boolean informPlaneReadyToLeave;
    
    /**
     * nao há mais passageiros, pode terminar a sua atividade
     */
    private boolean endPilotActivity;
    
    /**
     * informa a hospedeira que já é possivels os passageiros embarcarem
     */
    private boolean informPlaneReadyForBoarding;

    
    /**
     * 
     * @param MIN numero minimo de passageiros
     * @param MAX numero maximo de passageiros
     * @param repos repositorio
     */
    public DepartureAirport(int MIN,int MAX, GeneralRepository repos) {
        passengerQueue = new LinkedList<Integer>();
        passengersInPlane = 0;
        this.MIN = MIN;
        this.MAX = MAX;
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

    }

    
    //PASSAGEIROS

    /**
     * o passageiro esta na fila ate ser chamado
     */
    public synchronized void waitInQueue() {
    	
        Passenger pass = (Passenger) Thread.currentThread();
    	int passengerId = pass.getPassengerID();
    	pass.setPassengerState(PassengerState.IN_QUEUE);
        repos.setPassengerState(passengerId, pass.getPassengerState());
        GenericIO.writelnString("O Passageiro " + passengerId + " esta a espera na fila");
        passengerQueue.add(passengerId);
        
        notifyAll();
        
        while(passengerQueue.contains(passengerId) ){
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        
        pass.setPassengerState(PassengerState.IN_FLIGHT);
        repos.setPassengerState(passengerId, pass.getPassengerState());
    }

    
    /**
     * O Passageiro mostra os documentos 
     */
    public synchronized void showDocuments() {

        Passenger pass = (Passenger) Thread.currentThread();
        int passengerId = pass.getPassengerID();
        //pass.setPassengerState(PassengerState.IN_QUEUE);
        repos.setPassengerState(pass.getPassengerID(), pass.getPassengerState());
        
        GenericIO.writelnString("Sao pedidos os documentos ao Passageiro");
        while(!askDocuments){
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        showDocumentsToHostess = true;
        
        checked = true;
        passengersInPlane++;
        notifyAll();
        GenericIO.writelnString("O Passageiro mostrou os documentos");
    }
    
    
    //HOSTESS
    
    /**
     * Esperar que o aviao esteja pronto para os Passageiros entrarem
     */
    public synchronized void waitingForNextFlight() {
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
    public synchronized void prepareForPassBoarding() {
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
    	((Hostess) Thread.currentThread()).setHostessState(HostessState.WAIT_FOR_PASSENGER);
    	repos.setHostessState(passageiroAtual, HostessState.WAIT_FOR_PASSENGER);

    }

    /**
     * A Hostess pergunta pelos documentos e espera que o Passageiro os mostre
     */
    public synchronized void checkDocuments() {
    	
    	
    	GenericIO.writelnString("A Hostess pergunta pelo documentos ao Passageiro");
        askDocuments = true;
        notifyAll();
        
        while(!showDocumentsToHostess){ //A Hostess espera que o passageiro mostre os Documentos
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        
        ((Hostess) Thread.currentThread()).setHostessState(HostessState.CHECK_PASSENGER);
        repos.setHostessState(passageiroAtual, HostessState.CHECK_PASSENGER);
    }

    /**
     * Esperar que o Passageiro mostre os documentos a Hostess e depois espera pelo proximo passageiro
     */
    public synchronized void waitForNextPassenger() {
    	((Hostess) Thread.currentThread()).setHostessState(HostessState.WAIT_FOR_PASSENGER);
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
    public synchronized void informPlaneReadyToTakeOff() {
        Hostess h = (Hostess) Thread.currentThread();
        boolean allPassengersChecked = h.allPassengersChecked();

        boolean condition1 = (passengerQueue.isEmpty() && allPassengersChecked);
        boolean condition2 = passengersInPlane == MAX;
        boolean condition3 = (passengerQueue.isEmpty() && passengersInPlane > MIN)	;
        
        //caso os passageiros no Aviao sejam o minimo pedido ou o aviao esteja cheio ou a fila esta vazia e sao os ultimos passageiros, o aviao pode descolar
        if( condition1 ||  condition2 || condition3){
            informPlaneReadyToLeave = true;
            if(allPassengersChecked){
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
            ((Hostess) Thread.currentThread()).setHostessState(HostessState.READY_TO_FLY);
        	repos.setHostessState(passageiroAtual, HostessState.READY_TO_FLY);
        }

    }

    
    //PILOTO
    
    /**
     * O Piloto informa a Hostess que o aviao esta pronto para voar
     */
    public synchronized void informPlaneReadyForBoarding() {
        GenericIO.writelnString("O Piloto informa que o aviao esta pronto para voar");
        informPlaneReadyForBoarding = true;
        notifyAll();
    	((Pilot) Thread.currentThread()).setPilotState(PilotState.READING_FOR_BOARDING);
    	repos.setPilotState(PilotState.READING_FOR_BOARDING);


    }

    /**
     * O Piloto espera ate a Hostess dar o sinal de que os passageiros ja embarcaram e que o aviao pode voar
     */
    public synchronized void waitForAllInBoarding() {
    	((Pilot) Thread.currentThread()).setPilotState(PilotState.WAIT_FOR_BOARDING);
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
    

    public synchronized boolean endPilotActivity() {
        return endPilotActivity;
    }

    public synchronized void setEndPilotActivity(boolean endPilotActivity) {
        this.endPilotActivity = endPilotActivity;
    }
}