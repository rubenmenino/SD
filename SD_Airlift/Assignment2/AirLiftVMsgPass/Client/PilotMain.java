package Client;

import Client.entities.Pilot;
import Server.stubs.DepartureAirportStub;
import Server.stubs.DestinationAirportStub;
import Server.stubs.PlaneStub;

/**
 * 
 * @author menino
 * @author renato
 *
 */
public class PilotMain {

	/**
     * Main method.
     *
     * @param args runtime arguments
     */
	public static void main(String args[]) {
		
		DepartureAirportStub departureAirport = new DepartureAirportStub();
		DestinationAirportStub destinationAirport = new DestinationAirportStub();
		PlaneStub plane = new PlaneStub();
		
		Pilot pilot = new Pilot(departureAirport, destinationAirport, plane) ;
		
		pilot.start();
		System.out.print("Running Pilot!\n");
		try{
			pilot.join();
		} catch(InterruptedException e){}
	}
}
