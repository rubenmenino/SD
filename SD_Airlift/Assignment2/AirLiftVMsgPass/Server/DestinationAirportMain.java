package Server;

import java.net.SocketTimeoutException;

import Common.RunParameters;
import Server.Communications.ServerCom;
import Server.proxies.DestinationAirportProxy;
import Server.sharedRegions.DestinationAirport;
import Server.sharedRegions.GeneralRepository;

public class DestinationAirportMain {
	
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
		DestinationAirport destinationAirport = new DestinationAirport(repos);
		DestinationAirportProxy destinationAirportProxy = new DestinationAirportProxy(destinationAirport);

		serverCommunication = new ServerCom(RunParameters.DestinationAirportPort);
		serverCommunication.start();
		
		
		System.out.print("Destination started\n\n");
		open = true;
		while(open){
            try {
            	serverConnections = serverCommunication.accept();

                provider = new TunnelProvider(destinationAirportProxy, serverConnections);
                provider.start();
            }
                 catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
        }
		
	}

}

