package Server.proxies;

import Client.entities.Passenger;
import Common.Message;
import Server.TunnelProvider;
import Server.Communications.ServerCom;
import Server.sharedRegions.Plane;

/**
 * 
 * @author renato
 * @author ruben
 *
 */
public class PlaneProxy implements SharedRegionProxy{
	/**
	 * Plane
	 */
	private Plane plane;
	/**
	 * boolean
	 */
    public boolean open;

    /**
     * 
     * @param plane
     */
    public PlaneProxy(Plane plane){
        this.plane = plane;
		this.open = true;
    }
    
    /**
     * @param msg
     * @param scon
     */
	public Message processAndReply(Message msg, ServerCom scon){
        Message nm = new Message(false);
		TunnelProvider tp = (TunnelProvider) Thread.currentThread();

        switch(msg.getMt()){
        	// passengers
        	case BOARDTHEPLANE:
                tp.setPassengerID(msg.getId());
                plane.boardPlane();
                nm.setOpDone(true);
                break;
        	case WAITFORENDOFFLIGHT:
                tp.setPassengerID(msg.getId());
                plane.waitForEndOfFlight();
                nm.setOpDone(true);
                break;
        	case LEAVETHEPLANE:
                tp.setPassengerID(msg.getId());
                plane.leavePlane();
                nm.setOpDone(true);
                break;
                
             // pilot
        	case FLYTODESTINATIONPOINT:
                plane.flyToDestinationPoint();
                nm.setOpDone(true);
                break;
        	case ANNOUNCEARRIVAL:
                plane.announceArrival();
                nm.setOpDone(true);
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

