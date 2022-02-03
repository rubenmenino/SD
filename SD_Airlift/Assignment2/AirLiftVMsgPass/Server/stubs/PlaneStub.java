package Server.stubs;
import Common.*;
import Client.entities.*;
import Client.Communications.*;

/**
 * 
 * @author renato
 * @author ruben
 */
public class PlaneStub {
	
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
	public PlaneStub() {
		this.serverHostName = Common.RunParameters.PlaneHostName;
		this.serverPortNumber = Common.RunParameters.PlanePort;
	}
	
	// piloto
	/**
	 * announceArrival
	 */
	public void announceArrival() {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Plane not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Pilot p =  (Pilot) Thread.currentThread();
		Message nm = new Message(MessageType.ANNOUNCEARRIVAL);
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
		
	}
	
	/**
	 * flyToDestinationPoint
	 */
	public void flyToDestinationPoint() {
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Plane not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Pilot p =  (Pilot) Thread.currentThread();
		Message nm = new Message(MessageType.FLYTODESTINATIONPOINT);
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();		
	}

	// passageiro
	/**
	 * boardPlane
	 */
	public void boardPlane() {
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Plane not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Passenger p =  (Passenger) Thread.currentThread();
		Message nm = new Message(p.getPassengerID(), MessageType.BOARDTHEPLANE);

		
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	/**
	 * waitForEndOfFlight
	 */
	public void waitForEndOfFlight() {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Plane not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Passenger p =  (Passenger) Thread.currentThread();
		Message nm = new Message(p.getPassengerID(), MessageType.WAITFORENDOFFLIGHT);

		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	/**
	 * leavePlane
	 */
	public void leavePlane() {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Plane not active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Passenger p =  (Passenger) Thread.currentThread();
		Message nm = new Message(p.getPassengerID(), MessageType.LEAVETHEPLANE);
		
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}

	
	
	
}
