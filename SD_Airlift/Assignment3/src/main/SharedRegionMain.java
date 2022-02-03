package main;
import java.lang.Class;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.Register;
import interfaces.RepositoryInterface;
import main.*;
import sharedRegions.SharedRegion;


public class SharedRegionMain {


	public static void main(String args[]) {
		
		/* Shared region reflection */
		String regionClassName = args[0].substring(0, 1).toUpperCase() + args[0].substring(1);
		Class<?> shRegClass = null;
		Constructor<?> shRegConstructor = null;

		/* get location of the generic registry service */
		String rmiRegHostName =  RunParameters.registryHostname;
		int rmiRegPortNumb = RunParameters.registryPort;
		
		/* name entries */
		String nameEntryBase = RunParameters.registryNameEntry;
		String nameEntryObject = regionClassName;

		/* Registry and register */
		Registry registry = null;
		Register reg = null;
		
		try {
			shRegClass = Class.forName("sharedRegions." + regionClassName);
		} catch (ClassNotFoundException e) {
			System.out.println("No class found exception: " + e.getMessage ());
        	e.printStackTrace ();
        	System.exit (1);
		}
		
		try {
			shRegConstructor = shRegClass.getConstructor(RepositoryInterface.class);
		}
		catch (NoSuchMethodException e) {
			System.out.println("No such method exception: " + e.getMessage ());
			e.printStackTrace ();
			System.exit (1);
		}
		

		/* create and install the security manager */

		if (System.getSecurityManager () == null)
			System.setSecurityManager (new SecurityManager ());
		System.out.println("Security manager was installed!");


		/* Get registry */
		try
		{ registry = LocateRegistry.getRegistry (rmiRegHostName, rmiRegPortNumb);
		}
		catch (RemoteException e)
		{ System.out.println("RMI registry creation exception: " + e.getMessage ());
		e.printStackTrace ();
		System.exit (1);
		}
		System.out.println("RMI registry was created!");

		/* Get repository from RMI server */
		RepositoryInterface repInt = null;

		try {
			repInt = (RepositoryInterface) registry.lookup(RunParameters.repositoryNameEntry);
		} catch (RemoteException e) { 
            System.out.println("Repository lookup exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit (1);
        }
        catch (NotBoundException e)
        { 
            System.out.println("Repository not bound exception: " + e.getMessage ());
            e.printStackTrace ();
            System.exit(1);
		}
		
		/* Initialize shared region */
		SharedRegion sharedRegion = null;
		
		try{
			sharedRegion = (SharedRegion) shRegConstructor.newInstance(repInt);
		}
		catch(InstantiationException e){
			System.out.println(regionClassName + " instantiation exception: " + e.getMessage ());
			e.printStackTrace ();
			System.exit (1);
		}
		catch(IllegalAccessException e){
			System.out.println(regionClassName + " IllegalAccess exception: " + e.getMessage ());
			e.printStackTrace ();
			System.exit (1);
		} 
		catch(InvocationTargetException e){
			System.out.println(regionClassName + " InvocationTarget exception: " + e.getMessage ());
			e.printStackTrace ();
			System.exit (1);
		}
		Remote sharedRegionInt = null;

		try
		{ sharedRegionInt = UnicastRemoteObject.exportObject((Remote) sharedRegion, 
							RunParameters.class.getField(regionClassName + "Port").getInt(null));
		}
		catch (RemoteException e)
		{ 	System.out.println(regionClassName + " stub generation exception: " + e.getMessage ());
			e.printStackTrace ();
			System.exit (1);
		}
		catch(NoSuchFieldException e){
			System.out.println(regionClassName + " no such field exception: " + e.getMessage ());
			e.printStackTrace ();
			System.exit (1);
		}
		catch(IllegalAccessException e){
			System.out.println(regionClassName + " IllegalAccess exception: " + e.getMessage ());
			e.printStackTrace ();
			System.exit (1);
		}

		System.out.println("Stub was generated!");

		/* register it with the general registry service */
		try
		{ reg = (Register) registry.lookup (nameEntryBase);
		}
		catch (RemoteException e)
		{ System.out.println("RegisterRemoteObject lookup exception: " + e.getMessage ());
		e.printStackTrace ();
		System.exit (1);
		}
		catch (NotBoundException e)
		{ System.out.println("RegisterRemoteObject not bound exception: " + e.getMessage ());
		e.printStackTrace ();
		System.exit (1);
		}

		try
		{ reg.bind (nameEntryObject, sharedRegionInt);
		}
		catch (RemoteException e)
		{ System.out.println(regionClassName + " registration exception: " + e.getMessage ());
		e.printStackTrace ();
		System.exit (1);
		}
		catch (AlreadyBoundException e)
		{ System.out.println(regionClassName + " already bound exception: " + e.getMessage ());
		e.printStackTrace ();
		System.exit (1);
		}
		System.out.printf("Binded object %s\n",nameEntryObject);
		System.out.println(regionClassName + " object was registered!");

		/* Wait for shared region to terminate */
		try {
			while(!sharedRegion.getTerminationState()) {
				synchronized(sharedRegion) {
					sharedRegion.wait();
				}
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread of " + regionClassName + " was interrupted.");
			System.exit(1);
		}
		catch (RemoteException e)
		{ 
			System.out.println(regionClassName + " remote exception: " + e.getMessage ());
			e.printStackTrace ();
			System.exit (1);
		}

		System.out.println(regionClassName + " has finished");

		/* Unregister this shared region */
		try {
			reg.unbind(nameEntryObject);
		}
        catch (RemoteException e)
        { System.out.println(regionClassName + " unregistration exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        } catch (NotBoundException ex) {
          System.out.println(regionClassName + " unregistration exception: " + ex.getMessage ());
          ex.printStackTrace ();
          System.exit (1);
        }
        System.out.println(regionClassName + " was unregistered!");
		
		/* Unexport this shared region */
		try
        { UnicastRemoteObject.unexportObject ((Remote) sharedRegion, false);
        }
        catch (RemoteException e)
        { System.out.println(regionClassName + " unexport exception: " + e.getMessage ());
          e.printStackTrace ();
          System.exit (1);
        }
		
		System.out.printf("%s: Bye!\n",regionClassName);
	}
}

