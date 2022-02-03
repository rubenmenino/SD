package Server.proxies;

import Common.Message;
import Server.RepositoryMain;
import Server.Communications.ServerCom;
//import Server.sharedRegions.GeneralRepository;
import Server.sharedRegions.GeneralRepository;

/**
 * 
 * @author renato
 * @author ruben
 *
 */
public class GeneralRepositoryProxy implements SharedRegionProxy {
	/**
	 * GeneralRepository
	 */
	private final GeneralRepository repo;
	
	/**
	 * boolean 
	 */
	public boolean open;
	
	/**
	 * 
	 * @param repo
	 */
	public GeneralRepositoryProxy(GeneralRepository repo) {
		this.repo = repo;
	}

	
	/**
	 * @param msg
	 * @param scon
	 */
	@Override
	public Message processAndReply(Message msg, ServerCom scon) {
		Message nm = new Message();
		
		switch(msg.getMt()) {
		case SETPASSENGERSTATE:
			repo.setPassengerState(msg.getId(), msg.getPassengerState());
			nm.setOpDone(true);
			break;
		
		case SETHOSTESSSTATE:
			repo.setHostessState(msg.getId(), msg.getHostessState());
			nm.setOpDone(true);
			break;
			
		case SETPILOTSTATE:
			repo.setPilotState(msg.getPilotState());
			nm.setOpDone(true);
			break;
		
		case NUMBERVOO:
			repo.setInfoVoo(msg.getnVoo(), msg.getNpassageiros());
			nm.setOpDone(true);
			break;
		
		case ISSTOPPED:
			repo.resumoDoPrograma();
			nm.setOpDone(true);
			RepositoryMain.open = false;
			break;
		}	
		return nm;
	}

	/**
	 * boolean para parar de correr o server
	 */
    public synchronized boolean open(){
        return open;
    }

}
