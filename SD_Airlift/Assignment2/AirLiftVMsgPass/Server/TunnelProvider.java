package Server;

import Common.Message;
import Server.Communications.ServerCom;
import Server.interfaces.HostessInterface;
import Server.interfaces.PassengerInterface;
import Server.interfaces.PilotInterface;
import Server.proxies.SharedRegionProxy;

/**
 * 
 * @author menino
 * @author renato
 *
 */
public class TunnelProvider extends Thread implements HostessInterface, PilotInterface, PassengerInterface{
	/**
	 * SharedRegionProxy
	 */
	private SharedRegionProxy sharedRegion;
	
	/**
	 * ServerCommunication
	 */
	private ServerCom serverCom;
	
	/**
	 * id
	 */
	private int id;
	/**
	 * todos passageiros atendidos
	 */
	private boolean allPassengersAttended;
	
	/**
	 * estado do passageiro
	 */
	private int passengerState;
	/**
	 * estado da hospedeira	 
	 */
    private int hostessState;
	/**
	 * estado do piloto
	 */
    private int pilotState;
    
    
	/**
	 * 
	 * @param sharedRegion
	 * @param serverCom
	 */
	public TunnelProvider(SharedRegionProxy sharedRegion, ServerCom serverCom) {
		this.sharedRegion = sharedRegion;
		this.serverCom = serverCom;
	}
	
	/**
	 * 
	 */
    public void run() {
        Message incomingMessage = (Message) serverCom.readObject();
        Message response = sharedRegion.processAndReply(incomingMessage, serverCom);
        serverCom.writeObject(response);
        serverCom.close();
    }
	
    
    /**
     * getPassengerID
     */
	@Override
	public int getPassengerID() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setPassengerID(int id) {
		this.id = id;
	}

	/**
	 * 
	 */
	public boolean isAllPassengersAttended() {
        if(allPassengersAttended)
            return true;
        else
            return false;
	}

	/**
	 * 
	 * @param allPassengersAttended
	 */
	public void setAllPassengersAttended(boolean allPassengersAttended) {
		this.allPassengersAttended = allPassengersAttended;
	}

	/**
	 * @return passengerState
	 */
	public int getPassengerState() {
		return passengerState;
	}

	/**
	 * 
	 */
	public void setPassengerState(int passengerState) {
		this.passengerState = passengerState;
	}

	/**
	 * 
	 * @return hostessState
	 */
	public int getHostessState() {
		return hostessState;
	}

	/**
	 * setHostessState
	 */
	public void setHostessState(int hostessState) {
		this.hostessState = hostessState;
	}

	/**
	 * 
	 * @return pilotState
	 */
	public int getPilotState() {
		return pilotState;
	}

	/**
	 * @param pilotstate
	 */
	public void setPilotState(int pilotState) {
		this.pilotState = pilotState;
	}


}
