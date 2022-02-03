package Server;

import java.net.SocketTimeoutException;

import Common.RunParameters;
import Server.Communications.ServerCom;
import Server.proxies.DepartureAirportProxy;
import Server.sharedRegions.DepartureAirport;
import Server.sharedRegions.GeneralRepository;

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
	public static boolean open;

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		ServerCom serverCommunication, serverConnections;
		TunnelProvider provider;
		GeneralRepository repos = new GeneralRepository("log.txt");
		DepartureAirport departureAirport = new DepartureAirport(RunParameters.MIN, RunParameters.MIN, RunParameters.N, repos);
		DepartureAirportProxy departureAirportProxy = new DepartureAirportProxy(departureAirport);
		
		serverCommunication = new ServerCom(RunParameters.DepartureAirportPort);
		serverCommunication.start();
		
		
		System.out.print("DepartureAirport started\n\n");
		open = true;
		while(open){
            try {
            	serverConnections = serverCommunication.accept();

                provider = new TunnelProvider(departureAirportProxy, serverConnections);
                provider.start();
            }
                 catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
        }
		
	}

}
