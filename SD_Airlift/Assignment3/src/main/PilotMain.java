package main;

import java.rmi.NotBoundException;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import entities.Pilot;
//import Common.RunParameters;
import genclass.GenericIO;
import interfaces.DepartureAirportInterface;
import interfaces.DestinationAirportInterface;
import interfaces.PlaneInterface;

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
		
		DepartureAirportInterface departureInterface = null;
		DestinationAirportInterface destinationInterface = null;
		PlaneInterface plane = null;
		
		Registry registry = null;
		
        try {
            registry = LocateRegistry.getRegistry(RunParameters.registryHostname, RunParameters.registryPort);
        }
        catch (RemoteException e) {
            GenericIO.writelnString("RMI registry creation exception: " + e.getMessage());
            e.printStackTrace ();
            System.exit (1);
        }
        
        
        try {
        	departureInterface = (DepartureAirportInterface) registry.lookup(RunParameters.departureAirportNameEntry);
        	destinationInterface = (DestinationAirportInterface) registry.lookup(RunParameters.destinationAirportNameEntry);
        	plane = (PlaneInterface) registry.lookup(RunParameters.planeAirportNameEntry);
        } catch (RemoteException e) {
            GenericIO.writelnString("Departure Airport lookup exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            GenericIO.writelnString("Departure Airport not bound to registry: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        


		
		Pilot pilot = new Pilot(departureInterface, destinationInterface, plane) ;
		
		pilot.start();
		System.out.print("Running Pilot!\n");
		try{
			pilot.join();
		} catch(InterruptedException e){}
	}
}
