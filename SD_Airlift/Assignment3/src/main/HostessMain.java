package main;

import java.rmi.NotBoundException;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import entities.Hostess;
import genclass.GenericIO;
import interfaces.DepartureAirportInterface;
//import Common.*;

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
	 * @throws RemoteException 
     */
	public static void main(String args[])  {
		
		DepartureAirportInterface departureInterface = null;
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
        } catch (RemoteException e) {
            GenericIO.writelnString("Hostess lookup exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
            
        } catch (NotBoundException e) {
            GenericIO.writelnString("Hostess not bound to registry: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
		
		Hostess hostess = new Hostess(RunParameters.N, departureInterface) ;
		hostess.start();
		System.out.print("Running Hostess!\n");
		try{
			hostess.join();
		} catch(InterruptedException e){}

	}
}
