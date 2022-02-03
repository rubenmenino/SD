package Server.stubs;

import Client.Communications.ClientCom;
import Common.Message;
import Common.MessageType;

/**
 * 
 * @author renato
 * @author ruben
 */
public class GeneralRepositoryStub {

	/**
	 * nome do servidor
	 */
	private String serverHostName;
	
	/**
	 * numero da porta do servidor
	 */
	private int serverPortNumber;

	/**
	 * construtor
	 */
	public GeneralRepositoryStub() {
		this.serverHostName = Common.RunParameters.repositoryHostName;
		this.serverPortNumber = Common.RunParameters.repositoryPort;
	}
	
	/**
	 * 
	 * @param id
	 * @param state
	 */
	public void setPassengerState(int id, int state) {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Repository active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Message nm = new Message(MessageType.SETPASSENGERSTATE);
		nm.setId(id);
			
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	/**
	 * 
	 * @param state
	 */
	public void setHostessState(int state){
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Repository active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		Message nm = new Message(MessageType.SETHOSTESSSTATE);
		nm.setHostessState(state);
			
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	/**
	 * 
	 * @param state
	 */
	public void setPilotState(int state) {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Repository active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		
		Message nm = new Message(MessageType.SETPILOTSTATE);
		nm.setPilotState(state);
			
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	
	/////////////////////////////7
	/**
	 * 
	 * @param nVoo
	 * @param npassageiros
	 */
	public void setInfoVoo(int nVoo, int npassageiros) {
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Repository active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		
		Message nm = new Message(MessageType.NUMBERVOO);
		nm.setnVoo(nVoo);
		nm.setNpassageiros(npassageiros);

			
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();
	}
	
	
	
	
	////////////////////////////////
	
	/**
	 * resumoDoPrograma
	 */
	public void resumoDoPrograma(){
		
		ClientCom cc = new ClientCom(serverHostName, serverPortNumber);
		while (!cc.open()) {
			System.out.println("Repository active yet, sleeping for 1 seccond");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		};
		
		Message nm = new Message(MessageType.ISSTOPPED);
	
		cc.writeObject(nm);
		Message responseOut = (Message) cc.readObject();
		cc.close();	
	}

}