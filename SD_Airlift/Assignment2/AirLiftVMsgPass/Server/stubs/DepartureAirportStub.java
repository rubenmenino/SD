package Server.stubs;
import Common.*;
import Client.entities.*;
import Client.Communications.*;

/**
 * 
 * @author renato
 * @author ruben
 */
public class DepartureAirportStub implements DepartureHostess, DeparturePassenger, DeparturePilot{

	/**
	 * nome do servidor
	 */
	private String serverHostName;
	
	/**
	 * numero da porta do servidor
	 */
	private int serverPortNumber;

	/**
	 * construtor
	 */
	public DepartureAirportStub() {
		this.serverHostName = Common.RunParameters.DepartureAirportHostName;
		this.serverPortNumber = Common.RunParameters.DepartureAirportPort;
	}
	
	// passageiro
	/**
	 * travelToAirport
	 */
	public void travelToAirport() {
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Departure Airport not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Passenger p =  (Passenger) Thread.currentThread();
		Message nm = new Message(p.getPassengerID(), MessageType.TRAVELTOAIRPORT);
		
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
		
	}
	
	/**
	 * waitInQueue
	 */
	public void waitInQueue() {
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Departure Airport not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Passenger p =  (Passenger) Thread.currentThread();
		Message nm = new Message(p.getPassengerID(), MessageType.WAITINQUEUE);
		
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	/**
	 * showDocuments
	 */
	public void showDocuments() {
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Departure Airport not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Passenger p =  (Passenger) Thread.currentThread();
		Message nm = new Message(p.getPassengerID(), MessageType.SHOWDOCUMENTS);
		
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	// piloto
	/**
	 * informPlaneReadyForBoarding
	 */
	public void informPlaneReadyForBoarding() {
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Departure Airport not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Pilot p =  (Pilot) Thread.currentThread();
		Message nm = new Message(MessageType.INFORMPLANEREADYFORBOARDING);

		
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	/**
	 * waitForAllInBoarding
	 */
	public void waitForAllInBoarding() {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Departure Airport not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Pilot p =  (Pilot) Thread.currentThread();
		Message nm = new Message(MessageType.WAITFORALLINBOARD);
		
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	/**
	 * endPilotActivity
	 */
	public boolean endPilotActivity() {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Departure Airport not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Pilot p =  (Pilot) Thread.currentThread();
		Message nm = new Message(MessageType.PILOTFINISHACTIVITY);
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
        return responseOut.isBoolResponse();
	}
	
	
	// hospedeira
	/**
	 * waitingForNextFlight
	 */
	public void waitingForNextFlight() {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Departure Airport not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		
		Hostess h =  (Hostess) Thread.currentThread();
		Message nm = new Message(MessageType.WAITFORNEXTFLIGHT);
		

		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	/**
	 * prepareForPassBoarding
	 */
	public void prepareForPassBoarding() {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Departure Airport not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Hostess h =  (Hostess) Thread.currentThread();
		Message nm = new Message(MessageType.PREPAREFORPASSBOARDING);
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	/**
	 * checkDocuments
	 */
	public void checkDocuments() {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Departure Airport not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Hostess h =  (Hostess) Thread.currentThread();
		Message nm = new Message(MessageType.CHECKDOCUMENTS);
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	/**
	 * waitForNextPassenger
	 */
	public void waitForNextPassenger() {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Departure Airport not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Hostess h =  (Hostess) Thread.currentThread();
		Message nm = new Message(MessageType.WAITFORNEXTPASSENGER);
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	/**
	 * informPlaneReadyToTakeOff
	 */
	public boolean informPlaneReadyToTakeOff() {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Departure Airport not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Hostess h =  (Hostess) Thread.currentThread();
		Message nm = new Message(MessageType.INFORMPLANEREADYTOTAKEOFF);
		nm.setAllPassAtended(h.allPassengersChecked());
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
        return responseOut.isBoolResponse();
	}

	
	
}
