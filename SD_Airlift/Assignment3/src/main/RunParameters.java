package main;

// mudar as portas das shared regions
public class RunParameters {

	
	/**
	 * numero de passageiros
	 */
	public static final int N = 21;
	
	/**
	 * numero minimo de passageiros
	 */
	public static final int MIN = 5;
	
	/**
	 * numero maximo de passageiros
	 */
	public static final int MAX = 10;
	
	////////////////////////////////////////////////////////////////////////
	/**
	 * porta do DepartureAirport
	 */
	public static final int DepartureAirportPort = 22311; // mudar
	
	/**
	 * nome do servidor do DepartureAirport
	 */
	public static final String DepartureAirportHostName = "localhost";
			//"l040101-ws04.ua.pt";
	
	public static final String departureAirportNameEntry = "departureAirport";
	
	////////////////////////////////////////////////////////////////////////
	
	/**
	 * porta do DestinationAirport
	 */
	public static final int DestinationAirportPort = 22312; // mudar
	
	/**
	 * nome do servidor do DestinationAirport
	 */
	public static final String DestinationAirportHostName = "localhost";
			//"l040101-ws05.ua.pt";
	
	public static final String destinationAirportNameEntry = "destinationAirport";
	
	
	////////////////////////////////////////////////////////////////////////
	
	/**
	 * porta do Plane
	 */
	public static final int PlanePort = 22313; // mudar
	
	/**
	 * nome do servidor do Plane
	 */
	public static final String PlaneHostName = "localhost";
			//"l040101-ws06.ua.pt";
	
	public static final String planeAirportNameEntry = "plane";
	
	////////////////////////////////////////////////////////////////////////
	
	/**
	 * porta do Repositorio
	 */
	public static final int repositoryPort = 22314; // mudar

	/**
	 * nome do servidor do Repositorio
	 */
	public static final String repositoryHostName = "localhost";
			//"l040101-ws07.ua.pt";;
	
	public static final String repositoryNameEntry = "repository";
	
	//////////////////////////////////////////////////////////////////////////
	
	
	public final static String registryHostname = "localhost";
			//"l040101-ws10.ua.pt";
	
	public final static int registryPort = 22317;
	
	public final static String registryNameEntry = "registry";
	
	
	public final static int sharedRegions = 4;
	
	
	
	
	
	
	
	
	
	/**
	 * valor de resposta de sucesso
	 */
	public static final int GO = 200;
	
}
