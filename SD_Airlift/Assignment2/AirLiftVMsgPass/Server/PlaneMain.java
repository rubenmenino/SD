package Server;

import java.net.SocketTimeoutException;

import Common.RunParameters;
import Server.Communications.ServerCom;
import Server.proxies.DepartureAirportProxy;
import Server.proxies.PlaneProxy;
import Server.sharedRegions.*;


public class PlaneMain {

	/**
	 * variavel para fechar o servidorc
	 */
	public static boolean open;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		ServerCom serverCommunication, serverConnections;
		TunnelProvider provider;
		GeneralRepository repos = new GeneralRepository("log.txt");
		Plane plane = new Plane(repos);
		PlaneProxy planeProxy = new PlaneProxy(plane);
		
		serverCommunication = new ServerCom(RunParameters.PlanePort);
		serverCommunication.start();
		
		
		System.out.print("Plane started\n\n");
		open = true;
		while(open){
            try {
            	serverConnections = serverCommunication.accept();

                provider = new TunnelProvider(planeProxy, serverConnections);
                provider.start();
            }
                 catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
        }
		
	}
}
