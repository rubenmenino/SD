package Server.stubs;

import Client.Communications.ClientCom;
import Client.entities.Pilot;
import Common.Message;
import Common.MessageType;

/**
 * 
 * @author renato
 * @author ruben
 */
public class DestinationAirportStub {

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
	public DestinationAirportStub() {
		this.serverHostName = Common.RunParameters.DestinationAirportHostName;
		this.serverPortNumber = Common.RunParameters.DestinationAirportPort;
	}
	
	// pilot
	/**
	 * flyToDeparturePoint
	 */
	public void flyToDeparturePoint() {
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
		Message nm = new Message(MessageType.FLYTODEPARTUREPOINT);
		
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
}