package entities;

/**
 * 
 * Estado interno da hospedeira ({@link Hostess}).
 *
 */
public final class HostessState{

	/**
	 * A hoespedeira est� � espera da chegada do piloto
	 */
	 public static final int WAIT_FOR_FLIGHT = 0;
	 
	 /**
	  * A hospedeira 
	  */
	 public static final int READY_TO_FLY = 1;
	 
	 /**
	  * A hospedeira � acordada pela opera�ao waitinQueue do passageiro enquanto espera pelos documentos
	  */
	 public static final int WAIT_FOR_PASSENGER = 2;
	 
	 /**
	  *  A hospedeira v� os documentso do passageiro
	  */
	 public static final int CHECK_PASSENGER = 3;
	 
	 
	 /**
	   *   It can not be instantiated.
	   */

	 
	 private HostessState ()
	 { }
}
