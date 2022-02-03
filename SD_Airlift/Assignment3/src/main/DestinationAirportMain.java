package main;

import java.net.SocketTimeoutException;

import java.nio.channels.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

//import Common.RunParameters;
import sharedRegions.DepartureAirport;
import sharedRegions.DestinationAirport;
import sharedRegions.GeneralRepository;
import genclass.GenericIO;
import interfaces.DepartureAirportInterface;
import interfaces.DestinationAirportInterface;
import interfaces.Register;
import interfaces.RepositoryInterface;

public class DestinationAirportMain {
	
	/**
	 * variavel para fechar o servidorc
	 */
	//public static boolean open;
	
	/**
	 * 
	 * @param args
	 */
public static void main(String[] args) throws java.rmi.AlreadyBoundException {
		
		/* create and install the security manager */
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new SecurityManager());
        GenericIO.writelnString("Security Manager was installed!");
        
        RepositoryInterface repositoryInterface = null;
        try {
            Registry registry = LocateRegistry.getRegistry(RunParameters.registryHostname, RunParameters.registryPort);
            repositoryInterface = (RepositoryInterface) registry.lookup(RunParameters.repositoryNameEntry);
        } catch (RemoteException e) {
            GenericIO.writelnString("GeneralRepository lookup exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (NotBoundException e) {
            GenericIO.writelnString("GeneralRepository not bound to registry: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        
        
        DestinationAirport deestinationAirport = new DestinationAirport(repositoryInterface);
        DestinationAirportInterface destinationAirportInterface = null;
       

        try {
        	destinationAirportInterface = (DestinationAirportInterface) UnicastRemoteObject
                    .exportObject((Remote) deestinationAirport, RunParameters.DepartureAirportPort);
        } catch (RemoteException e) {
            GenericIO.writelnString("Departure Airport stub generation exception: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        GenericIO.writelnString("Stub was generated!");
        
		
        /* register it with the general registry service */
        String nameEntryBase = "RegisterHandler";
        Registry registry = null;
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
            reg.bind(RunParameters.destinationAirportNameEntry, destinationAirportInterface);
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

