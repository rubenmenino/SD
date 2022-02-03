package main;

import java.net.SocketTimeoutException;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//import Common.RunParameters;
import sharedRegions.DepartureAirport;
import sharedRegions.GeneralRepository;
import genclass.GenericIO;
import interfaces.DepartureAirportInterface;
import interfaces.Register;
import interfaces.RepositoryInterface;

/**
 * 
 * @author menino
 * @author renato
 *
 */
public class DepartureAirportMain {

	/**
	 * variavel para fechar o servidorc
	 */
	//public static boolean open;

	/**
	 * 
	 * @param args
	 * @throws java.rmi.AlreadyBoundException 
	 */
	public static void main(String[] args) throws java.rmi.AlreadyBoundException {
		
		Registry registry = null;
		RepositoryInterface repositoryInterface = null;
		/* create and install the security manager */
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());
        GenericIO.writelnString("Security Manager was installed!");
        
        
        try {
        	GenericIO.writelnString("1");
            registry = LocateRegistry.getRegistry(RunParameters.registryHostname, RunParameters.registryPort);
            GenericIO.writelnString("2");
            //repositoryInterface = (RepositoryInterface) registry.lookup(RunParameters.repositoryNameEntry);
            GenericIO.writelnString("3");
        } catch (RemoteException e) {
            GenericIO.writelnString("GeneralRepository lookup exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        
        DepartureAirport departureAirport = new DepartureAirport(RunParameters.MIN, RunParameters.MAX, RunParameters.N, repositoryInterface);
        DepartureAirportInterface departureAirportInterface = null;
       

        try {
        	departureAirportInterface = (DepartureAirportInterface) UnicastRemoteObject
                    .exportObject(departureAirport, RunParameters.DepartureAirportPort);
        } catch (RemoteException e) {
            GenericIO.writelnString("Departure Airport stub generation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        GenericIO.writelnString("Stub was generated!");
        
		
        /* register it with the general registry service */
        String nameEntryBase = "RegisterHandler";
        //Registry registry = null;
        Register reg = null;

        try {
            registry = LocateRegistry.getRegistry(RunParameters.registryHostname, RunParameters.registryPort);
        } catch (RemoteException e) {
            GenericIO.writelnString("RMI registry creation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        GenericIO.writelnString ("RMI registry was created!");

        try {
            reg = (Register) registry.lookup (nameEntryBase);
        } catch (RemoteException e) {
            GenericIO.writelnString("RegisterRemoteObject lookup exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            GenericIO.writelnString("RegisterRemoteObject not bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }

        try {
            reg.bind(RunParameters.departureAirportNameEntry, departureAirportInterface);
        } catch (RemoteException e) {
            GenericIO.writelnString("Park registration exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        catch (AlreadyBoundException e)
        { GenericIO.writelnString("Park already bound exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        GenericIO.writelnString("Park object was registered!");
	}

}
