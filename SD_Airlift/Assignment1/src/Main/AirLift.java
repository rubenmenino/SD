package Main;

import SharedRegions.DepartureAirport;
import SharedRegions.DestinationAirport;
import SharedRegions.GeneralRepository;
import SharedRegions.Plane;
import entities.Hostess;
import entities.Passenger;
import entities.Pilot;
import genclass.GenericIO;
import Main.*;

/**
 * 
 * Classe AirLift
 *
 */
public class AirLift {

	
	/**
	   *    Main method.
	   *
	   *    @param args runtime arguments
	   */
	
    public static void main(String args[]){

        GeneralRepository repos = new GeneralRepository("data.txt");
        
        // Inicializar Regioes partilhadas
        DepartureAirport departureAirport = new DepartureAirport(SimulPar.MIN,SimulPar.MAX, repos);
        Plane plane = new Plane(repos);
        DestinationAirport destinationAirport = new DestinationAirport();
        
        //criacao de threads
        Passenger[] passengers = new Passenger[SimulPar.N];
        Pilot pilot = new Pilot(departureAirport,plane);
        Hostess hostess = new Hostess(SimulPar.N,departureAirport);

        //Iniciar as threads
        for(int i = 0; i< passengers.length; i++){
            passengers[i] = new Passenger(i+1,departureAirport,plane,destinationAirport);
            passengers[i].start();
        }
        pilot.start();
        hostess.start();

        //Join das threads
        for(int i = 0; i< passengers.length; i++){
            try {
                passengers[i].join();
            } catch (InterruptedException e) {}
        }
        try {
            pilot.join();
        } catch (InterruptedException e) {}

        try {
            hostess.join();
        } catch (InterruptedException e) {}
        
        // preenchimento de informacao no ficheiro data.txt
        repos.resumoDoPrograma();
    }

}