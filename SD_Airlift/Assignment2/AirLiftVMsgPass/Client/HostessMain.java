package Client;

import Client.entities.Hostess;
import Server.stubs.DepartureAirportStub;
import Common.*;

/**
 * 
 * @author menino
 * @author renato
 *
 */
public class HostessMain {

	/**
     * Main method.
     *
     * @param args runtime arguments
     */
	public static void main(String args[]) {
		
		DepartureAirportStub departureAirport = new DepartureAirportStub();
		
		Hostess hostess = new Hostess(Common.RunParameters.N, departureAirport) ;
		hostess.start();
		System.out.print("Running Hostess!\n");
		try{
			hostess.join();
		} catch(InterruptedException e){}

	}
}
