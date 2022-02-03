package Common;

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
	
	/**
	 * valor de resposta de sucesso
	 */
	public static final int GO = 200;
	
}
