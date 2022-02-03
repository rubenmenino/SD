package entities;

/**
 * 
 * Estado interno da hospedeira ({@link Hostess}).
 *
 */

public final class PilotState {
	
	/**
	 * Piloto encontra.se no departure Airport
	 */
	public static final int AT_TRANSFER_GATE = 0;
	
	/**
	 * O piloto avisa que j� � possivel os passageiros embarcarem
	 */
	public static final int READING_FOR_BOARDING = 1;
	
	/**
	 * Piloto espera que o boarding termine
	 */
	public static final int WAIT_FOR_BOARDING = 2;
	
	/**
	 * O piloto faz o voo para o destino
	 */
    public static final int FLYING_FORWARD = 3;
    
    /**
     * Os passageiros saiem do aviao
     */
    public static final int DEBOARDING = 4;
    
    /**
     * o piloto vai de volta para o departureAirport
     */
    public static final int FLYING_BACK = 5;

    /**
     *   It can not be instantiated.
     */

	private PilotState ()
	{ }
}
