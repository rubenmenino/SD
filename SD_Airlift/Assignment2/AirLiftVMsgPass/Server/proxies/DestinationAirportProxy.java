package Server.proxies;

import Client.entities.Passenger;
import Common.Message;
import Server.TunnelProvider;
import Server.Communications.ServerCom;
import Server.sharedRegions.DestinationAirport;

/**
 * 
 * @author renato
 * @author ruben
 *
 */
public class DestinationAirportProxy implements SharedRegionProxy{

	/**
	 * DestinationAirport
	 */
	private DestinationAirport destinationAirport;
	
	/**
	 * boolean
	 */
	public boolean open;
	
	/**
	 * 
	 * @param departureAirport
	 */
	public DestinationAirportProxy(DestinationAirport departureAirport) {
		this.destinationAirport = departureAirport;
		this.open = true;
    }
	
	/**
	 * @param msg
	 * @param scon
	 */
	public Message processAndReply(Message msg, ServerCom scon) {
		Message nm = new Message(false);
		TunnelProvider tp = (TunnelProvider) Thread.currentThread();

		switch(msg.getMt()) {
	    	
			case FLYTODEPARTUREPOINT:
				destinationAirport.flyToDeparturePoint();
	            nm.setOpDone(true);
	            System.out.println("Terminou o Voo");
	            break;
		}
			
		return nm;
	}

	/**
	 * boolean para parar de correr o server
	 */
    public synchronized boolean open(){
        return open;
    }
	
}
