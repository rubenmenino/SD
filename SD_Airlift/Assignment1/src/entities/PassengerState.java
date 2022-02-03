package entities;

/**
 * 
 * Estado interno do passageiro ({@link Passenger}).
 *
 */
public final class PassengerState {
	
	/**
	 * O passageiro dirige-se ao aeroporto
	 */
	public static final int GOING_TO_AIRPORT = 0;
	
	/**
	 * O passageiro espera na fila
	 */
	public static final int IN_QUEUE = 1;
	
	/**
	 * o passageiro esta dentro do aviao à espera que acabe o voo
	 */
	public static final int IN_FLIGHT = 2;
	
	/**
	 * o passageiro chegou ao destino
	 */
	public static final int AT_DESTINATION = 3;
	
	/**
	   *   It can not be instantiated.
	   */

	private PassengerState ()
	{ }
}
