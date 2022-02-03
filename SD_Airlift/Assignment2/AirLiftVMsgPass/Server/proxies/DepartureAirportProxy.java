package Server.proxies;

import Client.entities.Passenger;
import Common.Message;
import Server.DepartureAirportMain;
import Server.DestinationAirportMain;
import Server.PlaneMain;
import Server.RepositoryMain;
import Server.TunnelProvider;
import Server.Communications.ServerCom;
import Server.sharedRegions.DepartureAirport;

/**
 * 
 * @author renato
 * @author ruben
 *
 */
public class DepartureAirportProxy implements SharedRegionProxy{
	/**
	 * DepartureAirport
	 */
	private DepartureAirport departureAirport;
	
	/**
	 * boolean
	 */
	public boolean open;
	
	/**
	 * 
	 * @param departureAirport
	 */
	public DepartureAirportProxy(DepartureAirport departureAirport) {
		this.departureAirport = departureAirport;
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
			// hostess
			case WAITFORNEXTFLIGHT:
				departureAirport.waitingForNextFlight();
				nm.setOpDone(true);
				break;
			case PREPAREFORPASSBOARDING:
				departureAirport.prepareForPassBoarding();;
				nm.setOpDone(true);
				break;
			case CHECKDOCUMENTS:
				departureAirport.checkDocuments();
				nm.setOpDone(true);
				break;
			case WAITFORNEXTPASSENGER:
				departureAirport.waitForNextPassenger();
				nm.setOpDone(true);
				break;
			case INFORMPLANEREADYTOTAKEOFF:
                tp.setAllPassengersAttended(msg.isAllPassAtended());
                boolean planeTakeOff = departureAirport.informPlaneReadyToTakeOff();
                nm.setBoolResponse(planeTakeOff);
				nm.setOpDone(true);
				break;
			
			// passengers
			case TRAVELTOAIRPORT:
                tp.setPassengerID(msg.getId());
                departureAirport.travelToAirport();
                nm.setOpDone(true);
                break;      
			case WAITINQUEUE:
				tp.setPassengerID(msg.getId());
                departureAirport.waitInQueue();
				nm.setOpDone(true);
				break;
			case SHOWDOCUMENTS:
				tp.setPassengerID(msg.getId());
                departureAirport.showDocuments();
				nm.setOpDone(true);
				break;
			
			// pilot
			case INFORMPLANEREADYFORBOARDING:
				departureAirport.informPlaneReadyForBoarding();
                nm.setOpDone(true);
				break;
			case WAITFORALLINBOARD:
				departureAirport.waitForAllInBoarding();
                nm.setOpDone(true);
				break;
				
			case PILOTFINISHACTIVITY:
                boolean value = departureAirport.endPilotActivity();
                nm.setBoolResponse(value);
                nm.setOpDone(true);
                synchronized (this){
                    if(value){
                        open = false; 
                        DepartureAirportMain.open = false;
                        DestinationAirportMain.open = false;
                        PlaneMain.open = false;
                        System.out.println("\n O Piloto acabou a atividade");
                    }
                }
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
