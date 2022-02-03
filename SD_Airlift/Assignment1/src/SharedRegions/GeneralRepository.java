package SharedRegions;

import Main.*;
import entities.*;
import genclass.GenericIO;
import genclass.TextFile;
import java.util.Objects;

/**
 * General Repository.
 *
 * It is responsible to keep the visible internal state of the problem and to
 * provide means for it to be printed in the logging file. It is implemented as
 * an implicit monitor. All public methods are executed in mutual exclusion.
 * There are no internal synchronization points.
 */

public class GeneralRepository {
	/**
	 * Name of the logging file.
	 */

	private String logFileName;

	/**
	 * State of the passengers
	 */

	private int[] passengerState;

	/**
	 * State of the hostess
	 */

	private int hostessState;

	/**
	 * State of the pilot.
	 */

	private int pilotState;

	/**
	 * Instantiation of a general repository object.
	 *
	 * @param logFileName name of the logging file
	 */

	/**
	 * numero passageiro na fila
	 */
	private int InQ; 
	
	/**
	 * numero passageiros no aviao
	 */
	private int InF; 
	
	/**
	 * numero de passageiros que ja chegaram ao destino
	 */
	private int PTAL;

	
	private int[] passAnteriorState;
	private int pilotAnteriorState;
	private int hostessAnteriorState;
	
	private int numeroDeVoo;
	private int ndoVoo;
	private String[] informacaoDosVoos;
	private int passageiroAtual;

	public GeneralRepository (String logFileName)
   {
	   if ((logFileName == null) || Objects.equals (logFileName, ""))
    	  this.logFileName = "logger";
      else this.logFileName = logFileName;
      passengerState = new int [SimulPar.N+1];
      passAnteriorState = new int [SimulPar.N+1];
      for (int i = 0; i < SimulPar.N; i++) {
      		passengerState[i] = PassengerState.GOING_TO_AIRPORT;
      	  	passAnteriorState[i] = 0;
      }
      
      hostessState = HostessState.WAIT_FOR_FLIGHT;
      hostessAnteriorState = 0;
      pilotState = PilotState.AT_TRANSFER_GATE;
      pilotAnteriorState = 0;
      
      this.InQ = 0;
      this.InF = 0;
      this.PTAL = 0;
      
      numeroDeVoo = 1;
      passageiroAtual = 0;
      informacaoDosVoos = new String[10];
      
      
      reportInitialStatus ();
      
   }

	/**
	 * Set passenger state.
	 *
	 * @param id    passenger id
	 * @param state passenger state
	 */

	public synchronized void setPassengerState(int id, int state) {
		this.passengerState[id] = state;
		reportStatus();
	}

	/**
	 * Set hostess state.
	 *
	 * @param state hostess state
	 */

	public synchronized void setHostessState(int idPassageiro, int state) {
		this.passageiroAtual = idPassageiro;
		hostessState = state;
		reportStatus();
	}

	/**
	 * Set pilot state.
	 *
	 * @param state pilot state
	 */

	public synchronized void setPilotState(int state) {
		pilotState = state;
		reportStatus();
	}
	
	
	public synchronized void setInfoVoo(int nVoo, int npassageiros) {
		this.ndoVoo = nVoo;
		informacaoDosVoos[nVoo-1] = nVoo + ":" + npassageiros;
	}
	

	/**
	   *  Write the header to the logging file.
	   *
	   *  
	   *  Internal operation.
	   */


	private void reportInitialStatus() {
		TextFile log = new TextFile(); // instantiation of a text file handler

		if (!log.openForWriting(".", logFileName)) {
			GenericIO.writelnString("The operation of creating the file " + logFileName + " failed!");
			System.exit(1);
		}
		log.writelnString("                Problem of AirLift ");
		log.writelnString(
				"  PT    HT    P00   P01   P02   P03   P04   P05   P06   P07   P08   P09   P10   P11   P12   P13   P14   P15   P16   P17   P18   P19   P20  InQ   InF  PTAL");
		if (!log.close()) {
			GenericIO.writelnString("The operation of closing the file " + logFileName + " failed!");
			System.exit(1);
		}
		reportStatus();
	}

	/**
	   *  Write a state line at the end of the logging file.
	   *
	   *  The current state of the hostess, pilot and the passengers is organized in a line to be printed.
	   *  Internal operation.
	   */

