package Common;

import java.io.Serializable;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * id do passageiro
     */
	private int id;
	

	/**
     * Tipo de mensagem
     * @serial mt
     */
	private MessageType mt;

	/**
	 * numero de voos
	 */
	private int nVoo;
	
	/**
	 * numero de passageiros
	 */
	private int npassageiros;

	/**
	 * estado do passageiro
	 */
    private int passengerState;
    
	/**
	 * estado da hospedeira
	 */
    private int hostessState;
    
	/**
	 * estado do piloto
	 */
    private int pilotState;
    
	/**
	 * validação da resposta
	 */
    private boolean boolResponse;
    
	/**
	 * todos os passageiros atendidos
	 */
    private boolean allPassAtended;
    
	/**
	 * operação feita
	 */
    private boolean opDone;

	/**
	 * 
	 * @param id
	 * @param mt
	 */
    public Message(int id, MessageType mt){
        this.id = id;
        this.mt = mt;
    }
    
    /**
     * 
     * @param mt
     */
    public Message(MessageType mt){
        //this.id = -1;
        this.mt = mt;
    }
    /**
     * 
     * @param operationDone
     */
    public Message(Boolean operationDone){
        this.opDone = operationDone;
    }
    /**
     * 
     */
    public Message() {
    }
	


	/**
	 * 
	 * @return get id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return getmt
	 */
	public MessageType getMt() {
		return mt;
	}


	/**
	 * 
	 * @return passenger state
	 */
	public int getPassengerState() {
		return passengerState;
	}

	/**
	 * 
	 * @param passengerState
	 */
	public void setPassengerState(int passengerState) {
		this.passengerState = passengerState;
	}

	/**
	 * 
	 * @return hostessState
	 */
	public int getHostessState() {
		return hostessState;
	}

	/**
	 * 
	 * @param hostessState
	 */
	public void setHostessState(int hostessState) {
		this.hostessState = hostessState;
	}

	/**
	 * 
	 * @return pilotState
	 */
	public int getPilotState() {
		return pilotState;
	}

	/**
	 * 
	 * @param pilotState
	 */
	public void setPilotState(int pilotState) {
		this.pilotState = pilotState;
	}

	/**
	 * 
	 * @return boolResponse
	 */
    public boolean isBoolResponse() {
		return boolResponse;
	}

    /**
     * 
     * @param boolResponse
     */
	public void setBoolResponse(boolean boolResponse) {
		this.boolResponse = boolResponse;
	}
	
	/**
	 * 
	 * @return allPassAtended
	 */
	public boolean isAllPassAtended() {
		return allPassAtended;
	}

	/**
	 * 
	 * @param allPassAtended
	 */
	public void setAllPassAtended(boolean allPassAtended) {
		this.allPassAtended = allPassAtended;
	}

	/**
	 * 
	 * @return opDone
	 */
	public boolean isOpDone() {
		return opDone;
	}

	/**
	 * 
	 * @param opDone
	 */
	public void setOpDone(boolean opDone) {
		this.opDone = opDone;
	}

	/**
	 * 
	 * @return nVoo
	 */
	public int getnVoo() {
		return nVoo;
	}
	
	/**
	 * 
	 * @param nVoo
	 */
	public void setnVoo(int nVoo) {
		this.nVoo = nVoo;
	}

	/**
	 * 
	 * @return npassageiros
	 */
	public int getNpassageiros() {
		return npassageiros;
	}

	/**
	 * 
	 * @param npassageiros
	 */
	public void setNpassageiros(int npassageiros) {
		this.npassageiros = npassageiros;
	}

	

	
	
	
	
	




	
	
    
    
    
}
