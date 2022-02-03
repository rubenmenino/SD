package Server;

import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;

import Common.RunParameters;
import Server.Communications.ServerCom;
import Server.proxies.DepartureAirportProxy;
import Server.proxies.GeneralRepositoryProxy;
import Server.sharedRegions.DepartureAirport;
import Server.sharedRegions.GeneralRepository;

public class RepositoryMain {

	/**
	 * variavel para fechar o servidorc
	 */
	public static boolean open;
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) throws SocketTimeoutException, FileNotFoundException  {
		
		ServerCom serverCommunication, serverConnections;
		TunnelProvider provider;
		GeneralRepository repos = new GeneralRepository("log.txt");
		
		GeneralRepositoryProxy repoProxy = new GeneralRepositoryProxy(repos);
		
		
		serverCommunication = new ServerCom(RunParameters.repositoryPort,1000);
		serverCommunication.start();
		
		
		System.out.print("Repository started");
		//open = true;
		while(repoProxy.open()){
            try {
            	serverConnections = serverCommunication.accept();

                provider = new TunnelProvider(repoProxy, serverConnections);
                provider.start();
            }
                 catch (SocketTimeoutException e) {
                e.printStackTrace();
            }
        }
	}
}