	private void reportStatus() {
		TextFile log = new TextFile(); // instantiation of a text file handler

		String lineStatus = ""; // state line to be printed

		if (!log.openForAppending(".", logFileName)) {
			GenericIO.writelnString("The operation of opening for appending the file " + logFileName + " failed!");
			System.exit(1);
		}

		switch (pilotState) {
		case PilotState.AT_TRANSFER_GATE:
			lineStatus += " ATRG ";
			pilotAnteriorState = PilotState.AT_TRANSFER_GATE;
			break;
		case PilotState.READING_FOR_BOARDING:
			lineStatus += " RDFB ";
			log.writelnString("\nFlight " + numeroDeVoo + " : boarding started.");
			pilotAnteriorState = PilotState.READING_FOR_BOARDING;
			break;
		case PilotState.WAIT_FOR_BOARDING:
			lineStatus += " WTFB ";
			pilotAnteriorState = PilotState.WAIT_FOR_BOARDING;
			break;
		case PilotState.FLYING_FORWARD:
			lineStatus += " FLFW ";
			pilotAnteriorState = PilotState.FLYING_FORWARD;
			break;
		case PilotState.DEBOARDING:
			lineStatus += " DRPP ";
			if (pilotAnteriorState == PilotState.FLYING_FORWARD) log.writelnString("\nFlight " + numeroDeVoo + " : arrived.");
			pilotAnteriorState = PilotState.DEBOARDING;
			break;
		case PilotState.FLYING_BACK:
			lineStatus += " FLBK ";
			if (pilotAnteriorState == PilotState.DEBOARDING) {
				log.writelnString("\nFlight " + numeroDeVoo + " : returning.");
				numeroDeVoo++;
			}
			pilotAnteriorState = PilotState.FLYING_BACK;
			break;
		}

		switch (hostessState) {
		case HostessState.WAIT_FOR_FLIGHT:
			lineStatus += " WTFL ";
			hostessAnteriorState = HostessState.WAIT_FOR_FLIGHT;
			break;
		case HostessState.WAIT_FOR_PASSENGER:
			lineStatus += " WTPS ";
			hostessAnteriorState = HostessState.WAIT_FOR_PASSENGER;
			break;
		case HostessState.CHECK_PASSENGER:
			lineStatus += " CKPS ";
			if (hostessAnteriorState == HostessState.WAIT_FOR_PASSENGER) {
				//InQ--;
				
			}
			hostessAnteriorState = HostessState.CHECK_PASSENGER;
			break;
		case HostessState.READY_TO_FLY:
			lineStatus += " RDTF ";
			if (hostessAnteriorState == HostessState.WAIT_FOR_PASSENGER) {
				log.writelnString("\nFlight " + numeroDeVoo + " : departed with " + InF + " passengers.");
			}
			hostessAnteriorState = HostessState.READY_TO_FLY;
			break;
		}

		for (int i = 0; i < SimulPar.N; i++)
			switch (passengerState[i]) {
			case PassengerState.GOING_TO_AIRPORT:
				lineStatus += " GTAP ";
				passAnteriorState[i] = PassengerState.GOING_TO_AIRPORT;
				break;
			case PassengerState.IN_QUEUE:
				lineStatus += " INQE ";
				if (passAnteriorState[i] == PassengerState.GOING_TO_AIRPORT) {
					InQ++;
				}
				passAnteriorState[i] = PassengerState.IN_QUEUE;
				break;
			case PassengerState.IN_FLIGHT:
				lineStatus += " INFL ";
				if (passAnteriorState[i] == PassengerState.IN_QUEUE) {
					InQ--;
					InF++;
					log.writelnString("\nFlight " + numeroDeVoo + " : passenger " + passageiroAtual + " checked.");
				}
				passAnteriorState[i] = PassengerState.IN_FLIGHT;
				break;
			case PassengerState.AT_DESTINATION:
				lineStatus += " ATDS ";
				if (passAnteriorState[i] == PassengerState.IN_FLIGHT) {
					InF--;
					PTAL++;
				}
				passAnteriorState[i] = PassengerState.AT_DESTINATION;
				break;
			}

		lineStatus += "  " + InQ + "     " + InF + "     " + PTAL;
		log.writelnString(lineStatus);
		if (!log.close()) {
			GenericIO.writelnString("The operation of closing the file " + logFileName + " failed!");
			System.exit(1);
		}

	}
	
	/**
	 * informação do numero de voos e do numero de passageiros nele
	 */
	
	public synchronized void resumoDoPrograma() {		
		TextFile log = new TextFile(); // instantiation of a text file handler

		String lineStatus = ""; // state line to be printed

		if (!log.openForAppending(".", logFileName)) {
			GenericIO.writelnString("The operation of opening for appending the file " + logFileName + " failed!");
			System.exit(1);
		}
		
		log.writelnString("\nAirlift sum up:");
		for(int i=0; i<ndoVoo; i++) {
			String[] aux = informacaoDosVoos[i].split(":");
			log.writelnString("\nFlight " + aux[0] + " transported " + aux[1] + " passengers." );			
		}
		if (!log.close()) {
			GenericIO.writelnString("The operation of closing the file " + logFileName + " failed!");
			System.exit(1);
		}
	}

}
