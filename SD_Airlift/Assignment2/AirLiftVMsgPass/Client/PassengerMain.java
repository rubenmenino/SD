package Client;

import Client.entities.Passenger;
import Common.RunParameters;
import Server.stubs.*;

/**
 * 
 * @author menino
 * @author renato
 *
 */

public class PassengerMain {

	/**
     * Main method.
     *
     * @param args runtime arguments
     */
	public static void main(String args[]) {
		
		DepartureAirportStub departureAirport = new DepartureAirportStub();
		DestinationAirportStub destinationAirport = new DestinationAirportStub();
		PlaneStub plane = new PlaneStub();
		Passenger[] passenger = new Passenger[RunParameters.N];
		for (int i = 0; i < passenger.length; i++) {
			passenger[i] = new Passenger(i, departureAirport,destinationAirport, plane);
		}
		
		for (int i = 0; i < passenger.length; i++) {
			System.out.print("Running Passenger!\n");
			passenger[i].start();
		}
		
		for (int i = 0; i < passenger.length; i++) {
			try{
				passenger[i].join();
			} catch(InterruptedException e){}
		}
	}
}
